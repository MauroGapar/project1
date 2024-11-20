package service;

import entity.joias;
import Repository.JoiasRepository;

import java.util.List;

public class JoiasService {
    private JoiasRepository joiasRepository;

    public JoiasService() {
        this.joiasRepository = new JoiasRepository();
    }

    // Adicionar joia
    public void adicionarJoia(joias joia) {
        joiasRepository.salvar(joia);
    }

    // Buscar joia por ID
    public joias buscarPorId(String id) {
        return joiasRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Joia não encontrada"));
    }

    // Listar todas as joias
    public List<joias> listarTodas() {
        return joiasRepository.listarTodos();
    }

    // Listar joias por tipo
    public List<joias> listarPorTipo(Class<? extends joias> tipo) {
        return joiasRepository.listarPorTipo(tipo);
    }

    // Atualizar joia
    public void atualizarJoia(joias joia) {
        joiasRepository.atualizar(joia);
    }

    // Remover joia
    public void removerJoia(String id) {
        joiasRepository.remover(id);
    }
}

