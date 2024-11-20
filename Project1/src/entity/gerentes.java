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
public class gerentes extends funcionarios {
    private int numeroDeEquipes; // Número de equipes supervisionadas pelo gerente
    private double metasGerenciais; // Metas específicas de gerenciamento

    public gerentes(String id, String nome, String nif, String email, String telefone, String endereco, 
            String dataContratacao, double salario, int numeroDeEquipes, double metasGerenciais) {
        super(id, nome, nif, email, telefone, endereco, dataContratacao, salario);
        
        this.numeroDeEquipes = numeroDeEquipes;
        this.metasGerenciais = metasGerenciais;
    }

    public int getNumeroDeEquipes() {
        return numeroDeEquipes;
    }

    public void setNumeroDeEquipes(int numeroDeEquipes) {
        this.numeroDeEquipes = numeroDeEquipes;
    }

    public double getMetasGerenciais() {
        return metasGerenciais;
    }

    public void setMetasGerenciais(double metasGerenciais) {
        this.metasGerenciais = metasGerenciais;
    }

    // Método para calcular o bônus baseado nas metas gerenciais
    public double calcularBonus() {
        if (metasGerenciais == 0) {
            return 0.0;
        }
        return metasGerenciais * 0.10; // Por exemplo, 10% das metas gerenciais
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas do gerente
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Funcionario)
        System.out.println("Número de Equipes: " + numeroDeEquipes);
        System.out.println("Metas Gerenciais: " + metasGerenciais);
        System.out.printf("Bônus Gerencial Calculado: %.2f\n", calcularBonus());
    }
   
    
    
}
