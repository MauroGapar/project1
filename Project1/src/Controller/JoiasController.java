package controller;

import entity.joias;
import entity.colares;
import entity.aneis;
import entity.brincos;
import service.JoiasService;

import java.util.List;
import java.util.Scanner;

public class JoiasController {
    private JoiasService joiasService;

    // Construtor para inicializar o serviço de joias
    public JoiasController() {
        this.joiasService = new JoiasService();
    }

    // Método principal para gerenciar joias
    public void gerenciarJoias() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("==== Gerenciamento de Joias ====");
            System.out.println("1. Adicionar Joia");
            System.out.println("2. Listar Todas as Joias");
            System.out.println("3. Listar Joias por Tipo");
            System.out.println("4. Remover Joia");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> adicionarJoia();
                case 2 -> listarJoias();
                case 3 -> listarJoiasPorTipo();
                case 4 -> removerJoia();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para adicionar uma nova joia
    private void adicionarJoia() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de joia:");
        System.out.println("1. Colar");
        System.out.println("2. Anel");
        System.out.println("3. Brinco");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        System.out.print("ID: ");
        String id = scanner.nextLine();

        // Verificar se o ID já existe
        if (joiasService.listarTodas().stream().anyMatch(j -> j.getId().equals(id))) {
            System.out.println("Erro: Joia com o mesmo ID já existe.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Material: ");
        String material = scanner.nextLine();
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade em Estoque: ");
        int estoque = scanner.nextInt();

        joias joia = null;
        switch (tipo) {
            case 1 -> joia = new Colares(id, nome, "Colar", material, peso, preco, estoque);
            case 2 -> joia = new Aneis(id, nome, "Anel", material, peso, preco, estoque);
            case 3 -> joia = new Brincos(id, nome, "Brinco", material, peso, preco, estoque);
            default -> {
                System.out.println("Tipo inválido. Operação cancelada.");
                return;
            }
        }

        joiasService.adicionarJoia(joia);
        System.out.println("Joia adicionada com sucesso!");
    }

    // Método para listar todas as joias
    private void listarJoias() {
        List<joias> joias = joiasService.listarTodas();
        if (joias.isEmpty()) {
            System.out.println("Nenhuma joia encontrada.");
        } else {
            System.out.println("Lista de Joias:");
            for (joias joia : joias) {
                System.out.println(joia.getId() + " - " + joia.getNome() + " (" + joia.getTipo() + ")");
            }
        }
    }

    // Método para listar joias por tipo
    private void listarJoiasPorTipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de joia:");
        System.out.println("1. Colares");
        System.out.println("2. Anéis");
        System.out.println("3. Brincos");
        int tipo = scanner.nextInt();

        Class<? extends Joias> classe = switch (tipo) {
            case 1 -> Colares.class;
            case 2 -> Aneis.class;
            case 3 -> Brincos.class;
            default -> null;
        };

        if (classe == null) {
            System.out.println("Tipo inválido.");
            return;
        }

        List<joias> joiasPorTipo = joiasService.listarPorTipo(classe);
        if (joiasPorTipo.isEmpty()) {
            System.out.println("Nenhuma joia do tipo selecionado encontrada.");
        } else {
            System.out.println("Lista de Joias do Tipo Selecionado:");
            for (joias joia : joiasPorTipo) {
                System.out.println(joia.getId() + " - " + joia.getNome() + " (" + joia.getTipo() + ")");
            }
        }
    }

    // Método para remover uma joia
    private void removerJoia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID da Joia: ");
        String id = scanner.nextLine();
        try {
            joiasService.removerJoia(id);
            System.out.println("Joia removida com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
