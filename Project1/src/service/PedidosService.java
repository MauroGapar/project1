package service;

import entity.Pedido;
import Repository.PedidosRepository;

import java.util.Date;
import java.util.List;

public class PedidosService {
    private PedidosRepository pedidosRepository;

    public PedidosService() {
        this.pedidosRepository = new PedidosRepository();
    }

    // Criar pedido
    public void criarPedido(Pedido pedido) {
        pedidosRepository.salvar(pedido);
    }

    // Buscar pedido por ID
    public Pedido buscarPorId(String id) {
        return pedidosRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    // Listar todos os pedidos
    public List<Pedido> listarTodos() {
        return pedidosRepository.listarTodos();
    }

    // Listar pedidos por status
    public List<Pedido> listarPorStatus(Pedido.StatusPedido status) {
        return pedidosRepository.listarPorStatus(status);
    }

    // Atualizar status de um pedido
    public void atualizarStatus(String id, Pedido.StatusPedido novoStatus) {
        pedidosRepository.atualizarStatus(id, novoStatus);
    }

    // Remover pedido
    public void removerPedido(String id) {
        pedidosRepository.remover(id);
    }
}
