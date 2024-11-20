package controller;

import entity.cliente;
import service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteController {
    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void gerenciarClientes() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==== Gerenciamento de Clientes ====");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Atualizar Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarClientePorId();
                case 4 -> atualizarCliente();
                case 5 -> removerCliente();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        String id = scanner.nextLine();
        if (clienteService.listarTodos().stream().anyMatch(c -> c.getId().equals(id))) {
            System.out.println("Erro: Cliente com o mesmo ID já existe.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        cliente cliente = new cliente(id, nome, nif, email, telefone, endereco, 0, 0.0);
        clienteService.criarCliente(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private void listarClientes() {
        List<cliente> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (cliente cliente : clientes) {
                System.out.println(cliente.getId() + " - " + cliente.getNome());
            }
        }
    }

    private void buscarClientePorId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Cliente: ");
        String id = scanner.nextLine();
        try {
            cliente cliente = clienteService.buscarPorId(id);
            System.out.println("Cliente encontrado: " + cliente.getNome());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void atualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Cliente: ");
        String id = scanner.nextLine();
        try {
            cliente cliente = clienteService.buscarPorId(id);
            System.out.print("Novo Nome: ");
            cliente.setNome(scanner.nextLine());
            System.out.print("Novo Email: ");
            cliente.setEmail(scanner.nextLine());
            clienteService.atualizarCliente(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removerCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Cliente: ");
        String id = scanner.nextLine();
        try {
            clienteService.removerCliente(id);
            System.out.println("Cliente removido com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
