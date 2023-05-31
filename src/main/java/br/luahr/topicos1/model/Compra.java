package br.luahr.topicos1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Compra extends DefaultEntity{
    
    private LocalDate dataCompra;

    private Double totalCompra;

    private Boolean ifConcluida;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "id_compra")
    private List<ItemCompra> itemCompra;

    public Compra (Cliente cliente) {

        this.ifConcluida = false;
        this.cliente = cliente;
        this.itemCompra = new ArrayList<>();
        this.totalCompra = 0.0;
    }

    public Compra() {
        
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataDaCompra) {
        this.dataCompra = dataDaCompra;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public void plusTotalCompra(Double totalCompra) {

        this.totalCompra += totalCompra;
    }

    public void minusTotalCompra(Double totalCompra) {

        this.totalCompra -= totalCompra;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(ItemCompra itemCompra) {
        this.itemCompra.add(itemCompra);
    }

    public Boolean getIfConcluida() {
        return ifConcluida;
    }

    public void setIfConcluida(Boolean ifConcluida) {
        this.ifConcluida = ifConcluida;
    }
}
