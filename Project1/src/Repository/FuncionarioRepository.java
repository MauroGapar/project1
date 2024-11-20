/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import entity.funcionarios;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {
     private final String arquivoFuncionarios = "funcionarios.csv"; // Nome do arquivo de persistência
    private List<funcionarios> funcionarios = new ArrayList<>(); // Simulação em memória
    
    
     // Construtor que carrega os dados do arquivo ao iniciar o repositório
    public FuncionarioRepository() {
        carregarFuncionarios();
    }
    
     // Método para salvar um funcionário na memória e persistir no arquivo
    public void salvar(funcionarios funcionario) {
        Optional<funcionarios> funcionarioExistente = buscarPorId(funcionario.getId());
        if (funcionarioExistente.isPresent()) {
            System.out.println("Erro: Funcionário com ID já existente.");
            return;
        }
        funcionarios.add(funcionario);
        salvarFuncionariosNoArquivo();
    }
    
     // Método para buscar um funcionário pelo ID
    public Optional<funcionarios> buscarPorId(String id) {
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getId().equals(id))
                .findFirst();
    }

    // Método para listar todos os funcionários
    public List<funcionarios> listarTodos() {
        return new ArrayList<>(funcionarios); // Retorna uma cópia da lista para evitar alterações externas
    }

    // Método para atualizar um funcionário
    public void atualizar(funcionarios funcionarioAtualizado) {
        Optional<funcionarios> funcionarioExistente = buscarPorId(funcionarioAtualizado.getId());
        if (funcionarioExistente.isPresent()) {
            funcionarios funcionario = funcionarioExistente.get();
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setEmail(funcionarioAtualizado.getEmail());
            funcionario.setEndereco(funcionarioAtualizado.getEndereco());
            funcionario.setTelefone(funcionarioAtualizado.getTelefone());
            funcionario.setDataContratacao(funcionarioAtualizado.getDataContratacao());
            funcionario.setSalario(funcionarioAtualizado.getSalario());
            salvarFuncionariosNoArquivo();
        } else {
            System.out.println("Erro: Funcionário não encontrado para atualização.");
        }
    }

    // Método para remover um funcionário pelo ID
    public void remover(String id) {
        Optional<funcionarios> funcionarioExistente = buscarPorId(id);
        if (funcionarioExistente.isPresent()) {
            funcionarios.remove(funcionarioExistente.get());
            salvarFuncionariosNoArquivo();
        } else {
            System.out.println("Erro: Funcionário não encontrado para remoção.");
        }
    }

    // Método para carregar funcionários do arquivo
    private void carregarFuncionarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoFuncionarios))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 7) { // Certifique-se de que há 7 campos
                    funcionarios funcionario = new funcionarios(
                            dados[0], // ID
                            dados[1], // Nome
                            dados[2], // NIF
                            dados[3], // Email
                            dados[4], // Telefone
                            dados[5], // Endereço
                            dados[6], // Data de Contratação
                            Double.parseDouble(dados[7]) // Salário
                    );
                    funcionarios.add(funcionario);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de funcionários não encontrado. Será criado um novo arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os funcionários: " + e.getMessage());
        }
    }

    // Método para salvar a lista de funcionários no arquivo
    private void salvarFuncionariosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoFuncionarios))) {
            for (funcionarios funcionario : funcionarios) {
                bw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%.2f\n",
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getNif(),
                        funcionario.getEmail(),
                        funcionario.getTelefone(),
                        funcionario.getEndereco(),
                        funcionario.getDataContratacao(),
                        funcionario.getSalario()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os funcionários: " + e.getMessage());
        }
    }

}
