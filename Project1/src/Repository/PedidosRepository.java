/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import entity.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class PedidosRepository {
     private final String arquivoPedidos = "pedidos.csv"; // Nome do arquivo de persistência
    private List<Pedido> pedidos = new ArrayList<>(); // Lista para simulação em memória

    // Construtor que carrega os dados do arquivo ao iniciar o repositório
    public PedidosRepository() {
        carregarPedidos();
    }
    // Método para salvar um pedido na memória e persistir no arquivo
    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
        salvarPedidosNoArquivo();
    }
    
    // Método para buscar um pedido pelo ID
    public Optional<Pedido> buscarPorId(String id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Método para listar todos os pedidos
    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos); // Retorna uma cópia da lista para evitar alterações externas
    }

    // Método para listar pedidos por status
    public List<Pedido> listarPorStatus(Pedido.StatusPedido status) {
        return Pedido.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList()); // Use Collectors.toList() para Java < 16
    }

    // Método para atualizar o status de um pedido
    public void atualizarStatus(String id, Pedido.StatusPedido novoStatus) {
        Optional<Pedido> pedidoExistente = buscarPorId(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();
            pedido.setStatus(novoStatus);
            salvarPedidosNoArquivo();
        } else {
            System.out.println("Erro: Pedido não encontrado para atualização.");
        }
    }

    // Método para remover um pedido pelo ID
    public void remover(String id) {
        Optional<Pedido> pedidoExistente = buscarPorId(id);
        if (pedidoExistente.isPresent()) {
            pedidos.remove(pedidoExistente.get());
            salvarPedidosNoArquivo();
        } else {
            System.out.println("Erro: Pedido não encontrado para remoção.");
        }
    }

    // Método para carregar pedidos do arquivo
    private void carregarPedidos() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoPedidos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 4) { // Certifique-se de que há pelo menos 4 campos: id, clienteId, data, status
                    Pedido pedido = new Pedido(
                            dados[0], // ID do pedido
                            null, // Cliente (será necessário buscar ou simular um cliente associado)
                            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dados[2]) // Data do pedido
                    );
                    pedido.setStatus(pedidos.StatusPedido.valueOf(dados[3].toUpperCase())); // Status
                    pedidos.add(pedido);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pedidos não encontrado. Será criado um novo arquivo.");
        } catch (IOException | java.text.ParseException e) {
            System.out.println("Erro ao carregar os pedidos: " + e.getMessage());
        }
    }

    // Método para salvar a lista de pedidos no arquivo
    private void salvarPedidosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoPedidos))) {
            for (Pedido pedido : pedidos) {
                bw.write(String.format("%s,%s,%s,%s\n",
                        pedido.getId(),
                        pedido.getCliente() != null ? pedido.getCliente().getId() : "NULL", // ID do cliente associado
                        new java.text.SimpleDateFormat("yyyy-MM-dd").format(pedido.getData()),
                        pedido.getStatus().name()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os pedidos: " + e.getMessage());
        }
    }
    

    
}
