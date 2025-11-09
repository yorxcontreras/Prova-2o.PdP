class UsinaNuclear {
    private EstadoUsina estadoAtual;
    private int temperatura = 25; // Simulação de um parâmetro de validação
    private boolean emManutencao = false; // Restrição: Modo "manutenção"
    private int tempoAlertaAmarelo = 0; // Para regra de 30 segundos

    public UsinaNuclear(EstadoUsina estadoInicial) {
        this.estadoAtual = estadoInicial;
        System.out.println("Usina inicializada. Estado: " + estadoInicial.getNome());
    }

    public void setEstado(EstadoUsina novoEstado) {
        if (emManutencao) {
            // Regra: Modo "manutenção" que sobreescreve temporariamente os estados
            System.out.println("[Manutenção] Transição de estado ignorada. Permanece em " + this.estadoAtual.getNome());
            return;
        }

        System.out.println("Transição: " + this.estadoAtual.getNome() + " -> " + novoEstado.getNome());
        this.estadoAtual = novoEstado;
        this.tempoAlertaAmarelo = 0; // Reset ao sair do Amarelo
    }

    public EstadoUsina getEstado() { return estadoAtual; }
    public int getTemperatura() { return temperatura; }
    public void setTemperatura(int temperatura) { this.temperatura = temperatura; }
    // Simula a passagem do tempo em blocos de 10 segundos
    public void incrementarTempoAlertaAmarelo() { this.tempoAlertaAmarelo += 10; } 
    public int getTempoAlertaAmarelo() { return tempoAlertaAmarelo; }

    public void entrarEmManutencao() {
        this.emManutencao = true;
        System.out.println("\n*** ATENÇÃO: Usina entrou em Modo Manutenção. Sobreescrevendo estados normais. ***");
    }

    public void sairDeManutencao() {
        this.emManutencao = false;
        System.out.println("*** Usina saiu do Modo Manutenção. Retornando ao controle de estado normal. ***");
        // Força uma reavaliação ou permanece no último estado válido.
        System.out.println("Ação: Usina está em " + this.estadoAtual.getNome());
    }

    // Métodos de delegação para o Estado
    public void ligar() { estadoAtual.ligar(this); }
    public void desligar() { estadoAtual.desligar(this); }
    public void operar() { estadoAtual.aquecer(this, this.temperatura); }
    public void resfriarSistema(boolean falhaResfriamento) { estadoAtual.resfriar(this, falhaResfriamento); }
}