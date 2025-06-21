package models;

import enuns.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private final Double valor;
    private final LocalDateTime dataTransacao;
    private final TipoTransacao tipoTransacao;

    public Transacao(Double valor, LocalDateTime dataTransacao, TipoTransacao tipoTransacao) {
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", dataTransacao=" + dataTransacao +
                ", tipoTransacao=" + tipoTransacao +
                '}';
    }
}
