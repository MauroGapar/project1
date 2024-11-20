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
public class vendedores extends funcionarios{
    
    private double metasVendas; // Metas de vendas atribuídas ao vendedor
    private double vendasRealizadas; // Vendas realizadas pelo vendedor
    
    public vendedores(String id, String nome, String nif, String email, String telefone, String endereco, String dataContratacao, 
            double salario, double metasVendas, double vendasRealizadas) {
        super(id, nome, nif, email, telefone, endereco, dataContratacao, salario);
        this.metasVendas = metasVendas;
        this.vendasRealizadas = vendasRealizadas;
    }

    public double getMetasVendas() {
        return metasVendas;
    }

    public void setMetasVendas(double metasVendas) {
        this.metasVendas = metasVendas;
    }

    public double getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(double vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }
    
    // Método para calcular a porcentagem de vendas atingida
    public double calcularPercentualMeta() {
        if (metasVendas == 0) {
            return 0.0;
        }
        return (vendasRealizadas / metasVendas) * 100;
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas do vendedor
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Funcionario)
        System.out.println("Metas de Vendas: " + metasVendas);
        System.out.println("Vendas Realizadas: " + vendasRealizadas);
        System.out.printf("Percentual da Meta Alcançado: %.2f%%\n", calcularPercentualMeta());
    }
    
}
