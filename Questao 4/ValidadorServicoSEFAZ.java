class ValidadorServicoSEFAZ extends ValidadorBase {
    // Validador 5: Executado apenas se V1, V2, V3 e V4 passarem (garantido pela CoR).
    @Override protected boolean executarValidacao(DocumentoFiscal doc) {
        // Simulação: Consulta online. Falha se a string for muito longa (simulando documento complexo que trava o serviço)
        return doc.getXmlData().length() < 100;
    }
    @Override protected int getTimeoutSegundos() { return 5; } // Timeout maior
}