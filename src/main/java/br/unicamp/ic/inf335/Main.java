package br.unicamp.ic.inf335;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("--- Lista Original de Produtos ---");
        produtoDAO.listaProdutos();

        Produto novProduto = new Produto("7","Prod 7", "Bla Bla", new BigDecimal("150.75"), "Estado de novo");
        produtoDAO.insereProduto(novProduto);

        System.out.println("--- Lista de Produtos pós Inserção ---");
        produtoDAO.listaProdutos();

        produtoDAO.alteraValorProduto("7", new BigDecimal("120.00"));

        System.out.println("--- Lista de Produtos pós Alteração ---");
        produtoDAO.listaProdutos();

        produtoDAO.apagaProduto("7");

        System.out.println("--- Lista de Produtos pós Exclusão ---");
        produtoDAO.listaProdutos();

        MongoConnection.desconectar();
    }

}
