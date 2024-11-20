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
public class brincos extends joias{
    private String tipoFecho; // Tipo de fecho do brinco (ex.: tarracha, argola) 

    public brincos(String id, String nome, String tipo, String material, double peso, double preco, 
            int quantidadeEstoque, String tipoFecho) {
        super(id, nome, tipo, material, peso, preco, quantidadeEstoque);
        this.tipoFecho= tipoFecho;
    }

    public String getTipoFecho() {
        return tipoFecho;
    }

    public void setTipoFecho(String tipoFecho) {
        this.tipoFecho = tipoFecho;
    }
    
     // Método para verificar se o tipo de fecho é compatível com um padrão especificado
    public boolean verificarCompatibilidadeFecho(String fechoEsperado) {
        return this.tipoFecho.equalsIgnoreCase(fechoEsperado);
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas dos brincos
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Joias)
        System.out.println("Tipo de Fecho: " + tipoFecho);
    }
    
    
}
