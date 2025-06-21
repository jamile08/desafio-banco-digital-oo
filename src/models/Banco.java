package models;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private final List<Conta> contas;

	public Banco() {
		this.contas = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void listarClientes() {
		System.out.println("Clientes do banco " + this.nome);

		for (Conta conta : contas) {
			System.out.println(conta.cliente.getNome());
		}
	}

	public Conta encontrarContaPorNumero(int numero) {
		for (Conta conta : contas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

	public void adicionarConta(Conta conta) {
		this.contas.add(conta);
	}
}
