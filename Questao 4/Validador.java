interface Validador {
    void setProximo(Validador proximo);
    boolean validar(DocumentoFiscal documento);
    boolean fazerRollback(DocumentoFiscal documento);
}