package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Proposta")
public class Proposta extends AbstractEntity<Long> {

    @Column(nullable = true, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal valorproposta;

    @Column(length = 256)
    private String condicoes;

    @Column(length = 100)
    private String dataatual;

    @Column(length = 64)
    private String statusproposta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    public void setValorproposta(BigDecimal valorproposta){
        this.valorproposta = valorproposta;
    }

    public void setDataatual(String dataatual) {
        this.dataatual = dataatual;
    }

    public void setCondicoes(String condicoes) {
        this.condicoes = condicoes;
    }

    public void setStatusproposta(String statusproposta) {
        this.statusproposta = statusproposta;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public BigDecimal getValorproposta() {
        return valorproposta;
    }

    public String getDataatual() {
        return dataatual;
    }

    public String getCondicoes() {
        return condicoes;
    }

    public String getStatusproposta() {
        return statusproposta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Carro getCarro() {
        return carro;
    }
}
