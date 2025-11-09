class ValidadorCertificadoDigital extends ValidadorBase {
    @Override protected boolean executarValidacao(DocumentoFiscal doc) {
        // Validador 2: Simulação (Certificado válido se não for "EXPIRADO")
        return doc.getCertificadoSerial() != null && !doc.getCertificadoSerial().contains("EXPIRADO");
    }
    @Override protected int getTimeoutSegundos() { return 2; }
}