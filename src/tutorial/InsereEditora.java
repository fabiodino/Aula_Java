package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsereEditora {

	private static Scanner entrada;

	public static void main(String[] args) {

		try {
			// Abre conexao
			System.out.println("Abrindo conexao...");
			Connection conexao = FabricaDeConexao.criaConexao();

			// Recebe input do usuario
			entrada = new Scanner(System.in);

			System.out.println("Digite o nome da editora: ");
			String nome = entrada.nextLine();

			System.out.println("Digite o email da editora: ");
			String email = entrada.nextLine();

			// Cria string SQL: Insere registro no BD (sanitize "?")
			String textoDoComando = "INSERT INTO Editora (nome, email)" + "VALUES (?, ?)";

			// Prepara comando SQL: Conexao JDBC
			PreparedStatement comando = conexao.prepareStatement(textoDoComando);

			// Recebe indice do parametro e o valor: (sanitize - "limpa" valores
			// enviados (sql injection))
			comando.setString(1, nome);
			comando.setString(2, email);

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
