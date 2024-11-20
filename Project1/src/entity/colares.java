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
public class colares extends joias{
    
     private double comprimento; // Comprimento do colar em centímetros

    public colares(double comprimento, String id, String nome, String tipo, String material, double peso, double preco, int quantidadeEstoque) {
        super(id, nome, tipo, material, peso, preco, quantidadeEstoque);
        this.comprimento = comprimento;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }
     
      // Método para verificar se o comprimento está dentro de um intervalo aceitável
    public boolean verificarComprimento(double comprimentoMinimo, double comprimentoMaximo) {
        return comprimento >= comprimentoMinimo && comprimento <= comprimentoMaximo;
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas do colar
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Joias)
        System.out.println("Comprimento: " + comprimento + " cm");
    }
}
