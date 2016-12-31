package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RemoveLivro {

	private static Scanner entrada;

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			// Recebe input do usuario
			entrada = new Scanner(System.in);

			System.out.println("Digite o id do livro para remover: ");
			int id = entrada.nextInt();

			// Cria string SQL: Remove registro do BD (sanitize "?")
			String textoDoComando = "DELETE FROM Livro WHERE id = ?";

			// Prepara comando SQL: conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Recebe indice do parametro e o valor: (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setInt(1, id);

			// Executa comando SQL: Conexao JDBC
			System.out.println("Executando comando...");
			comando.execute();

			// Fecha conexao
			System.out.println("Fechando conexao...");
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
