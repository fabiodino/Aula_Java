package tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsereLivro {
	
	public static void main (String[] args) {
		
		// Cria conexao
		String stringDeConexao = "jdbc:mysql://localhost:3306/livraria";
		String usuario = "root";
		String senha = "123456";
		
		// Obtem input do usuario
		Scanner entrada = new Scanner(System.in);
		
		try {
			System.out.println("Abrindo conexao...");
			Connection conexao = DriverManager.getConnection(stringDeConexao, usuario, senha);
			
			System.out.println("Digite o nome do livro: ");
			String titulo = entrada.nextLine();
			
			System.out.println("Digite o valor do livro: ");
			double preco = entrada.nextDouble();
			
			System.out.println("Digite o id da editora: ");
			int editora_id = entrada.nextInt();
			
			// Insere registro no BD (sanitize - "?")
			String textoDoComando = "INSERT INTO Livro (titulo, preco, editora_id)" + "VALUES (?, ?, ?)";
			
			// Conexao JDBC prepara comando SQL
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			// Recebe indice do parametro e o valor (sanitize - "limpa" valores enviados (sql injection)) 
			comando.setString(1, titulo);
			comando.setDouble(2, preco);
			comando.setInt(3, editora_id);
			
			System.out.println("Executando comando...");
			comando.execute();
			
			System.out.println("Fechando conexao...");
			comando.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
