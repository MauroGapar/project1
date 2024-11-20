package controller;

import entity.Pedido;
import service.PedidosService;

import java.util.List;
import java.util.Scanner;

public class PedidosController {
    private PedidosService pedidosService;

    // Construtor para inicializar o serviço de pedidos
    public PedidosController() {
        this.pedidosService = new PedidosService();
    }

    // Método principal para gerenciar pedidos
    public void gerenciarPedidos() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==== Gerenciamento de Pedidos ====");
            System.out.println("1. Adicionar Pedido");
            System.out.println("2. Listar Todos os Pedidos");
            System.out.println("3. Buscar Pedido por ID");
            System.out.println("4. Atualizar Status do Pedido");
            System.out.println("5. Remover Pedido");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarPedido();
                case 2 -> listarPedidos();
                case 3 -> buscarPedidoPorId();
                case 4 -> atualizarStatusPedido();
                case 5 -> removerPedido();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para adicionar um novo pedido
    private void adicionarPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Pedido: ");
        String id = scanner.nextLine();

        // Verificar se o ID do pedido já existe
        if (pedidosService.listarTodos().stream().anyMatch(p -> p.getId().equals(id))) {
            System.out.println("Erro: Pedido com o mesmo ID já existe.");
            return;
        }

        System.out.print("ID do Cliente: ");
        String clienteId = scanner.nextLine();
        // Aqui você pode buscar o cliente pelo ID, caso tenha uma implementação de ClienteRepository

        System.out.print("Data do Pedido (yyyy-MM-dd): ");
        String data = scanner.nextLine();

        // Criando o pedido com status PENDENTE por padrão
        Pedido pedido = new Pedido(id, null, Pedido.formatarData(data)); // Supondo que 'null' será substituído por um cliente real
        pedidosService.criarPedido(pedido);

        System.out.println("Pedido adicionado com sucesso!");
    }

    // Método para listar todos os pedidos
    private void listarPedidos() {
        List<Pedido> pedidos = pedidosService.listarTodos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido.getId() + " - " + pedido.getStatus());
            }
        }
    }

    // Método para buscar um pedido pelo ID
    private void buscarPedidoPorId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Pedido: ");
        String id = scanner.nextLine();
        try {
            Pedido pedido = pedidosService.buscarPorId(id);
            System.out.println("Pedido encontrado: ");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Data: " + pedido.getData());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para atualizar o status de um pedido
    private void atualizarStatusPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Pedido: ");
        String id = scanner.nextLine();
        try {
            Pedido pedido = pedidosService.buscarPorId(id);
            System.out.println("Status atual: " + pedido.getStatus());
            System.out.println("Escolha o novo status:");
            System.out.println("1. Pendente");
            System.out.println("2. Aceito");
            System.out.println("3. Entregue");
            System.out.println("4. Cancelado");
            int statusEscolhido = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            Pedido.StatusPedido novoStatus = switch (statusEscolhido) {
                case 1 -> Pedido.StatusPedido.PENDENTE;
                case 2 -> Pedido.StatusPedido.ACEITO;
                case 3 -> Pedido.StatusPedido.ENTREGUE;
                case 4 -> Pedido.StatusPedido.CANCELADO;
                default -> {
                    System.out.println("Status inválido. Operação cancelada.");
                    yield null;
                }
            };

            if (novoStatus != null) {
                pedido.setStatus(novoStatus);
                pedidosService.atualizarStatus(id, novoStatus);
                System.out.println("Status do pedido atualizado com sucesso!");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para remover um pedido
    private void removerPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Pedido: ");
        String id = scanner.nextLine();
        try {
            pedidosService.removerPedido(id);
            System.out.println("Pedido removido com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}