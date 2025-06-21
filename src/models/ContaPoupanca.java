package models;

import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato models.Conta Poupan�a ===");
		super.imprimirInfosComuns();
	}

	@Override
	public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException("O valor negativo de " + valor + " é inválido para realizar operação de saque.");
		if (valor <= this.saldo)
			saldo -= valor;
		else
			throw new SaldoInsuficienteException("Erro: Saldo insuficiente para realizar o saque de R$ " + valor);
	}
}
