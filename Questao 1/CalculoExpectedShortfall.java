class CalculoExpectedShortfall implements EstrategiaRisco {
    @Override
    public String calcularMetrica(ParametrosFinanceiros contexto) {
        // Implementação dummy do Expected Shortfall (ES).
        double investido = (double) contexto.get("valorInvestido");
        double riscoCalculado = investido * 0.075; // Dummy 7.5% de risco
        System.out.println("-> Algoritmo Expected Shortfall: Analisando " + contexto);
        return String.format("Expected Shortfall (ES) calculado: R$ %.2f (Perda média além do VaR).", riscoCalculado);
    }
}