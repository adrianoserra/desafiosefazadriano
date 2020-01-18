package desafiosefazadriano.util.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBC {

	// Preparando as variáveis de conexão
	private static Connection conexao;
	private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/desafiosefaz?useTimezone=true&serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String SENHA = "root";

	//Para abrir conexão com o SGBD
	public static Connection getConexao() {
		if (conexao == null) {

			try {
				// Carrega o driver especificado
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA); //Cria um objeto que possui acesso/conexão ao SGBD
			} catch (SQLException | ClassNotFoundException e) {
				throw new	RuntimeException(e);
			}
		}

		return conexao;
	}

	//Fecha/encerra conexão com o banco
	public static void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
				conexao = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
