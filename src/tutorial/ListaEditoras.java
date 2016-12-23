package tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListaEditoras {
	
	public static void main(String[] args) {
		
		// Cria conexao
		String stringDeConexao = "jdbc:mysql://localhost:3306/livraria";
		String usuario = "root";
		String senha = "123456";
		
		try {
			System.out.println("Abrindo conexao...");
			Connection conexao = DriverManager.getConnection(stringDeConexao, usuario, senha);
			
			// Busca registro no BD
			String textoDoComando = "SELECT * FROM Editora;";
			
			// Conexao JDBC prepara comando SQL
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			
			System.out.println("Executando comando...");
			ResultSet resultado = comando.executeQuery();
			
			System.out.println("Resultados encontrados: \n");
			while (resultado.next()) {
				System.out.printf("%d : %s - %s\n",
						resultado.getInt("id"),
						resultado.getString("nome"),
						resultado.getString("email"));				
			}
			
			System.out.println("\nFechando conexao...");
			conexao.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
