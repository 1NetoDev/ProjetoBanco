import java.util.*;

public class Cliente {
    // ====================================================
    // 🔐 Atributos da Conta
    // ====================================================
    String nomeCliente;
    String agencia;
    int numeroConta;
    double saldo;
    String tipoConta;
    String senha;

    // Internos de menu
    int opcao = 0;
    double transferencia = 0;

    // ========================================================
    // 🛠️ MÉTODOS ESTÁTICOS: Criação, Login e geração de chave
    // ========================================================

    public static Cliente criarNovoCliente(Scanner scanner) {
        Cliente cliente = new Cliente();

        System.out.print("Digite seu nome: ");
        cliente.nomeCliente = scanner.nextLine();

        System.out.print("Digite o número da Agência: ");
        cliente.agencia = scanner.nextLine();

        System.out.print("Digite o número da Conta: ");
        cliente.numeroConta = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        System.out.print("Digite o saldo inicial: ");
        cliente.saldo = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer

        System.out.println("Digite 1 para Conta Corrente ou 2 para Poupança:");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        cliente.tipoConta = (tipo == 2) ? "Conta Poupança" : "Conta Corrente";

        System.out.print("Crie uma senha para sua conta: ");
        cliente.senha = scanner.nextLine();
        return cliente;
    }

    // ====================================================
    // 📋 Metodo de login LOOP externo
    // ====================================================

    public static Cliente login(Map<String, Cliente> banco, Scanner scanner) {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite sua agência: ");
        String agencia = scanner.nextLine();

        System.out.print("Digite sua conta: ");
        int conta = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer

        String chave = gerarChave(nome, conta, agencia);
        Cliente cliente = banco.get(chave);

        if (cliente != null) {
            System.out.print("Digite sua senha: ");
            String senhaDigitada = scanner.nextLine();
            if (!cliente.senha.equals(senhaDigitada)) {
                System.out.println("Senha incorreta! Acesso negado.");
                return null;
            }
            cliente.exibirFichaCliente();
        } else {
            System.out.println("Cliente não encontrado!");
        }
        return cliente;}

    public static String gerarChave(String nome, int conta, String agencia) {
        return nome.trim().toLowerCase() + "_" + conta + "_" + agencia.trim().toLowerCase();
    }

    public static String gerarChave(Cliente c) {
        return gerarChave(c.nomeCliente, c.numeroConta, c.agencia);
    }

    // ===========================================================
    // 🛠️ MÉTODOS de AÇÕES BANCÁRIAS: 
    // ==========================================================

    // 🧾 EXIBIÇÃO DE DADOS
    void exibirFichaCliente() {
        System.out.println("********************** Banco DIO-Bank ***********************");
        System.out.println("Agência/Conta:             " + agencia + "/" + numeroConta);
        System.out.println("Nome do Cliente:           " + nomeCliente);
        System.out.println("Saldo Atual:               R$" + saldo);
        System.out.println("Tipo de Conta:             " + tipoConta + "\n");
    }


    // ====================================================
    // 💸 OPERAÇÕES BANCÁRIAS
    // ====================================================

    // Exibir saldo
    void exibirSaldo() {
        System.out.println("Seu saldo é: R$" + saldo);
    }

    // Sacar valor
    void sacar(Scanner scanner) {
        System.out.print("Digite o valor a ser transferido: ");
        transferencia = scanner.nextDouble();
        if (saldo < transferencia) {
            System.out.println("Saldo Insuficiente. Seu saldo é: R$" + saldo);
        } else {
            saldo -= transferencia;
            System.out.println("Transferência realizada. Saldo atual: R$" + saldo);
        }
    }

    // Depositar valor
    void depositar(Scanner scanner) {
        System.out.print("Digite o valor a ser recebido: ");
        saldo += scanner.nextDouble();
        System.out.println("Valor recebido. Saldo atual: R$" + saldo);
    }

    //Transferencia de valores entre contas
    public void transferencia(Cliente destinatario, double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            destinatario.saldo += valor;
            System.out.println("Transferência realizada com sucesso!");
            System.out.println("Novo saldo de " + this.nomeCliente + ": R$" + this.saldo);
            System.out.println("Novo saldo de " + destinatario.nomeCliente + ": R$" + destinatario.saldo);
        } else {
            System.out.println("Transferência não realizada. Verifique o valor ou saldo insuficiente.");
        }
    }

    // ====================================================
    // 📋 MENU DE OPÇÕES LOOP INTERNO
    // ====================================================

    public void chamaMenu(Scanner scanner, Map<String, Cliente> banco) {
        System.out.println("Bem-vindo(a) " + nomeCliente + " ao DIO-Bank!");
        while (opcao != 5) {
            System.out.println("\n******************** Menu Opções ***********************");
            System.out.println("1- Consultar saldo");
            System.out.println("2- Depositar Valor");
            System.out.println("3- Sacar Valor");
            System.out.println("4- Transferir Valor");
            System.out.println("5- Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("*********************************************************");

            switch (opcao) {
                case 1 -> exibirSaldo();
                case 2 -> depositar(scanner);
                case 3 -> sacar(scanner);
                case 4 -> {
                    System.out.print("Digite o nome do destinatário: ");
                    String nomeDest = scanner.nextLine();
                    
                    System.out.print("Digite a agência do destinatário: ");
                    String agenciaDest = scanner.nextLine();

                    System.out.print("Digite a conta do destinatário: ");
                    int contaDest = scanner.nextInt();
                    scanner.nextLine();

                    String chaveDest = gerarChave(nomeDest, contaDest, agenciaDest);
                    Cliente destinatario = banco.get(chaveDest);

                    if (destinatario != null) {
                        System.out.print("Digite o valor a transferir: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();
                        transferencia(destinatario, valor);
                    } else {
                        System.out.println("Destinatário não encontrado!");
                    }
                }
                case 5 -> System.out.println("Sessão encerrada. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        }
        opcao = 0; // Reseta para nova sessão
    }
}
