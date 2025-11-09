class AlertaAmarelo implements EstadoUsina {
    @Override public void ligar(UsinaNuclear usina) { System.out.println("Não pode ligar, já está em alerta."); }
    @Override public void desligar(UsinaNuclear usina) { usina.setEstado(new Desligada()); }

    // Regra: ALERTA_AMARELO → ALERTA_VERMELHO: se temperatura > 400°C por mais de 30 segundos
    @Override
    public void aquecer(UsinaNuclear usina, int temperatura) {
        System.out.println("Temperatura atual: " + temperatura + "°C. Alerta Amarelo.");
        if (temperatura > 400) {
            usina.incrementarTempoAlertaAmarelo();
            System.out.println("Contagem Alerta Vermelho: " + usina.getTempoAlertaAmarelo() + "s.");
            if (usina.getTempoAlertaAmarelo() >= 30) {
                usina.setEstado(new AlertaVermelho());
            }
        } else if (temperatura <= 300) {
            // Transição bidirecional (volta)
            usina.setEstado(new OperacaoNormal());
        } else {
            usina.incrementarTempoAlertaAmarelo(); // Continua contando no limite
        }
    }
    @Override public void resfriar(UsinaNuclear usina, boolean falha) { System.out.println("Resfriamento sob atenção."); }
    @Override public String getNome() { return "ALERTA_AMARELO"; }
}