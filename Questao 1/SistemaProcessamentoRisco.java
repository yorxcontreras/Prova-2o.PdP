class SistemaProcessamentoRisco {
    // O sistema mantém uma referência à Estratégia, permitindo a troca dinâmica.
    private EstrategiaRisco estrategiaAtual;

    public SistemaProcessamentoRisco(EstrategiaRisco estrategiaInicial) {
        this.estrategiaAtual = estrategiaInicial;
    }

    // Permite trocar a estratégia em tempo de execução (dinamicamente).
    public void setEstrategia(EstrategiaRisco novaEstrategia) {
        // Restrição atendida: O cliente pode mudar de algoritmo sem conhecer os detalhes.
        System.out.println("\n*** Trocando estratégia de risco para: " + novaEstrategia.getClass().getSimpleName() + " ***");
        this.estrategiaAtual = novaEstrategia;
    }

    // Delega a execução para a estratégia configurada no momento.
    public String processarRisco(ParametrosFinanceiros contexto) {
        System.out.println("Iniciando processamento de risco...");
        return this.estrategiaAtual.calcularMetrica(contexto);
    }
}