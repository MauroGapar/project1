package service;

import entity.cliente;
import Repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    // Criar cliente
    public void criarCliente(cliente cliente) {
        clienteRepository.salvar(cliente);
    }

    // Buscar cliente por ID
    public cliente buscarPorId(String id) {
        Optional<cliente> cliente = clienteRepository.buscarPorId(id);
        return cliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // Listar todos os clientes
    public List<cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    // Atualizar cliente
    public void atualizarCliente(cliente cliente) {
        clienteRepository.atualizar(cliente);
    }

    // Remover cliente
    public void removerCliente(String id) {
        clienteRepository.remover(id);
    }
}