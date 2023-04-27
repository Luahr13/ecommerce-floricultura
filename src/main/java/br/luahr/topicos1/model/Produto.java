package br.luahr.topicos1.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;

@MappedSuperclass
@PrimaryKeyJoinColumn(name = "id")
public class Produto extends DefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;
    private String descricao;
    private Float valorUnidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(Float valorUnidade) {
        this.valorUnidade = valorUnidade;
    }
}
