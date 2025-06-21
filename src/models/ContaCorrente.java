package models;

import enuns.TipoTransacao;
import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

import java.time.LocalDateTime;

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
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
		System.out.println(String.format("Limite de cheque: %.2f", this.limiteCheque));
		historicoTransacoes();
	}

    @Override
	public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException("O valor negativo de " + valor + " é inválido para realizar operação de saque.");
		if (valor <= this.saldo) {
			saldo -= valor;
			transacoes.add(new Transacao(valor, LocalDateTime.now(), TipoTransacao.SAQUE));
		} else if (valor <= this.saldo + limiteCheque) {
			double saque = valor;
			valor -= saldo;
			saldo = 0;
			limiteCheque -= valor;
			System.out.println("Limite de cheque disponível: " + limiteCheque);
			transacoes.add(new Transacao(saque, LocalDateTime.now(), TipoTransacao.SAQUE));
		} else {
			throw new SaldoInsuficienteException("Erro: Saldo insuficiente para realizar o saque de R$ " + valor);
		}

	}

}
