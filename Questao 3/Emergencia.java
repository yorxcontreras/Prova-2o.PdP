class Emergencia implements EstadoUsina {
    @Override public void ligar(UsinaNuclear usina) { System.out.println("Estado crítico! Apenas ações de contenção."); }
    @Override public void desligar(UsinaNuclear usina) { usina.setEstado(new Desligada()); }
    // Nenhuma transição para estados operacionais a partir daqui (Unidirecional).
    @Override public void aquecer(UsinaNuclear usina, int temp) { System.out.println("Emergência ativa. Foco na contenção."); }
    @Override public void resfriar(UsinaNuclear usina, boolean falha) { System.out.println("Falha total. Plano de emergência."); }
    @Override public String getNome() { return "EMERGENCIA"; }
}