import java.util.Scanner;

public class Cliente { //Abstração
    String agencia;
    int numeroConta;
    String nomeCliente;
    double saldo;
    String tipoConta;
    int opcao = 0;
    double transferencia = 0;

    Scanner scanner = new Scanner(System.in);

    void exibirFichaCliente() {
        System.out.println("********************** Banco DIO-Bank ***********************");
        System.out.println("Agência/Conta:                              " + agencia + "/" + numeroConta);
        System.out.println("Nome do Cliente:                           " + nomeCliente);
        System.out.println("Saldo Atual:                                 " + "R$" + saldo);
        System.out.println("Tipo de Conta:                            " + tipoConta);
        System.out.println("\n");
    }

    void exibirSaldo() {
        System.out.println("Seu saldo é: " + saldo);
    }

    void transferirValor() {
        System.out.println("Digite o valor a ser transferido: ");
        transferencia = scanner.nextDouble();
        if (saldo < transferencia) {
            System.out.println("Saldo Insuficiente, seu saldo é: " + saldo);
        } else {
            saldo = saldo - transferencia;
            System.out.println("Transferência realizada, seu saldo agora é: " + saldo);
        }
    }
    void receberValor() {
        System.out.println("Digite o valor a ser recebido: ");
        saldo += scanner.nextDouble();
        System.out.println("Transferência realizada, seu saldo agora é: " + saldo);
    }

    void chamaMenu(){
        //Interface Menu
        System.out.println("******************** Menu Opções ***********************");
        System.out.println("1- Consultar saldo");
        System.out.println("2- Receber Valor");
        System.out.println("3- Transferir Valor");
        System.out.println("4- Sair");
        System.out.println("Digite a opção Desejada: ");
        opcao = scanner.nextInt();
        System.out.println("***********************");

        //lAÇO DE REPETIÇÃO
        while (opcao != 4){
            if (opcao == 1){
                exibirSaldo();
            }
            else if (opcao == 2){
                receberValor();
            }
            else if (opcao == 3){
                transferirValor();
            }
            else if (opcao == 4) {
                System.out.println("Programa Encerrado, Até Logo!");
                break;
            }
            else {
                System.out.println("Opção inválida");
            }
        }
    }
}