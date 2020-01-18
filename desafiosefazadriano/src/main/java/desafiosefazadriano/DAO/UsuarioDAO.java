package desafiosefazadriano.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class UsuarioDAO {

	// Metodo que cadastra o usuario no banco
	public void cadastrar(Usuario user) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

	}

	// Metodo que atualiza os dados do usuario no banco atraves do ID do usuario
	public void atualizar(Usuario user) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE ID_Usuario = ?";

			stmt = conexao.prepareStatement(sql);

			stmt.setInt(4, user.getId());
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

	}

	// Metodo que busca todos os usuarios cadastrado no banco
	public List<Usuario> listar() throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		List<Usuario> usuarios = new ArrayList<>();

		try {
			String sql = "SELECT * FROM usuario;";

			stmt = conexao.prepareStatement(sql);
			ResultSet resultSet = null;
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("ID_Usuario"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

		return usuarios;

	}

	

	// Metodo pra deletar um usuario no banco pelo seu ID
	public void deletar(Integer idUsuario) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql = "DELETE FROM usuario WHERE ID_Usuario = ?";

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}
	}

	// Metodo pra verificar se usuario possui cadastro no banco atraves do email
	public boolean verificaSeUsuarioEstaCadastrado(String email) throws SQLException {

		boolean cadastrado = false;

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT email FROM usuario WHERE email = '" + email + "' ";

			ResultSet resultSet = null;

			stmt = conexao.prepareStatement(sql);

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				cadastrado = true;
			}

			JDBC.fecharConexao();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

		return cadastrado;
	}

	/*
	 * Metodo pra verificar se email e senha informados na hora do login existe no banco. 
	 * Se sim, retorna o usuario com ID, nome, email e senha dele.
	 * Se não, retorna um usuario com id -1, o email que ele digitou e a senha.
	 */
	public Usuario verificaLogin(String email, String senha) throws SQLException {

		boolean emailValido = false;
		boolean senhaValida = false;

		Usuario usuario = new Usuario();

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql1 = "SELECT * FROM usuario WHERE email = '" + email + "' AND BINARY Senha = '" + senha + "'";

			ResultSet resultSet = null;

			stmt = conexao.prepareStatement(sql1);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				if (resultSet.getString("email").equals(email) && resultSet.getString("senha").equals(senha)) {
					emailValido = true;
					senhaValida = true;
					usuario.setId(resultSet.getInt("ID_Usuario"));
					usuario.setNome(resultSet.getString("nome"));
					usuario.setEmail(resultSet.getString("email"));
					usuario.setSenha(resultSet.getString("senha"));
				}
			}

			if (emailValido == true && senhaValida == true) {

				return usuario;

			} else {

				usuario.setId(-1);
				usuario.setEmail(email);
				usuario.setSenha(senha);

				return usuario;
			}

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

	}

}
