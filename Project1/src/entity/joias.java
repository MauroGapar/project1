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
public class joias {
    private String id; // Identificador exclusivo da joia
    private String nome; // Nome da joia
    private String tipo; // Tipo da joia (colar, brinco ou anel)
    private String material; // Material da joia (ouro, prata, etc.)
    private double peso; // Peso da joia em gramas
    private double preco; // Preço da joia
    private int quantidadeEstoque; // Quantidade disponível em estoque

    public joias(String id, String nome, String tipo, String material, double peso, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.material = material;
        this.peso = peso;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    // Método para verificar se há estoque suficiente
    public boolean verificarEstoque(int quantidadeDesejada) {
        return quantidadeDesejada <= quantidadeEstoque;
    }
    
     // Método para atualizar o estoque após uma venda
    public void atualizarEstoque(int quantidadeVendida) {
        if (verificarEstoque(quantidadeVendida)) {
            quantidadeEstoque -= quantidadeVendida;
        } else {
            System.out.println("Quantidade insuficiente em estoque!");
        }
    }

    // Método para exibir os detalhes da joia
    public void exibirDetalhes() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Material: " + material);
        System.out.println("Peso: " + peso + "g");
        System.out.println("Preço: " + preco);
        System.out.println("Quantidade em Estoque: " + quantidadeEstoque);
    }
    
    
}
