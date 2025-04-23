import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {
        /* Afazeres:
        Conhecer e Importar a classe scanner
        //Exibir as mensagens para o nosso usuário
        //Obter pela scanner os valores digitados no terminal
        Exibir a mensagem conta criada com sucesso
        */

        //Instancia a classe Cliente para criar um novo cliente
        Scanner scanner = new Scanner(System.in);
        Cliente cliente1 = new Cliente();

        System.out.println("Bem-vindo ao DIO-Bank!😁\n");

        //Definição de variáveis para armazenar informações posteriormente
        double transferencia = 0;
        int opcao = 0;
        cliente1.tipoConta = "Conta Corrente"; // Inicializa a variável tipoConta com um valor padrão

        //Obtenção de dados do cliente
        System.out.println("Por favor, digite o seu nome: ");
        cliente1.nomeCliente = scanner.nextLine();

        System.out.println("Por favor, digite o número da Agência: ");
        cliente1.agencia = scanner.nextLine();

        System.out.println("Por favor, digite o número da Conta: ");
        cliente1.numeroConta = scanner.nextInt();

        System.out.println("Por favor, digite o saldo: ");
        cliente1.saldo = scanner.nextDouble();

        //definição tipo de conta
        System.out.println("Você prefere que seja conta corrente ou conta poupança?: ");
        System.out.println("Digite 1 para corrente ou 2 para poupança: ");
        opcao = scanner.nextInt();
        if (opcao == 1){
            cliente1.tipoConta = "Conta Corrente";
            System.out.println("Você escolheu conta corrente.");
        }
        else if (opcao == 2){
            cliente1.tipoConta = "Conta Poupança";
            System.out.println("Você escolheu conta poupança.");
        }
        else {
            System.out.println("Opção inválida, conta corrente será criada por padrão.");
        }
        System.out.println("Olá " + cliente1.nomeCliente + ", Seja muito bem vindo, sua agência é " + cliente1.agencia + ", " + cliente1.tipoConta + " Número: " + cliente1.numeroConta + " e seu saldo R$" + cliente1.saldo + " já está disponível para saque.");
        System.out.println("Confira nosso menu de opções: \n");

        //Menu Opções
        cliente1.chamaMenu();

    }
}
