
package entity;

/**
 *
 * @author Mauro Gaspar
 */
public class cliente extends pessoa {
     private int totalPedidos; // Número total de pedidos feitos pelo cliente
    private double valorTotalGasto; // Valor total gasto pelo cliente na loja

    public cliente(String id, String nome, String nif, String email, String telefone, String endereco, 
            int totalPedidos, double valorTotalGasto) {
        super(id, nome, nif, email, telefone, endereco);
        this.totalPedidos = totalPedidos;
        this.valorTotalGasto = valorTotalGasto;
    }

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(int totalPedidos) {
        this.totalPedidos = totalPedidos;
    }

    public double getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(double valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }
    
    // Método para atualizar o total de pedidos e o valor total gasto
    public void registrarPedido(double valorPedido) {
        totalPedidos++; // Incrementa o número de pedidos
        valorTotalGasto += valorPedido; // Soma o valor do pedido ao total gasto
    }

    // Sobrescrita do método exibirDetalhes para incluir informações específicas do cliente
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Chama o método da classe pai (Pessoa)
        System.out.println("Total de Pedidos: " + totalPedidos);
        System.out.println("Valor Total Gasto: " + valorTotalGasto);
    }
    
    
}
