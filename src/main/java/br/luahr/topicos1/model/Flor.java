package br.luahr.topicos1.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
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
