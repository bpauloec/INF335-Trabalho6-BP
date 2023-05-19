package br.unicamp.ic.inf335;

import org.bson.Document;

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
	
	public static void main(String[] args) {
		Loja loja = new Loja();
		
		MongoDatabase lojaDB = loja.conecta("mongodb://localhost", "Loja");
		
		loja.listaProdutos(lojaDB);
		System.out.println();
	}

}
