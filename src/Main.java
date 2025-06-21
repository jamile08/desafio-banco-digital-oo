import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;
import models.Cliente;
import models.Conta;
import models.ContaCorrente;
import models.ContaPoupanca;

public class Main {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");

		ContaCorrente cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();

		try {
			cc.transferir(-100, poupanca);
			cc.sacar(350); // Vai lançar a exceção
		} catch (SaldoInsuficienteException | ValorInvalidoException e) {
			System.out.println(e.getMessage());
		}

        System.out.println("limite de cheque: " + cc.getLimiteCheque());

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
