package br.luahr.topicos1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends DefaultEntity{
    @Column(length = 50)
    private String nome;

    @Column(length = 14)
    private String cpf;

    @Column(length = 6)
    private String senha;
    
    private String email;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Telefone telefones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Endereco enderecos;

    //Relacionamento para lista de desejos
    @ManyToMany
    @JoinColumn(name = "id_flor")
    private List<Flor> flores = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefone getTelefones() {
        return telefones;
    }
    
    public void setTelefones(Telefone telefones) {
        this.telefones = telefones;
    }
    
    public Endereco getEnderecos() {
        return enderecos;
    }
    
    public void setEnderecos(Endereco enderecos) {
        this.enderecos = enderecos;
    }
    
    public List<Flor> getFlores() {
        return flores;
    }
    
    public void setFlores(List<Flor> flores) {
        this.flores = flores;
    }
}
