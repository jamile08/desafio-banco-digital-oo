package models;

import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

public class ContaCorrente extends Conta {

	private double limiteCheque = 200.00;

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

	public double getLimiteCheque() {
		return limiteCheque;
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato models.Conta Corrente ===");
		super.imprimirInfosComuns();
	}

    @Override
	public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException("O valor negativo de " + valor + " é inválido para realizar operação de saque.");
		if (valor <= this.saldo)
			saldo -= valor;
		else if (valor <= this.saldo + limiteCheque) {
			valor -= saldo;
			saldo = 0;
			limiteCheque -= valor;
		} else {
			throw new SaldoInsuficienteException("Erro: Saldo insuficiente para realizar o saque de R$ " + valor);
		}

	}



}
