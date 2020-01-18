package desafiosefazadriano.controle;

import java.sql.SQLException;
import java.util.List;

import desafiosefazadriano.DAO.UsuarioDAO;
import desafiosefazadriano.modelo.Usuario;

public class UsuarioControle {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	// Metodo que chama o metodo de cadastrar no DAO e passa o usuario recebido
	public void cadastrar(Usuario usuario) throws SQLException {
		usuarioDAO.cadastrar(usuario);
	}

	// Metodo que chama o metodo de atualizar no DAO e passa o usuario recebido
	public void atualizar(Usuario usuario) throws SQLException {
		usuarioDAO.atualizar(usuario);
	}

	// Metodo que chama o metodo de deletar no DAO e passa o ID do usuario recebido
	public void deletar(Integer idUsuario) throws SQLException {
		usuarioDAO.deletar(idUsuario);
	}

	// Metodo que chama o metodo de listar no DAO para listar todos os usuarios cadastrado no banco.
	public List<Usuario> listar() throws SQLException {
		return usuarioDAO.listar();
	}

	// Metodo que chama o metodo de verificar se o usuario ja esta cadastrado no DAO e passa o usuario email informado pelo usuario
	public Boolean verificaSeUsuarioEstaCadastrado(String email) throws SQLException {
		return usuarioDAO.verificaSeUsuarioEstaCadastrado(email);
	}

	// Metodo que chama o metodo no DAO de verificar se email e senha digitados pelo usuario esta cadastrado no banco para entrar no sistema
	public Usuario verificaLogin(String email, String senha) throws SQLException {
		return usuarioDAO.verificaLogin(email, senha);
	}
	

}
