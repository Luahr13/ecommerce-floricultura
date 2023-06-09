package br.luahr.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ItemCompra extends Produto {

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_flor", nullable = false)
    private Flor flor;

    public ItemCompra (Flor flor, Integer quantidade) {
        this.flor = flor;
        this.precoUnitario = flor.getValorUnidade();
        this.quantidade = quantidade;
    }

    public ItemCompra() {
        
    }

    public boolean contains(Flor flor) {

        if (this.flor.getId() == flor.getId())
            return true;
        
        else
            return false;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void updateQuantidade(Integer quantidade) {

        this.quantidade += quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double preco) {
        this.precoUnitario = preco;
    }

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

}
