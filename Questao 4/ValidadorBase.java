import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

abstract class ValidadorBase implements Validador {
    private Validador proximoValidador;
    // Usa um pool de threads para gerenciar o timeout de cada validador
    protected static final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void setProximo(Validador proximo) {
        this.proximoValidador = proximo;
    }

    protected abstract boolean executarValidacao(DocumentoFiscal documento);
    protected abstract int getTimeoutSegundos();

    @Override
    public boolean fazerRollback(DocumentoFiscal documento) {
        // Implementação padrão de rollback (a ser sobrescrita se necessário)
        return true;
    }

    @Override
    public boolean validar(DocumentoFiscal documento) {
        String nomeValidador = this.getClass().getSimpleName();
        System.out.println("\n[Validador " + nomeValidador + "] Iniciando...");

        if (documento.getFalhasConsecutivas() >= 3) {
            // Implementação do "Circuit Breaker": interrompe após 3 falhas
            System.out.println("!!! CIRCUIT BREAKER ATIVADO !!! Mais de 3 falhas consecutivas. Interrompendo cadeia.");
            return false;
        }

        // Executa a validação em uma thread separada para controle de timeout
        Callable<Boolean> tarefa = () -> executarValidacao(documento);
        Future<Boolean> future = executor.submit(tarefa);
        boolean resultado = false;

        try {
            // Implementação de Timeout Individual para cada validador (Restrição atendida)
            resultado = future.get(getTimeoutSegundos(), TimeUnit.SECONDS);

            if (resultado) {
                System.out.println("-> [OK] Validador " + nomeValidador + " passou.");
                documento.setFalhasConsecutivas(0); // Reset do contador de falhas

                // Continua a cadeia (Validações Condicionais)
                if (proximoValidador != null) {
                    // Se o próximo falhar, o rollback é acionado recursivamente
                    if (!proximoValidador.validar(documento)) {
                        System.out.println("<-- [ROLLBACK] Iniciando Rollback no " + nomeValidador + ".");
                        fazerRollback(documento); // Chama o rollback se o restante da cadeia falhar
                        return false; // Propaga a falha
                    }
                }
                return true; // Sucesso
            } else {
                System.out.println("-> [FALHA] Validador " + nomeValidador + " falhou.");
                documento.setFalhasConsecutivas(documento.getFalhasConsecutivas() + 1);
                return false; // Interrompe a cadeia de sucesso
            }

        } catch (TimeoutException e) {
            future.cancel(true); // Cancela a tarefa em caso de timeout
            System.out.println("-> [TIMEOUT] Validador " + nomeValidador + " excedeu o tempo limite de " + getTimeoutSegundos() + "s.");
            documento.setFalhasConsecutivas(documento.getFalhasConsecutivas() + 1);
            return false;
        } catch (Exception e) {
            System.out.println("-> [ERRO] Validador " + nomeValidador + " erro inesperado: " + e.getMessage());
            documento.setFalhasConsecutivas(documento.getFalhasConsecutivas() + 1);
            return false;
        }
    }
}