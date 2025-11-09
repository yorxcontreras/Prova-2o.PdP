class CalculoStressTesting implements EstrategiaRisco {
    @Override
    public String calcularMetrica(ParametrosFinanceiros contexto) {
        // Implementação dummy do Stress Testing.
        System.out.println("-> Algoritmo Stress Testing: Analisando " + contexto);
        return "Stress Testing realizado: Cenário de crise simulado (Perda potencial de 25% do capital).";
    }
}