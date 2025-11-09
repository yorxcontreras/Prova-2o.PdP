interface EstrategiaRisco {
    // A interface define o método comum para todos os algoritmos.
    // Recebe o contexto complexo (compartilhado) como parâmetro.
    String calcularMetrica(ParametrosFinanceiros contexto);
}