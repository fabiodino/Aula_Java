package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AlteraLivro {

	private static Scanner entrada;

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			entrada = new Scanner(System.in);

			System.out.println("Digite o id do livro que irá alterar: ");
			int id = entrada.nextInt(); // gera erro no 1 nextline (pula ele)

			System.out.println("Digite o novo titulo do livro: ");
			String titulo = entrada.nextLine();

			System.out.println("Digite o novo preco do livro: ");
			double preco = entrada.nextDouble();

			// Cria string SQL: altera registro no BD (sanitize "?")
			String textoDoComando = "UPDATE Livro SET titulo = ?, preco = ? WHERE id = ?";

			// Prepara comando SQL: conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Recebe indice do parametro e o valor: (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setString(1, titulo);
			comando.setDouble(2, preco);
			comando.setInt(3, id);

			// Executa comando SQL
			System.out.println("Executando comando...");
			comando.executeUpdate();

			// Fecha conexao
			System.out.println("Fechando conexao...");
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
