package mongoproject;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoAdmin {
	private static MongoClient mongoclient;

	public static void main(String[] args) {
		MongoClient client = conectar();
		MongoDatabase db = basetest(client);
		
		listaProdutos(db);
		inserir(db, "Novo", "Teste", "100.0", "Nada");
		listaProdutos(db);
		alterar(db, "Novo", "150");
		listaProdutos(db);
		remover(db, "Novo");
		listaProdutos(db);
		
	}
	public static void alterar(MongoDatabase db, String nome, String valor) {
		MongoCollection<Document> colection = db.getCollection("produtos");
		Bson updates = Updates.combine(Updates.set("valor", valor));
		Document busca = new Document("nome", nome);
		colection.updateOne(busca, updates);
	}
	public static void remover(MongoDatabase db, String nome) {
		MongoCollection<Document> colection = db.getCollection("produtos");
		Document busca = new Document("nome", nome);
		colection.deleteOne(busca);
	}
	
	public static void inserir(MongoDatabase db, String nome, String descricao, String valor, String estado) {
		MongoCollection<Document> colection = db.getCollection("produtos");
		Document produto = new Document("nome", nome).append("descricao", descricao).append("valor", valor).append("estado", estado);
		colection.insertOne(produto);
	}
	
	public static MongoClient conectar() {
		System.out.println("Conectando com o Mongo");
		MongoClient client = MongoClients.create("mongodb://localhost");
		
		return client;
	}
	
	public static MongoDatabase basetest(MongoClient client) {
		System.out.println("Conectando a base test");
		MongoDatabase db = client.getDatabase("test");
		return db;
	}
	public static void listaColecao(MongoDatabase db) {
		System.out.println("Lista as colecoes da base");
		Iterable<Document> collections = db.listCollections();
		for (Document col: collections) {
			System.out.println(col.get("name"));
		}
	}
	public static void listaProdutos(MongoDatabase db) {
		MongoCollection<Document> colection = db.getCollection("produtos");
		
		System.out.println("Imprimindo Produtos");
		Iterable<Document> produtos = colection.find();
		for (Document produto: produtos) {
			String nome = produto.getString("nome");
			String descricao = produto.getString("descricao");
			String valor = produto.getString("valor");
			String estado = produto.getString("estado");
			System.out.println(nome + " -- " + descricao + " -- " + valor + " -- " + estado);
		}
	}
	
	}
	

