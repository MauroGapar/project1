package controller;

import entity.pagamentos;
import service.PagamentosService;

import java.util.List;
import java.util.Scanner;

public class PagamentosController {
    private PagamentosService pagamentosService;

    // Construtor para inicializar o serviço de pagamentos
    public PagamentosController() {
        this.pagamentosService = new PagamentosService();
    }

    // Método principal para gerenciar pagamentos
    public void gerenciarPagamentos() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==== Gerenciamento de Pagamentos ====");
            System.out.println("1. Adicionar Pagamento");
            System.out.println("2. Listar Todos os Pagamentos");
            System.out.println("3. Listar Pagamentos por Método");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarPagamento();
                case 2 -> listarPagamentos();
                case 3 -> listarPagamentosPorMetodo();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para adicionar um pagamento
    private void adicionarPagamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Valor do Pagamento: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Data do Pagamento (yyyy-MM-dd): ");
        String data = scanner.nextLine();

        System.out.println("Método de Pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Transferência Bancária");
        System.out.println("3. Dinheiro");
        int metodoEscolhido = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        pagamentos.MetodoPagamento metodo = switch (metodoEscolhido) {
            case 1 -> Pagamentos.MetodoPagamento.CARTAO_CREDITO;
            case 2 -> Pagamentos.MetodoPagamento.TRANSFERENCIA_BANCARIA;
            case 3 -> Pagamentos.MetodoPagamento.DINHEIRO;
            default -> {
                System.out.println("Método inválido. Operação cancelada.");
                yield null;
            }
        };

        if (metodo == null) {
            return;
        }

        pagamentos pagamento = new pagamentos(valor, pagamentos.formatarData(data), metodo);
        pagamentosService.adicionarPagamento(pagamento);
        System.out.println("Pagamento adicionado com sucesso!");
    }

    // Método para listar todos os pagamentos
    private void listarPagamentos() {
        List<pagamentos> pagamentos = pagamentosService.listarTodos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento encontrado.");
        } else {
            System.out.println("Lista de Pagamentos:");
            for (pagamentos pagamento : pagamentos) {
                System.out.println("Valor: " + pagamento.getValor() +
                        ", Data: " + pagamento.getData() +
                        ", Método: " + pagamento.getMetodo());
            }
        }
    }

    // Método para listar pagamentos por método
    private void listarPagamentosPorMetodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o Método de Pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Transferência Bancária");
        System.out.println("3. Dinheiro");
        int metodoEscolhido = scanner.nextInt();

        pagamentos.MetodoPagamento metodo = switch (metodoEscolhido) {
            case 1 -> Pagamentos.MetodoPagamento.CARTAO_CREDITO;
            case 2 -> Pagamentos.MetodoPagamento.TRANSFERENCIA_BANCARIA;
            case 3 -> Pagamentos.MetodoPagamento.DINHEIRO;
            default -> {
                System.out.println("Método inválido.");
                yield null;
            }
        };

        if (metodo == null) {
            return;
        }

        List<pagamentos> pagamentosPorMetodo = pagamentosService.listarPorMetodo(metodo);
        if (pagamentosPorMetodo.isEmpty()) {
            System.out.println("Nenhum pagamento encontrado para o método selecionado.");
        } else {
            System.out.println("Pagamentos com o Método " + metodo + ":");
            for (pagamentos pagamento : pagamentosPorMetodo) {
                System.out.println("Valor: " + pagamento.getValor() +
                        ", Data: " + pagamento.getData() +
                        ", Método: " + pagamento.getMetodo());
            }
        }
    }
}