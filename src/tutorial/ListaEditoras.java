package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListaEditoras {

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			// Cria string SQL: Busca registros no BD
			String textoDoComando = "SELECT * FROM Editora;";

			// Prepara comando SQL: Conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Executa comando SQL: Conexao JDBC
			System.out.println("Executando comando...");
			ResultSet resultado = comando.executeQuery();

			// Mostra resultados encontrados
			System.out.println("Resultados encontrados: \n");
			while (resultado.next()) {
				System.out.printf("%d : %s - %s\n", resultado.getInt("id"), resultado.getString("nome"),
						resultado.getString("email"));
			}

			// Fecha conexao
			System.out.println("\nFechando conexao...");
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
