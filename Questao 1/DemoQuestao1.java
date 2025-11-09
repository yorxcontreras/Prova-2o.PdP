class DemoQuestao1 {
    public static void main(String[] args) {
        // Cria o contexto complexo (parâmetros financeiros)
        ParametrosFinanceiros params = new ParametrosFinanceiros(100000.0, 0.95, "30 dias");

        // 1. Inicializa o sistema com o primeiro algoritmo (VaR)
        SistemaProcessamentoRisco sistema = new SistemaProcessamentoRisco(new CalculoVaR());
        String resultadoVaR = sistema.processarRisco(params);
        System.out.println("Resultado: " + resultadoVaR);

        // 2. Troca o algoritmo para Expected Shortfall (intercambiável em tempo de execução)
        sistema.setEstrategia(new CalculoExpectedShortfall());
        String resultadoES = sistema.processarRisco(params);
        System.out.println("Resultado: " + resultadoES);

        // 3. Troca para Stress Testing
        sistema.setEstrategia(new CalculoStressTesting());
        String resultadoStress = sistema.processarRisco(params);
        System.out.println("Resultado: " + resultadoStress);
    }
}