/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Mauro Gaspar
 */
public class aneis extends joias{
     private int tamanho;

    public aneis(String id, String nome, String tipo, String material, double peso, double preco, int quantidadeEstoque, int tamanho) {
        super(id, nome, tipo, material, peso, preco, quantidadeEstoque);
        this.tamanho = tamanho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    // Método para verificar se o tamanho do anel é compatível com o solicitado
    public boolean verificarTamanho(int tamanhoSolicitado) {
        return this.tamanho == tamanhoSolicitado;
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas dos anéis
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Joias)
        System.out.println("Tamanho: " + tamanho);
    }
    
}
