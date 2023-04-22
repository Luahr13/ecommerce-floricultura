package br.luahr.topicos1.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Produto extends DefaultEntity{
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
