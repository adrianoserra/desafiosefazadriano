package desafiosefazadriano.controle;

import java.sql.SQLException;
import java.util.List;

import desafiosefazadriano.DAO.TelefoneDAO;
import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;

public class TelefoneControle {
	
	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	
	// Metodo que chama o metodo de para cadastrar no DAO passando o usuario e o telefone
	public void cadastrar (Usuario usuario, Telefone telefone) throws SQLException {
		telefoneDAO.cadastrar(usuario, telefone);
	}
	
	// Metodo que chama o metodo de atualziar no DAO passando o telefone a ser atualizado
	public void atualizar (Telefone telefone) throws SQLException {
		telefoneDAO.atualizar(telefone);
	}
	
	// Metodo que chama o metodo de deletar no DAO passando o ID do telefone a ser deletado
	public void deletar (Integer idTelefone) throws SQLException {
		telefoneDAO.deletar(idTelefone);
	}
	
	// Metodo que chama o metodo de listar todos os telefone do usuario selecionado no DAO
	public List<Telefone> listar (Usuario usuario) throws SQLException{
		return telefoneDAO.listar(usuario);
	}
	
	// Metodo que chama o metodo de verificar se o telefone que o usuario esta tentando cadastrar ja existe no banco
	public boolean verificaSeTelefoneExiste (Telefone telefone) throws SQLException {
		return telefoneDAO.verificaSeTelefoneExiste(telefone);
	}

}
