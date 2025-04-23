import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Map<String, Cliente> bancoDeClientes = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao DIO-Bank! 😁");

        while (true) {
            System.out.println("********************** Banco DIO-Bank ***********************");
            System.out.println("\n1 - Entrar em uma conta existente");
            System.out.println("2 - Criar nova conta");
            System.out.println("3 - Sair do sistema");
            System.out.print("Escolha uma opção: ");
            int menuInicial = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            if (menuInicial == 1) {
                Cliente clienteLogado = Cliente.login(bancoDeClientes, scanner);
                if (clienteLogado != null) {
                    clienteLogado.chamaMenu(scanner, bancoDeClientes);
                } else {
                    System.out.println("Conta não encontrada. Verifique os dados.");
                }
            }
            else if (menuInicial == 2) {
                Cliente novoCliente = Cliente.criarNovoCliente(scanner);
                String chave = Cliente.gerarChave(novoCliente);
                bancoDeClientes.put(chave, novoCliente);
                System.out.println("Conta criada com sucesso! Faça login para acessar.");
            } 
            else if (menuInicial == 3) {
                System.out.println("Até logo!");
                break;
            } 
            else {
                System.out.println("Opção inválida.");
            }
        }
    }
}
