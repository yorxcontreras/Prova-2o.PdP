class AlertaVermelho implements EstadoUsina {
    @Override public void ligar(UsinaNuclear usina) { System.out.println("Não pode ligar, perigo iminente."); }
    @Override public void desligar(UsinaNuclear usina) { usina.setEstado(new Desligada()); }

    // Regra: ALERTA_VERMELHO → EMERGENCIA: se sistema de resfriamento falhar
    @Override
    public void resfriar(UsinaNuclear usina, boolean falhaResfriamento) {
        System.out.println("Alerta Vermelho: Resfriamento é crítico.");
        if (falhaResfriamento) {
            // Restrição atendida: EMERGENCIA só é ativada após passar por ALERTA_VERMELHO
            usina.setEstado(new Emergencia());
        } else if (usina.getTemperatura() < 350) {
             // Exemplo de transição bidirecional (volta, se a temperatura cair significativamente)
             usina.setEstado(new AlertaAmarelo());
        }
        // Previna transições circulares perigosas (não pode voltar a Normal/Amarelo direto se as condições não melhorarem)
    }
    @Override public void aquecer(UsinaNuclear usina, int temperatura) { System.out.println("Aquecimento extremo. Foco em resfriar."); }
    @Override public String getNome() { return "ALERTA_VERMELHO"; }
}