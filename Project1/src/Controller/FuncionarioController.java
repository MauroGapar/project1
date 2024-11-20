package controller;

import entity.funcionarios;
import service.FuncionarioService;

import java.util.List;
import java.util.Scanner;

public class FuncionarioController {
    private FuncionarioService funcionarioService;

    // Construtor para inicializar o serviço de funcionários
    public FuncionarioController() {
        this.funcionarioService = new FuncionarioService();
    }

    // Método principal para gerenciar funcionários
    public void gerenciarFuncionarios() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==== Gerenciamento de Funcionários ====");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Buscar Funcionário por ID");
            System.out.println("4. Atualizar Funcionário");
            System.out.println("5. Remover Funcionário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarFuncionario();
                case 2 -> listarFuncionarios();
                case 3 -> buscarFuncionarioPorId();
                case 4 -> atualizarFuncionario();
                case 5 -> removerFuncionario();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para adicionar um funcionário
    private void adicionarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        String id = scanner.nextLine();

        // Validação para evitar duplicidade de IDs
        if (funcionarioService.listarTodos().stream().anyMatch(f -> f.getId().equals(id))) {
            System.out.println("Erro: Funcionário com o mesmo ID já existe.");
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
        System.out.print("Data de Contratação (dd/mm/yyyy): ");
        String dataContratacao = scanner.nextLine();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        funcionarios funcionario = new funcionarios(id, nome, nif, email, telefone, endereco, dataContratacao, salario);
        funcionarioService.criarFuncionario(funcionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }

    // Método para listar todos os funcionários
    private void listarFuncionarios() {
        List<funcionarios> funcionarios = funcionarioService.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            System.out.println("Lista de Funcionários:");
            for (funcionarios funcionario : funcionarios) {
                System.out.println(funcionario.getId() + " - " + funcionario.getNome());
            }
        }
    }

    // Método para buscar um funcionário por ID
    private void buscarFuncionarioPorId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Funcionário: ");
        String id = scanner.nextLine();
        try {
            funcionarios funcionario = funcionarioService.buscarPorId(id);
            System.out.println("Funcionário encontrado:");
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Email: " + funcionario.getEmail());
            System.out.println("Data de Contratação: " + funcionario.getDataContratacao());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para atualizar um funcionário
    private void atualizarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Funcionário: ");
        String id = scanner.nextLine();
        try {
            funcionarios funcionario = funcionarioService.buscarPorId(id);
            System.out.print("Novo Nome: ");
            funcionario.setNome(scanner.nextLine());
            System.out.print("Novo Email: ");
            funcionario.setEmail(scanner.nextLine());
            funcionarioService.atualizarFuncionario(funcionario);
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para remover um funcionário
    private void removerFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID do Funcionário: ");
        String id = scanner.nextLine();
        try {
            funcionarioService.removerFuncionario(id);
            System.out.println("Funcionário removido com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}