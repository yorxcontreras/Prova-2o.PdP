class Desligada implements EstadoUsina {
    @Override
    public void ligar(UsinaNuclear usina) {
        usina.setEstado(new OperacaoNormal());
    }
    @Override public void desligar(UsinaNuclear usina) { System.out.println("Já está desligada."); }
    @Override public void aquecer(UsinaNuclear usina, int temp) { System.out.println("Não pode aquecer, usina desligada."); }
    @Override public void resfriar(UsinaNuclear usina, boolean falha) { System.out.println("Não precisa resfriar, usina desligada."); }
    @Override public String getNome() { return "DESLIGADA"; }
}