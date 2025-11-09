class OperacaoNormal implements EstadoUsina {
    @Override public void ligar(UsinaNuclear usina) { System.out.println("Já está em operação normal."); }
    @Override public void desligar(UsinaNuclear usina) { usina.setEstado(new Desligada()); }

    // Regra: OPERACAO_NORMAL → ALERTA_AMARELO: se temperatura > 300°C
    @Override
    public void aquecer(UsinaNuclear usina, int temperatura) {
        System.out.println("Temperatura atual: " + temperatura + "°C.");
        if (temperatura > 300) {
            usina.setEstado(new AlertaAmarelo());
        } else {
            System.out.println("Operação normal e estável.");
        }
    }
    @Override public void resfriar(UsinaNuclear usina, boolean falha) { System.out.println("Resfriamento normal."); }
    @Override public String getNome() { return "OPERACAO_NORMAL"; }
}