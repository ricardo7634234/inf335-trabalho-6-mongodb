package br.unicamp.ic.inf335;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    // String de conexão padrão do MongoDB
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static MongoClient mongoClient;

    // Bloco estático para inicializar o cliente uma vez
    static {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            System.out.println("--- Conexao com o MongoDB estabelecida ---");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
        }
    }

    /**
     * Retorna uma instância do banco de dados especificado.
     * @param dbName O nome do banco de dados.
     * @return Uma instância de MongoDatabase.
     */
    public static MongoDatabase conectar(String dbName) {
        if (mongoClient == null) {
            throw new RuntimeException("MongoClient não inicializado!");
        }
        return mongoClient.getDatabase(dbName);
    }

    /**
     * Fecha a conexão com o cliente MongoDB.
     */
    public static void desconectar() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("--- Conexao com o MongoDB fechada ---");    
        }
    }
}