import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class BancoServer {
    private static final int PORT = 8080; // Porta do servidor
    private static Map<Integer, Double> contas = new HashMap<>(); // Map para armazenar os dados de conta e saldo
    private static final String FILE_PATH = "contas.txt"; // Caminho do arquivo de dados

    public static void main(String[] args) {
        carregarContas(); // Carregar as contas do arquivo TXT

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    // Receber a operação do cliente
                    String operacao = input.readUTF();
                    int numeroConta = input.readInt();
                    double valor = input.readDouble();

                    String resposta = processarOperacao(operacao, numeroConta, valor);
                    output.writeUTF(resposta);
                    output.flush();
                } catch (IOException e) {
                    System.out.println("Erro ao processar a solicitação: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            salvarContas(); // Salvar as contas no arquivo TXT
        }
    }

    private static String processarOperacao(String operacao, int numeroConta, double valor) {
        switch (operacao) {
            case "deposito" -> {
                contas.put(numeroConta, contas.getOrDefault(numeroConta, 0.0) + valor);
                salvarContas();
                return "Depósito de R$ " + valor + " realizado com sucesso!";
            }

            case "saque" -> {
                double saldoAtual = contas.getOrDefault(numeroConta, 0.0);
                if (saldoAtual >= valor) {
                    contas.put(numeroConta, saldoAtual - valor);
                    salvarContas();
                    return "Saque de R$ " + valor + " realizado com sucesso!";
                } else {
                    return "Erro: saldo insuficiente.";
                }
            }
            default -> {
                return "Operação inválida.";
            }
        }
    }

    private static void carregarContas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int numeroConta = Integer.parseInt(dados[0]);
                double saldo = Double.parseDouble(dados[1]);
                contas.put(numeroConta, saldo);

            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar contas: " + e.getMessage());
        }
    }

    private static void salvarContas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<Integer, Double> entry : contas.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contas: " + e.getMessage());
        }
    }
}
