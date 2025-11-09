import java.util.HashMap;
import java.util.Map;

// --- 3. Adapter (O Adaptador Bancário) ---
class AdaptadorBancario implements ProcessadorTransacoes {
    private SistemaBancarioLegado legado;

    public AdaptadorBancario(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    // Método que adapta a interface moderna para a interface legada
    @Override
    public Map<String, Object> autorizar(String cartao, double valor, String moeda) {
        System.out.println("\n[ADAPTER] Convertendo chamada moderna para formato legado...");

        // --- Conversão "Moderna -> Legado" (Tradução de Entrada) ---

        // 1. Conversão de Moeda
        int codMoeda = converterMoedaParaCodigo(moeda);
        if (codMoeda == -1) {
            throw new IllegalArgumentException("[ADAPTER] Moeda não suportada: " + moeda);
        }

        // 2. Conversão de Assinatura (para HashMap)
        HashMap<String, Object> parametrosLegado = new HashMap<>();
        parametrosLegado.put("cartao", cartao);
        parametrosLegado.put("valor", valor);
        parametrosLegado.put("codMoeda", codMoeda);

        // 3. Tratamento de Campo Obrigatório do Legado (adiciona 'referencia')
        parametrosLegado.put("referencia", "REF-" + cartao.substring(cartao.length() - 4));

        // Chama o método legado com os parâmetros adaptados
        HashMap<String, Object> respostaLegado = legado.processarTransacao(parametrosLegado);

        // --- Conversão "Legado -> Moderna" (Tradução de Saída/Bidirecional) ---

        System.out.println("[ADAPTER] Convertendo resposta legada para formato moderno...");
        return converterRespostaLegado(respostaLegado);
    }

    /**
     * CORREÇÃO JAVA 11: Usa a sintaxe switch/case/break tradicional.
     */
    private int converterMoedaParaCodigo(String moeda) {
        int codigo;
        switch (moeda.toUpperCase()) {
            case "USD":
                codigo = 1;
                break;
            case "EUR":
                codigo = 2;
                break;
            case "BRL":
                codigo = 3;
                break;
            default:
                codigo = -1;
                break;
        }
        return codigo;
    }

    private Map<String, Object> converterRespostaLegado(HashMap<String, Object> respostaLegado) {
        Map<String, Object> respostaModerna = new HashMap<>();
        int statusCode = (int) respostaLegado.get("statusCod");

        // Converte o status code interno (200) para um status booleano moderno
        if (statusCode == 200) {
            respostaModerna.put("autorizado", true);
            respostaModerna.put("mensagem", "Transação Autorizada com Sucesso.");
        } else {
            respostaModerna.put("autorizado", false);
            respostaModerna.put("mensagem", "Falha na Transação. Detalhe Legado: " + respostaLegado.get("mensagemLegado"));
        }
        return respostaModerna;
    }
}