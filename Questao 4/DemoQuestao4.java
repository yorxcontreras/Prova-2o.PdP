class DemoQuestao4 {
    public static void main(String[] args) {
        // --- 1. Cria os Validadores ---
        Validador v1Schema = new ValidadorSchemaXML();
        Validador v2Certificado = new ValidadorCertificadoDigital();
        Validador v3Fiscais = new ValidadorRegrasFiscais();
        Validador v4DB = new ValidadorBancoDeDados();
        Validador v5SEFAZ = new ValidadorServicoSEFAZ();

        // --- 2. Monta a Cadeia de Responsabilidade (CoR) ---
        v1Schema.setProximo(v2Certificado);
        v2Certificado.setProximo(v3Fiscais);
        v3Fiscais.setProximo(v4DB);
        v4DB.setProximo(v5SEFAZ); 

        // --- 3. Cenário 1: Sucesso Total ---
        System.out.println("--- CENÁRIO 1: Sucesso Total ---");
        DocumentoFiscal docSucesso = new DocumentoFiscal("<xml>Dados Fiscais OK</xml>", "CERT12345");
        boolean resultadoSucesso = v1Schema.validar(docSucesso);
        System.out.println("\nResultado Final (Sucesso): " + resultadoSucesso + ". DB Inserido: " + docSucesso.isDbInserted());

        // --- 4. Cenário 2: Falha no final com Rollback ---
        // V4 (DB) insere, V5 (SEFAZ) falha. V4 deve dar rollback.
        System.out.println("\n\n--- CENÁRIO 2: Falha na SEFAZ (Validador 5) com Rollback no DB ---");
        DocumentoFiscal docRollback = new DocumentoFiscal("<xml>XML muito longo para SEFAZ, o que causa falha no V5</xml>", "CERT67890");
        boolean resultadoRollback = v1Schema.validar(docRollback);
        System.out.println("\nResultado Final (Rollback): " + resultadoRollback + ". DB Inserido: " + docRollback.isDbInserted()); // Deve ser false

        // --- 5. Cenário 3: Circuit Breaker ---
        // Simular 3 falhas consecutivas (usando XML com "ERRO_FISCAL" que falha no V3)
        System.out.println("\n\n--- CENÁRIO 3: Ativação do Circuit Breaker ---");
        DocumentoFiscal docBreaker = new DocumentoFiscal("<xml>ERRO_FISCAL</xml>", "CERT-BREAKER"); 
        
        System.out.println("\n** Tentativa 1 (Falha no V3).");
        v1Schema.validar(docBreaker); 

        System.out.println("\n** Tentativa 2 (Falha no V3). Contador: " + docBreaker.getFalhasConsecutivas());
        v1Schema.validar(docBreaker);

        System.out.println("\n** Tentativa 3 (Falha no V3). Contador: " + docBreaker.getFalhasConsecutivas());
        v1Schema.validar(docBreaker);

        System.out.println("\n** Tentativa 4 (Interrupção): Circuit Breaker ativado no V1.");
        boolean resultadoBreaker = v1Schema.validar(docBreaker); // Deve interromper imediatamente

        System.out.println("\nResultado Final (Breaker): " + resultadoBreaker + ". Falhas consecutivas: " + docBreaker.getFalhasConsecutivas());

        // Desliga o executor de threads no final
        ValidadorBase.executor.shutdownNow();
    }
}