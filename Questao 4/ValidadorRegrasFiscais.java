class ValidadorRegrasFiscais extends ValidadorBase {
    // Validador 3: Executado apenas se V1 e V2 passarem (garantido pela CoR no ValidadorBase).
    @Override protected boolean executarValidacao(DocumentoFiscal doc) {
        // Simulação de cálculo de impostos. Falha se o XML for "ERRO_FISCAL"
        return !doc.getXmlData().contains("ERRO_FISCAL");
    }
    @Override protected int getTimeoutSegundos() { return 3; }
}