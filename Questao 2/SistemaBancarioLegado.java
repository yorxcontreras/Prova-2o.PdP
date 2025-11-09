import java.util.HashMap;

class SistemaBancarioLegado {
    // Classe com interface complexa e incompatível.
    // Usa HashMap para parâmetros e exige um campo 'referencia'.
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("\n[SISTEMA LEGADO] Recebendo processamento de transação...");

        // Adiciona um campo obrigatório do legado (tratamento de campo obrigatório)
        if (!parametros.containsKey("referencia")) {
            throw new IllegalArgumentException("[LEGADO] Campo 'referencia' obrigatório ausente.");
        }

        String cartao = (String) parametros.get("cartao");
        double valor = (double) parametros.get("valor");
        int codMoeda = (int) parametros.get("codMoeda");
        String referencia = (String) parametros.get("referencia");

        System.out.printf("[LEGADO] Processando Cartão: %s, Valor: %.2f, CodMoeda: %d, Ref: %s\n", cartao, valor, codMoeda, referencia);

        // Retorno (Resposta Incompatível do Legado)
        HashMap<String, Object> resultadoLegado = new HashMap<>();
        resultadoLegado.put("statusCod", 200); // Código de status interno do legado
        resultadoLegado.put("mensagemLegado", "Transacao APROVADA - ID " + System.currentTimeMillis());
        return resultadoLegado;
    }
}