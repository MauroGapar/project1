/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import entity.joias;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JoiasRepository {
     private final String arquivoJoias = "joias.csv"; // Nome do arquivo de persistência
    private List<joias> joias = new ArrayList<>(); // Simulação em memória
    
    // Construtor que carrega os dados do arquivo ao iniciar o repositório
    public JoiasRepository() {
        carregarJoias();
    }
    // Método para salvar uma joia na memória e persistir no arquivo
    public void salvar(joias joia) {
        Optional<joias> joiaExistente = buscarPorId(joia.getId());
        if (joiaExistente.isPresent()) {
            System.out.println("Erro: Joia com ID já existente.");
            return;
        }
        joias.add(joia);
        salvarJoiasNoArquivo();
    }
    // Método para buscar uma joia pelo ID
    public Optional<joias> buscarPorId(String id) {
        return joias.stream()
                .filter(joia -> joia.getId().equals(id))
                .findFirst();
    }

    // Método para listar todas as joias
    public List<joias> listarTodos() {
        return new ArrayList<>(joias); // Retorna uma cópia da lista para evitar alterações externas
    }

    // Método para listar joias por tipo (colar, anel, brinco)
    public List<joias> listarPorTipo(Class<? extends joias> tipo) {
        return joias.stream()
                .filter(tipo::isInstance)
                .collect(Collectors.toList());
    }

    // Método para atualizar uma joia
    public void atualizar(joias joiaAtualizada) {
        Optional<joias> joiaExistente = buscarPorId(joiaAtualizada.getId());
        if (joiaExistente.isPresent()) {
            joias joia = joiaExistente.get();
            joia.setNome(joiaAtualizada.getNome());
            joia.setTipo(joiaAtualizada.getTipo());
            joia.setMaterial(joiaAtualizada.getMaterial());
            joia.setPeso(joiaAtualizada.getPeso());
            joia.setPreco(joiaAtualizada.getPreco());
            joia.setQuantidadeEstoque(joiaAtualizada.getQuantidadeEstoque());
            salvarJoiasNoArquivo();
        } else {
            System.out.println("Erro: Joia não encontrada para atualização.");
        }
    }

    // Método para remover uma joia pelo ID
    public void remover(String id) {
        Optional<joias> joiaExistente = buscarPorId(id);
        if (joiaExistente.isPresent()) {
            joias.remove(joiaExistente.get());
            salvarJoiasNoArquivo();
        } else {
            System.out.println("Erro: Joia não encontrada para remoção.");
        }
    }

    // Método para carregar joias do arquivo
    private void carregarJoias() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoJoias))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 7) { // Certifique-se de que há 7 campos
                    joias joia = new joias(
                            dados[0], // ID
                            dados[1], // Nome
                            dados[2], // Tipo
                            dados[3], // Material
                            Double.parseDouble(dados[4]), // Peso
                            Double.parseDouble(dados[5]), // Preço
                            Integer.parseInt(dados[6]) // Quantidade em Estoque
                    );
                    joias.add(joia);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de joias não encontrado. Será criado um novo arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar as joias: " + e.getMessage());
        }
    }

    // Método para salvar a lista de joias no arquivo
    private void salvarJoiasNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoJoias))) {
            for (joias joia : joias) {
                bw.write(String.format("%s,%s,%s,%s,%.2f,%.2f,%d\n",
                        joia.getId(),
                        joia.getNome(),
                        joia.getTipo(),
                        joia.getMaterial(),
                        joia.getPeso(),
                        joia.getPreco(),
                        joia.getQuantidadeEstoque()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as joias: " + e.getMessage());
        }
    }

}
