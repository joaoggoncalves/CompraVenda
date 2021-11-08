package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@SuppressWarnings("serial")
@Entity
@Table(name = "Carro")
public class Carro extends AbstractEntity<Long> {

    @NotBlank
    @Column(nullable = false, length = 10)
    private String placa;

    @Column(nullable = true, length = 64)
    private String modelo;

    @NotBlank
    @Column(nullable = false, length = 32)
    private String chassi;

    @Column(nullable = true)
    private int ano;

    @Column(nullable = true)
    private int km;

    @Column(nullable = true, length = 256)
    private String descricaocarro;

    @Column(nullable = true, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    public void setDescricaocarro(String descricaocarro) {
        this.descricaocarro = descricaocarro;
    }
     public void setAno(int ano) {
        this.ano = ano;
    }
    public void setKm(int km) {
        this.km = km;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getChassi() {
        return chassi;
    }
    public String getDescricaocarro() {
        return descricaocarro;
    }
    public int getAno() {
        return ano;
    }
    public int getKm() {
        return km;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
