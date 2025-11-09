import java.util.HashMap;
import java.util.Map;

/**
 * Padrão de Projeto Escolhido: Adapter (Adaptador)
 *
 * Justificativa:
 * O problema é clássico de integração com sistemas legados (terceiros) que possuem interfaces incompatíveis
 * (assinaturas de métodos e tipos de dados - HashMap, moedas codificadas). O padrão Adapter atua como um
 * 'tradutor' ou 'ponte'. Ele permite que a classe moderna (ProcessadorTransacoes) trabalhe com a classe
 * legada (SistemaBancarioLegado) sem a necessidade de mudar o código de nenhuma das duas.
 * O Adaptador encapsula a lógica de conversão de dados e tipos incompatíveis, garantindo o
 * Princípio da Responsabilidade Única (SRP - SOLID) e atendendo à conversão bidirecional.
 */

// --- 1. Target (Interface Desejada/Moderna) ---
interface ProcessadorTransacoes {
    // Interface atualizada e limpa do nosso sistema.
    // Métodos e tipos de dados modernos.
    Map<String, Object> autorizar(String cartao, double valor, String moeda);
}