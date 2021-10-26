package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;*/

    @NotBlank
    @Column(nullable = false, length = 64)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = true, length = 12)
    private String cpf;

    @Column(nullable = true, length = 64)
    private String nome;

    @Column(nullable = true, length = 11)
    private String telefone;

    @Column(nullable = true, length = 1)
    private String sexo;

    @Column(nullable = true, length = 20)
    private String datanasc;

    @Column(nullable = true, length = 256)
    private String descricao;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String role;

    /*public Long getId() {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf (String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public void setTelefone (String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setSexo (String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setDatanasc (String datanasc) {
        this.datanasc = datanasc;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setRole (String papel) {
        this.role = papel;
    }

    public String getRole() {
        return role;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}