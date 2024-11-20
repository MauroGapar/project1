package service;

import entity.funcionarios;
import Repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    // Criar funcionário
    public void criarFuncionario(funcionarios funcionario) {
        funcionarioRepository.salvar(funcionario);
    }

    // Buscar funcionário por ID
    public funcionarios buscarPorId(String id) {
        Optional<funcionarios> funcionario = funcionarioRepository.buscarPorId(id);
        return funcionario.orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    // Listar todos os funcionários
    public List<funcionarios> listarTodos() {
        return funcionarioRepository.listarTodos();
    }

    // Atualizar funcionário
    public void atualizarFuncionario(funcionarios funcionario) {
        funcionarioRepository.atualizar(funcionario);
    }

    // Remover funcionário
    public void removerFuncionario(String id) {
        funcionarioRepository.remover(id);
    }
}