class ValidadorSchemaXML extends ValidadorBase {
    @Override protected boolean executarValidacao(DocumentoFiscal doc) {
        // Validador 1: Simulação (Falha se XML for "INVALIDO")
        return !doc.getXmlData().contains("INVALIDO");
    }
    @Override protected int getTimeoutSegundos() { return 1; }
}