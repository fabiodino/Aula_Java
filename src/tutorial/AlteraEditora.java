package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AlteraEditora {

	private static Scanner entrada;

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			// Recebe input do usuario
			entrada = new Scanner(System.in);

			System.out.println("Digite o id da editora que irá alterar: ");
			int id = entrada.nextInt(); // gera erro no 1 nextline (pula ele)

			System.out.println("Digite o novo nome da editora: ");
			String nome = entrada.nextLine();

			System.out.println("Digite o novo email da editora: ");
			String email = entrada.nextLine();

			// Cria string SQL: altera registro no BD (sanitize "?")
			String textoDoComando = "UPDATE Editora SET nome = ?, email = ? WHERE id = ?";

			// Prepara comando SQL: conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Recebe indice do parametro e o valor: (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setString(1, nome);
			comando.setString(2, email);
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
