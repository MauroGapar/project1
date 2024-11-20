package service;

import entity.pagamentos;
import Repository.PagamentosRepository;

import java.util.List;

public class PagamentosService {
    private PagamentosRepository pagamentosRepository;

    public PagamentosService() {
        this.pagamentosRepository = new PagamentosRepository();
    }

    // Adicionar pagamento
    public void adicionarPagamento(pagamentos pagamento) {
        pagamentosRepository.salvar(pagamento);
    }

    // Buscar pagamento por valor e data
    public pagamentos buscarPagamento(double valor, String data) {
        return pagamentosRepository.buscarPorId(valor, data)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    // Listar todos os pagamentos
    public List<pagamentos> listarTodos() {
        return pagamentosRepository.listarTodos();
    }

    // Listar pagamentos por método
    public List<pagamentos> listarPorMetodo(pagamentos.MetodoPagamento metodo) {
        return pagamentosRepository.listarPorMetodo(metodo);
    }

    // Remover pagamento
    public void removerPagamento(pagamentos pagamento) {
        pagamentosRepository.remover(pagamento);
    }
}
