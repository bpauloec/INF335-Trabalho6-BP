package br.unicamp.ic.inf335;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Loja {

	/***
	 * Conectar com Banco de Dados MongoDB
	 * @param endereco - Endereço para conexão mongodb://[nome do servidor]
	 * @param nomeBD - Nome do Banco de Dados
	 * @return Objeto de Banco de Dados MongoDB
	 */
	public MongoDatabase conecta(String endereco, String nomeBD){
		MongoClient client = null;
		MongoDatabase db = null;
		
		try{
			client = MongoClients.create(endereco);
			db = 	client.getDatabase(nomeBD);
		}
		catch(Exception e){
			System.out.println("Erro de conexão e selecionar banco: " + e);
			e.printStackTrace();
		}
		
		return db;
	}
	
	/***
	 * Listar Produtos do banco de dados Loja
	 * @param db - Objeto de Banco de Dados MongoDB
	 */
	public void listaProdutos(MongoDatabase db){
		try{
			MongoCollection<Document> collection = db.getCollection("Produto");
			
			Iterable<Document> produtos = collection.find();
			
			System.out.println("ID Produto\tNome\tDescricao\tValor(reias)\tEstado");
			for(Document produto : produtos) {
				int idProduto = produto.getInteger("idProduto");
				String nome = produto.getString("nome");
				String descricao = produto.getString("descricao");
				int valor = produto.getInteger("valor");
				String estado = produto.getString("estado");
				
				System.out.println(idProduto + "\t" + nome + "\t" + descricao + "\t" + valor + "\t" + estado);
			}
		}
		catch (Exception e){
			System.out.println("Erro ao Listar Produtos: " + e);
			e.printStackTrace();
		}
	}
	
	/***
	* Cria um BSON Document da Collation Produto
	* @param idProduto
	* @param nome
	* @param descricao
	* @param valor
	* @param estado
	*/
	public void insereProduto(MongoDatabase db, int idProduto, String nome, String descricao, int valor, String estado){
		Document produto = new Document("_id", new ObjectId());
		
		produto.append("idProduto", idProduto);
		produto.append("nome", nome);
		produto.append("descricao", descricao);
		produto.append("valor", valor);
		produto.append("estado", estado);
		
		MongoCollection<Document> produtos = db.getCollection("Produto");
		
		produtos.insertOne(produto);
	}
	
	public static void main(String[] args) {
		Loja loja = new Loja();
		
		MongoDatabase lojaDB = loja.conecta("mongodb://localhost", "Loja");
		
		//Lista de Produtos
		System.out.println("Lista Produtos");
		loja.listaProdutos(lojaDB);
		System.out.println();
		
		//Insere novo produto
		System.out.println("Inserindo no Produto (Prod7)");
		loja.insereProduto(lojaDB, 7, "Prod7", "Bla Bla", 500, "Bla Bla");
		System.out.println();
		
		//Lista os produtos da Loia
		System.out.println("Lista com Novo Produto");
		loja.listaProdutos(lojaDB);
		System.out.println();
	}

}
