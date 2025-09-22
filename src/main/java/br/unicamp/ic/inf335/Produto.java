package br.unicamp.ic.inf335;

import java.math.BigDecimal;

public class Produto {
    private String idProduto;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String estado;

    // Construtores
    public Produto() {}

    public Produto(String idProduto, String nome, String descricao, BigDecimal valor, String estado) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.estado = estado;
    }

    // Getters e Setters
    public String getIdProduto() { return idProduto; }
    public void setIdProduto(String idProduto) { this.idProduto = idProduto; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto='" + idProduto + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", estado='" + estado + '\'' +
                '}';
    }
}