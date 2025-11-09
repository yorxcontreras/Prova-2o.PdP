import java.util.Map;
import java.util.HashMap;

/**
 * Padrão de Projeto Escolhido: Strategy (Estratégia)
 *
 * Justificativa:
 * O problema exige que diferentes algoritmos de cálculo de risco (VaR, Expected Shortfall, Stress Testing)
 * sejam facilmente intercambiáveis em tempo de execução. O padrão Strategy define uma família de algoritmos,
 * coloca cada um em uma classe separada (as estratégias concretas) e os torna intercambiáveis
 * através de uma interface comum (EstrategiaRisco). Isso permite que o contexto (SistemaProcessamentoRisco)
 * mude o comportamento (o algoritmo de cálculo) dinamicamente, sem alterar sua estrutura,
 * e sem que o cliente precise conhecer a implementação dos algoritmos (Princípio Open/Closed - SOLID).
 */

// --- 1. Contexto Complexo de Parâmetros Financeiros ---
class ParametrosFinanceiros {
    private Map<String, Object> dados;

    public ParametrosFinanceiros(double valorInvestido, double confianca, String periodo) {
        this.dados = new HashMap<>();
        this.dados.put("valorInvestido", valorInvestido);
        this.dados.put("nivelConfianca", confianca);
        this.dados.put("periodo", periodo);
    }

    public Object get(String chave) {
        return dados.get(chave);
    }

    @Override
    public String toString() {
        return "Contexto: " + dados.toString();
    }
}