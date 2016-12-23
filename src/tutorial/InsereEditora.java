package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsereEditora {

	public static void main(String[] args) {

		// Obtem input do usuario
		Scanner entrada = new Scanner(System.in);

		try {
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			System.out.println("Digite o nome da editora: ");
			String nome = entrada.nextLine();

			System.out.println("Digite o email da editora: ");
			String email = entrada.nextLine();

			// Insere registro no BD (sanitize "?")
			String textoDoComando = "INSERT INTO Editora (nome, email)" + "VALUES (?, ?)";

			// Conexao JDBC prepara comando SQL
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);
			// Recebe indice do parametro e o valor (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setString(1, nome);
			comando.setString(2, email);

			System.out.println("Executando comando...");
			comando.execute();

			System.out.println("Fechando conexao...");
			comando.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
