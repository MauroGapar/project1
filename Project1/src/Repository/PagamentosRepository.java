/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import entity.pagamentos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PagamentosRepository {
     private final String arquivoPagamentos = "pagamentos.csv"; // Nome do arquivo de persistência
    private List<pagamentos> pagamentos = new ArrayList<>(); // Simulação em memória

    // Construtor que carrega os dados do arquivo ao iniciar o repositório
    public PagamentosRepository() {
        carregarPagamentos();
    }

    // Método para salvar um pagamento na memória e persistir no arquivo
    public void salvar(pagamentos pagamento) {
        pagamentos.add(pagamento);
        salvarPagamentosNoArquivo();
    }

    // Método para buscar um pagamento pelo ID (considerando que o valor e a data identificam o pagamento)
    public Optional<pagamentos> buscarPorId(double valor, String data) {
        return pagamentos.stream()
                .filter(p -> p.getValor() == valor && p.getData().toString().equals(data))
                .findFirst();
    }

    // Método para listar todos os pagamentos
    public List<pagamentos> listarTodos() {
        return new ArrayList<>(pagamentos); // Retorna uma cópia da lista para evitar alterações externas
        
    }

    // Método para listar pagamentos por método de pagamento
    public List<pagamentos> listarPorMetodo(pagamentos.MetodoPagamento metodo) {
        return pagamentos.stream()
                .filter(p -> p.getMetodo() == metodo)
                .collect(Collectors.toList()); 
    }

    // Método para remover um pagamento
    public void remover(pagamentos pagamento) {
        if (pagamentos.remove(pagamento)) {
            salvarPagamentosNoArquivo();
        } else {
            System.out.println("Erro: Pagamento não encontrado para remoção.");
        }
    }

    // Método para carregar pagamentos do arquivo
  private void carregarPagamentos() {
    File arquivo = new File(arquivoPagamentos);
    if (!arquivo.exists()) {
        try {
            boolean criado = arquivo.createNewFile();
            if (criado) {
                System.out.println("Arquivo de pagamentos criado: " + arquivoPagamentos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo de pagamentos: " + e.getMessage());
            return;
        }
    }

    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length == 3) {
                try {
                    pagamentos.MetodoPagamento metodo = pagamentos.Metodopagamentos.valueOf(dados[2].toUpperCase());
                    pagamentos pagamento = new pagamentos(
                            Double.parseDouble(dados[0]), // Valor
                            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dados[1]), // Data
                            metodo // Método
                    );
                    pagamentos.add(pagamento);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: Método de pagamento inválido no arquivo: " + dados[2]);
                }
            }
        }
    } catch (IOException | java.text.ParseException e) {
        System.out.println("Erro ao carregar os pagamentos: " + e.getMessage());
    }
}

    // Método para salvar a lista de pagamentos no arquivo
    private void salvarPagamentosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoPagamentos))) {
            for (pagamentos pagamento : pagamentos) {
                bw.write(String.format("%.2f,%s,%s\n",
                        pagamento.getValor(),
                        new java.text.SimpleDateFormat("yyyy-MM-dd").format(pagamento.getData()),
                        pagamento.getMetodo()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os pagamentos: " + e.getMessage());
        }
    }
}
