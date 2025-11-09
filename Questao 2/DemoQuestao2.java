import java.util.Map;
class DemoQuestao2 {
    public static void main(String[] args) {
        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();

        // O sistema cliente usa a interface moderna (Target)
        ProcessadorTransacoes processador = new AdaptadorBancario(sistemaLegado);

        // A chamada é feita usando a interface moderna
        Map<String, Object> resultado1 = processador.autorizar("1234-5678-9012-3456", 550.75, "BRL");
        System.out.println("\n[SISTEMA CLIENTE] Resultado Moderno (BRL): " + resultado1);

        Map<String, Object> resultado2 = processador.autorizar("9876-5432-1098-7654", 100.00, "USD");
        System.out.println("[SISTEMA CLIENTE] Resultado Moderno (USD): " + resultado2);
    }
}