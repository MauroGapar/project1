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
public class funcionarios extends pessoa {
     private String dataContratacao; // Data de contratação do funcionário
    private double salario;         // Salário do funcionário

    public funcionarios(String id, String nome, String nif, String email, String telefone, String endereco, String dataContratacao, double salario) {
        super(id, nome, nif, email, telefone, endereco); // Chamada ao construtor da superclasse Pessoa
        this.dataContratacao = dataContratacao;
        this.salario = salario;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    // Método para exibir os detalhes do funcionário
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método exibirDetalhes da superclasse Pessoa
        System.out.println("Data de Contratação: " + dataContratacao);
        System.out.println("Salário: " + salario);
    }

    
}
