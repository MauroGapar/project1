
package Repository;
import entity.cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    private final String arquivoClientes = "clientes.csv"; // Nome do arquivo de persistência
    private List<cliente> clientes = new ArrayList<>(); // Simulação em memória

    public ClienteRepository() {
         carregarClientes();
    }
    
     // Método para salvar um cliente na memória e persistir no arquivo
    public void salvar(cliente cliente) {
        Optional<cliente> clienteExistente = buscarPorId(cliente.getId());
        if (clienteExistente.isPresent()) {
            System.out.println("Erro: Cliente com ID já existente.");
            return;
        }
        clientes.add(cliente);
        salvarClientesNoArquivo();
    }

    // Método para buscar um cliente pelo ID
    public Optional<cliente> buscarPorId(String id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
    }
    
     // Método para listar todos os clientes
    public List<cliente> listarTodos() {
        return new ArrayList<>(clientes); // Retorna uma cópia da lista para evitar alterações externas
    }

    // Método para atualizar um cliente
    public void atualizar(cliente clienteAtualizado) {
        Optional<cliente> clienteExistente = buscarPorId(clienteAtualizado.getId());
        if (clienteExistente.isPresent()) {
            cliente cliente = clienteExistente.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setTotalPedidos(clienteAtualizado.getTotalPedidos());
            cliente.setValorTotalGasto(clienteAtualizado.getValorTotalGasto());
            salvarClientesNoArquivo();
        } else {
            System.out.println("Erro: Cliente não encontrado para atualização.");
        }
    }

    // Método para remover um cliente pelo ID
    public void remover(String id) {
        Optional<cliente> clienteExistente = buscarPorId(id);
        if (clienteExistente.isPresent()) {
            clientes.remove(clienteExistente.get());
            salvarClientesNoArquivo();
        } else {
            System.out.println("Erro: Cliente não encontrado para remoção.");
        }
    }

    // Método para carregar clientes do arquivo
    private void carregarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoClientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 7) { // Certifique-se de que há 7 campos
                    cliente cliente = new cliente(
                            dados[0], // ID
                            dados[1], // Nome
                            dados[2], // NIF
                            dados[3], // Email
                            dados[4], // Telefone
                            dados[5], // Endereço
                            Integer.parseInt(dados[6]), // Total de Pedidos
                            Double.parseDouble(dados[7]) // Valor Total Gasto
                    );
                    clientes.add(cliente);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de clientes não encontrado. Será criado um novo arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os clientes: " + e.getMessage());
        }
    }

    // Método para salvar a lista de clientes no arquivo
    private void salvarClientesNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoClientes))) {
            for (cliente cliente : clientes) {
                bw.write(String.format("%s,%s,%s,%s,%s,%s,%d,%.2f\n",
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getNif(),
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        cliente.getEndereco(),
                        cliente.getTotalPedidos(),
                        cliente.getValorTotalGasto()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os clientes: " + e.getMessage());
        }
    }

   
}
