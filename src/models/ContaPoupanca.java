package models;

import enuns.TipoTransacao;
import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

import java.time.LocalDateTime;

public class ContaPoupanca extends Conta {

	private static final double TAXA_RENDIMENTO = 0.005; // Exemplo: 0,5% ao mês

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
		if (valor <= this.saldo){
			saldo -= valor;
			transacoes.add(new Transacao(valor, LocalDateTime.now(), TipoTransacao.SAQUE));
		} else{
			throw new SaldoInsuficienteException("Erro: Saldo insuficiente para realizar o saque de R$ " + valor);
		}
	}

	public void aplicarRendimento() {
		double rendimento  = saldo * TAXA_RENDIMENTO;
		this.saldo += rendimento;
		transacoes.add(new Transacao(rendimento, LocalDateTime.now(), TipoTransacao.RENDIMENTO));
	}
}
