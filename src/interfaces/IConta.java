package interfaces;

import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

public interface IConta {
	
	void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException;
	
	void depositar(double valor);
	
	void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException, ValorInvalidoException;
	
	void imprimirExtrato();
}
