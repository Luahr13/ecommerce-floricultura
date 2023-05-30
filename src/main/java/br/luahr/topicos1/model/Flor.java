package br.luahr.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Flor extends Produto{
    private String corPetalas;
    private Double alturaCaule;

    @Enumerated(EnumType.STRING)
    private TipoFlor tipoFlor;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    public String getCorPetalas() {
        return corPetalas;
    }

    public void setCorPetalas(String corPetalas) {
        this.corPetalas = corPetalas;
    }

    public Double getAlturaCaule() {
        return alturaCaule;
    }

    public void setAlturaCaule(Double alturaCaule) {
        this.alturaCaule = alturaCaule;
    }

    public TipoFlor getTipoFlor() {
        return tipoFlor;
    }

    public void setTipoFlor(TipoFlor tipoFlor) {
        this.tipoFlor = tipoFlor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
