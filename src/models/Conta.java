package models;

import enuns.TipoTransacao;
import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;
import interfaces.IConta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected List<Transacao> transacoes;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		transacoes = new ArrayList<>();
	}

	@Override
	public void depositar(double valor) throws ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException("O valor negativo de " + valor + " é inválido para realizar operação de saque.");
		saldo += valor;
		transacoes.add(new Transacao(valor, LocalDateTime.now(), TipoTransacao.DEPOSITO));
	}

	@Override
	public void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException, ValorInvalidoException {
		this.sacar(valor);
		contaDestino.depositar(valor);
		transacoes.add(new Transacao(valor, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA));
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	public void historicoTransacoes() {
		System.out.println("Histórico de transações da conta " + this.numero);
		transacoes.forEach(System.out::println);
	}
}
