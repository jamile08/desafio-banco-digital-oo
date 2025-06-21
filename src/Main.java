import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;
import models.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Banco banco = new Banco();
		int opcao;

		System.out.println("Informe o nome do banco: ");
		banco.setNome(scanner.nextLine());

		do {
			System.out.println("\n=== MENU BANCO DIGITAL "+ banco.getNome() +" ===");
			System.out.println("1. Criar Conta Corrente");
			System.out.println("2. Criar Conta Poupança");
			System.out.println("3. Depositar");
			System.out.println("4. Sacar");
			System.out.println("5. Transferir");
			System.out.println("6. Ver Extrato");
			System.out.println("7. Aplicar Rendimento (Poupança)");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					System.out.print("Nome do cliente: ");
					scanner.nextLine(); // Limpar buffer
					String nomeCorrente = scanner.nextLine();
					Cliente clienteCorrente = new Cliente();
					clienteCorrente.setNome(nomeCorrente);
					Conta contaCorrente = new ContaCorrente(clienteCorrente);
					banco.adicionarConta(contaCorrente);
					System.out.println("Conta Corrente criada com sucesso! Número da conta: " + contaCorrente.getNumero());
					break;

				case 2:
					System.out.print("Nome do cliente: ");
					scanner.nextLine();
					String nomePoupanca = scanner.nextLine();
					Cliente clientePoupanca = new Cliente();
					clientePoupanca.setNome(nomePoupanca);
					Conta contaPoupanca = new ContaPoupanca(clientePoupanca);
					banco.adicionarConta(contaPoupanca);
					System.out.println("Conta Poupança criada com sucesso! Número da conta: " + contaPoupanca.getNumero());
					break;

				case 3:
					System.out.print("Número da conta para depósito: ");
					int numeroDeposito = scanner.nextInt();
					Conta contaDep = banco.encontrarContaPorNumero(numeroDeposito);
					if (contaDep != null) {
						System.out.print("Valor do depósito: ");
						double valorDep = scanner.nextDouble();
                        try {
							contaDep.depositar(valorDep);
							System.out.println("Depósito realizado com sucesso!");
						} catch (ValorInvalidoException e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;

				case 4:
					System.out.print("Número da conta para saque: ");
					int numeroSaque = scanner.nextInt();
					Conta contaSaque = banco.encontrarContaPorNumero(numeroSaque);
					if (contaSaque != null) {
						System.out.print("Valor do saque: ");
						double valorSaque = scanner.nextDouble();
						try {
							contaSaque.sacar(valorSaque);
							System.out.println("Saque realizado com sucesso!");
						} catch (SaldoInsuficienteException | ValorInvalidoException e) {
							System.out.println(e.getMessage());
						}
                    } else {
						System.out.println("Conta não encontrada.");
					}
					break;

				case 5:
					System.out.print("Número da conta de origem: ");
					int origem = scanner.nextInt();
					System.out.print("Número da conta de destino: ");
					int destino = scanner.nextInt();
					Conta contaOrigem = banco.encontrarContaPorNumero(origem);
					Conta contaDestino = banco.encontrarContaPorNumero(destino);
					if (contaOrigem != null && contaDestino != null) {
						System.out.print("Valor da transferência: ");
						double valorTransfer = scanner.nextDouble();
						try {
							contaOrigem.transferir(valorTransfer, contaDestino);
							System.out.println("Transferência realizada com sucesso!");
						} catch (SaldoInsuficienteException | ValorInvalidoException e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("Conta(s) não encontrada(s).");
					}
					break;

				case 6:
					System.out.print("Número da conta para extrato: ");
					int numeroExtrato = scanner.nextInt();
					Conta contaExtrato = banco.encontrarContaPorNumero(numeroExtrato);
					if (contaExtrato != null) {
						contaExtrato.imprimirExtrato();
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;

				case 7:
					System.out.print("Número da conta poupança: ");
					int numeroPoupanca = scanner.nextInt();
					Conta conta = banco.encontrarContaPorNumero(numeroPoupanca);
					if (conta != null) {
						if (conta instanceof ContaPoupanca) {
							((ContaPoupanca) conta).aplicarRendimento();
							System.out.println("Rendimento aplicado com sucesso!");
							System.out.println("Novo saldo: " + conta.getSaldo());
						} else {
							System.out.println("Conta não é uma poupança.");
						}
					} else {
						System.out.println("Conta não encontrada.");
					}
					break;

				case 0:
					System.out.println("Encerrando o programa...");
					break;

				default:
					System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

		scanner.close();
	}

}
