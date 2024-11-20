package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private String id; // Identificador único do pedido
    private cliente cliente; // Cliente que realizou o pedido
    private Date data; // Data do pedido
    private List<joias> itens; // Lista de itens de joia adquiridos
    private double valorTotal; // Valor total do pedido
    private StatusPedido status; // Status do pedido
    private List<pagamentos> pagamentos; // Lista de pagamentos associados ao pedido

    // Enum para os possíveis status do pedido
    public enum StatusPedido {
        ENTREGUE, PENDENTE, ACEITO, CANCELADO
    }

    // Construtor com ID, cliente e data
    public Pedido(String id, cliente cliente, Date data) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.itens = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        this.status = StatusPedido.PENDENTE; // Status inicial padrão
    }

    // Construtor completo
    public Pedido(String id, cliente cliente, Date data, List<joias> itens, double valorTotal, StatusPedido status) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.itens = itens != null ? itens : new ArrayList<>();
        this.valorTotal = valorTotal;
        this.status = status;
        this.pagamentos = new ArrayList<>();
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<joias> getItens() {
        return itens;
    }

    public void setItens(List<joias> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    // Método para adicionar um item ao pedido
    public void adicionarItem(joias joia, int quantidade) {
        if (joia.verificarEstoque(quantidade)) {
            itens.add(joia);
            joia.atualizarEstoque(quantidade);
            valorTotal += joia.getPreco() * quantidade;
        } else {
            System.out.println("Estoque insuficiente para o item: " + joia.getNome());
        }
    }

    // Método para exibir os detalhes do pedido
    public void exibirDetalhes() {
        System.out.println("ID do Pedido: " + id);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Sem cliente associado"));
        System.out.println("Data: " + data);
        System.out.println("Status: " + status);
        System.out.println("Itens do Pedido:");
        for (joias joia : itens) {
            System.out.println("- " + joia.getNome() + " | Preço: " + joia.getPreco());
        }
        System.out.println("Valor Total: " + valorTotal);
    }

    // Método para adicionar um pagamento ao pedido
    public void adicionarPagamento(pagamentos pagamento) {
        pagamentos.add(pagamento);
    }

    // Método para exibir todos os pagamentos do pedido
    public void exibirPagamentos() {
        System.out.println("Pagamentos do Pedido:");
        for (pagamentos pagamento : pagamentos) {
            pagamento.exibirDetalhes();
        }
    }
}