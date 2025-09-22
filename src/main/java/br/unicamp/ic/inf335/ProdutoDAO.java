package br.unicamp.ic.inf335;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProdutoDAO {

    private final MongoCollection<Document> collection;

    public ProdutoDAO() {
        MongoDatabase database = MongoConnection.conectar("lojadb");
        this.collection = database.getCollection("produtos");
    }

    /**
     * Converte um objeto Produto em um Documento BSON.
     * Usaremos o 'idProduto' como o '_id' do documento.
     */
    private Document toDocument(Produto produto) {
        return new Document("_id", produto.getIdProduto())
                .append("nome", produto.getNome())
                .append("descricao", produto.getDescricao())
                .append("valor", produto.getValor())
                .append("estado", produto.getEstado());
    }

    /**
     * Converte um Documento BSON em um objeto Produto.
     */
    private Produto fromDocument(Document doc) {
        Produto produto = new Produto();
        produto.setIdProduto(doc.getString("_id"));
        produto.setNome(doc.getString("nome"));
        produto.setDescricao(doc.getString("descricao"));
        produto.setValor(doc.get("valor") != null ? new java.math.BigDecimal(doc.get("valor").toString()) : null);
        produto.setEstado(doc.getString("estado"));
        return produto;
    }

    /**
     * Cria um novo produto no banco de dados.
     */
    public void insereProduto(Produto produto) {
        try {
            Document doc = toDocument(produto);
            collection.insertOne(doc);
            System.out.println("Produto criado com sucesso: " + produto.getNome());
        } catch (Exception e) {
            System.err.println("Erro ao criar produto no MongoDB: " + e.getMessage());
        }
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public List<Produto> listaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        for (Document doc : collection.find()) {
            System.out.println(fromDocument(doc));
        }
        return produtos;
    }

    /**
     * Atualiza as informações de um produto existente.
     */
    public void alteraValorProduto(String idProduto, BigDecimal novoValor) {
        Document filter = new Document("_id", idProduto);

        Document updateDoc = new Document("$set", new Document()
                .append("valor", novoValor));

        try {
            UpdateResult result = collection.updateOne(filter, updateDoc);
            if (result.getModifiedCount() > 0) {
                System.out.println("Produto atualizado com sucesso para o código: " + idProduto);
            } else {
                System.out.println("Nenhum produto encontrado com o código: " + idProduto);
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar produto no MongoDB: " + e.getMessage());
        }
    }

    /**
     * Deleta um produto pelo seu código.
     */
    public void apagaProduto(String idProduto) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("_id", idProduto));
            if (result.getDeletedCount() > 0) {
                System.out.println("Produto com código " + idProduto + " foi deletado.");
            } else {
                System.out.println("Nenhum produto encontrado com o código: " + idProduto);
            }
        } catch (Exception e) {
            System.err.println("Erro ao apagar produto no MongoDB: " + e.getMessage());
        }
    }
}