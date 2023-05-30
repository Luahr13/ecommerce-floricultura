package br.luahr.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends DefaultEntity{
    private String nome;
    private String país;
    private String safra;
    private Float volume;

    public String getNome() {
        return nome;
    }

    public void setNome(String produtor) {
        this.nome = produtor;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public Float getVolume() {
        return volume;
    }
    
    public void setVolume(Float volume) {
        this.volume = volume;
    }
}
