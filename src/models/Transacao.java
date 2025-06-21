package models;

import enuns.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private Double valor;
    private LocalDateTime dataTransacao;
    private TipoTransacao tipoTransacao;

    public Transacao(Double valor, LocalDateTime dataTransacao, TipoTransacao tipoTransacao) {
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
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
