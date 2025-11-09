import java.util.Random;

/**
 * Padrão de Projeto Escolhido: State (Estado)
 *
 * Justificativa:
 * O sistema exige um controle rígido sobre transições de múltiplos estados complexos (usina nuclear),
 * cada um com regras de validação específicas (temperatura, tempo) para a mudança. O padrão State
 * permite que um objeto mude seu comportamento quando seu estado interno muda. Cada estado da usina
 * (DESLIGADA, OPERACAO_NORMAL, etc.) é implementado como uma classe separada, o que encapsula o
 * comportamento específico e as regras de transição desse estado. Isso evita um grande bloco
 * de condicionais (if/else ou switch) na classe principal e torna as transições bidirecionais/unidirecionais
 * e as restrições (Emergência só após Vermelho) explícitas e fáceis de gerenciar (Princípio Open/Closed - SOLID).
 */

// --- 1. Interface State (EstadoUsina) ---
interface EstadoUsina {
    void ligar(UsinaNuclear usina);
    void desligar(UsinaNuclear usina);
    void aquecer(UsinaNuclear usina, int temperatura);
    void resfriar(UsinaNuclear usina, boolean falhaResfriamento);
    String getNome();
}