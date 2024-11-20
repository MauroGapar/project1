package main;

import controller.ClienteController;
import controller.FuncionarioController;
import controller.JoiasController;
import controller.PedidosController;
import controller.PagamentosController;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // Inicializa os controladores
        ClienteController clienteController = new ClienteController();
        FuncionarioController funcionarioController = new FuncionarioController();
        JoiasController joiasController = new JoiasController();
        PedidosController pedidosController = new PedidosController();
        PagamentosController pagamentosController = new PagamentosController();

        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            // Exibe o menu principal para o usuário
            System.out.println("==== Sistema de Gestão de Joias ====");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Joias");
            System.out.println("4. Gerenciar Pedidos");
            System.out.println("5. Gerenciar Pagamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    clienteController.gerenciarClientes();
                    break;
                case 2:
                    funcionarioController.gerenciarFuncionarios();
                    break;
                case 3:
                    joiasController.gerenciarJoias();
                    break;
                case 4:
                    pedidosController.gerenciarPedidos();
                    break;
                case 5:
                    pagamentosController.gerenciarPagamentos();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0); // Continua executando até o usuário escolher sair

        scanner.close();
    }
}