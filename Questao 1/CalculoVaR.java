class CalculoVaR implements EstrategiaRisco {
    @Override
    public String calcularMetrica(ParametrosFinanceiros contexto) {
        // Implementação dummy do VaR.
        // O algoritmo utiliza o contexto complexo (ex: nível de confiança, período).
        double investido = (double) contexto.get("valorInvestido");
        double riscoCalculado = investido * 0.05; // Dummy 5% de risco
        System.out.println("-> Algoritmo VaR: Analisando " + contexto);
        return String.format("Value at Risk (VaR) calculado: R$ %.2f (Perda máxima com 95%% de confiança).", riscoCalculado);
    }
}