class DemoQuestao3 {
    public static void main(String[] args) throws InterruptedException {
        UsinaNuclear usina = new UsinaNuclear(new Desligada());

        // 1. Ligar -> Operação Normal
        usina.ligar(); 

        // 2. Simular aquecimento até Alerta Amarelo (> 300°C)
        usina.setTemperatura(350);
        usina.operar(); // OPERACAO_NORMAL -> ALERTA_AMARELO

        // 3. Simular aquecimento e tempo para Alerta Vermelho (> 400°C por 30s)
        usina.setTemperatura(450);
        System.out.println("\n--- SIMULAÇÃO DE 40 SEGUNDOS COM TEMPERATURA ALTA ---");
        for (int i = 0; i < 4; i++) { // 4 iterações de 10s (total 40s)
            usina.operar(); // Tenta transicionar para ALERTA_VERMELHO
            if (usina.getEstado().getNome().equals("ALERTA_VERMELHO")) break;
            Thread.sleep(10); 
        }

        // 4. Ativar Modo Manutenção (sobrescreve temporariamente)
        usina.entrarEmManutencao();
        usina.setTemperatura(250); // Mudar temp, mas estado não muda
        usina.operar();
        usina.desligar(); // Estado não muda

        // 5. Sair da Manutenção e Forçar Emergência (após Vermelho)
        usina.sairDeManutencao();
        System.out.println("\n--- FORÇANDO EMERGÊNCIA ---");
        usina.resfriarSistema(true); // ALERTA_VERMELHO -> EMERGENCIA (por falha de resfriamento)

        // 6. Tentar voltar para Normal (Previne transições circulares perigosas)
        usina.ligar(); // Estado de Emergência não permite
        usina.desligar(); // EMERGENCIA -> DESLIGADA
    }
}