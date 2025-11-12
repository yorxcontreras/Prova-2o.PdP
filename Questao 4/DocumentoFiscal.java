/**
 * Padrão de Projeto Escolhido: Chain of Responsibility (Cadeia de Responsabilidade)
 *
 * Justificativa:
 * O problema exige uma série de validações sequenciais e condicionais, onde o sucesso de um validador
 * é condição para a execução do próximo, e existe um mecanismo de interrupção (Circuit Breaker).
 * O CoR é ideal, pois a cadeia de objetos (Validadores) processa a requisição em ordem. A lógica de
 * avanço (continua se sucesso), interrupção (falha ou Circuit Breaker), timeout e rollback é
 * centralizada no ValidadorBase, garantindo o Princípio da Responsabilidade Única (SRP - SOLID)
 * para cada validador especializado.
 */

// --- DTO de Documento Fiscal (NF-e) ---
class DocumentoFiscal {
    private final String xmlData;
    private final String certificadoSerial;
    private boolean dbInserted = false; // Flag para simular o estado de inserção no DB
    private int falhasConsecutivas = 0; // Para o "Circuit Breaker"

    public DocumentoFiscal(String xmlData, String certificadoSerial) {
        this.xmlData = xmlData;
        this.certificadoSerial = certificadoSerial;
    }

    public String getXmlData() { return xmlData; }
    public String getCertificadoSerial() { return certificadoSerial; }
    public boolean isDbInserted() { return dbInserted; }
    public void setDbInserted(boolean dbInserted) { this.dbInserted = dbInserted; }
    public int getFalhasConsecutivas() { return falhasConsecutivas; }
    public void setFalhasConsecutivas(int falhasConsecutivas) { this.falhasConsecutivas = falhasConsecutivas; }
}