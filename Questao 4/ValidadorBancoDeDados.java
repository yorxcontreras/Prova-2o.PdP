class ValidadorBancoDeDados extends ValidadorBase {
    // Validador 4: Faz a inserção e implementa Rollback
    @Override protected boolean executarValidacao(DocumentoFiscal doc) {
        // Simulação: Checa duplicidade e insere (Falha se contém "DUPLICADO")
        if (doc.getXmlData().contains("DUPLICADO")) return false; 

        doc.setDbInserted(true); // Simula a inserção que PRECISA de rollback
        System.out.println("   [DB] Documento temporariamente inserido (status: " + doc.isDbInserted() + ").");
        return true;
    }

    // Restrição atendida: Validador 4 deve fazer rollback
    @Override
    public boolean fazerRollback(DocumentoFiscal documento) {
        if (documento.isDbInserted()) {
            // Simula o rollback da inserção
            documento.setDbInserted(false);
            System.out.println("   [DB] Rollback executado: Inserção revertida (status: " + documento.isDbInserted() + ").");
            return true;
        }
        return true;
    }
    @Override protected int getTimeoutSegundos() { return 4; }
}