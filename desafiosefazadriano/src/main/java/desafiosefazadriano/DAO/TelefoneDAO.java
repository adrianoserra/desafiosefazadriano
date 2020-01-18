package desafiosefazadriano.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class TelefoneDAO {

	// Metodo pra cadastrar telefone do usuario no banco
	public void cadastrar(Usuario user, Telefone telefone) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
		String sql = "INSERT INTO telefone (ddd, numero, tipo, ID_Usuario) VALUES (?,?,?,?)";

		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, telefone.getDdd());
		stmt.setString(2, telefone.getNumero());
		stmt.setString(3, telefone.getTipo());
		stmt.setInt(4, user.getId());

		stmt.execute();
	
		
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

	}

	// Metodo que busca todos os telefones cadastrado do usuario no banco
	public List<Telefone> listar(Usuario user) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		List<Telefone> telefones = new ArrayList<>();

		try {
		String sql = "SELECT * FROM telefone WHERE ID_Usuario = " + user.getId();

		stmt = conexao.prepareStatement(sql);
		ResultSet resultSet = null;
		resultSet = stmt.executeQuery();	

		while (resultSet.next()) {
			Telefone telefone = new Telefone();
			telefone.setId(resultSet.getInt("ID_Telefone"));
			telefone.setDdd(resultSet.getInt("ddd"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));

			telefones.add(telefone);
		}
	
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

		return telefones;

	}

	// Metodo pra atualizar os dados do telefone ja cadastrado no banco atraves do ID do telefone
	public void atualizar(Telefone tel) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
		String sql = "UPDATE telefone SET ddd = ?, numero = ?, tipo = ? WHERE ID_Telefone = ?";

		stmt = conexao.prepareStatement(sql);

		stmt.setInt(4, tel.getId());
		stmt.setInt(1, tel.getDdd());
		stmt.setString(2, tel.getNumero());
		stmt.setString(3, tel.getTipo());
		stmt.execute();

		
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}

	}

	// Metodo pra deletar telefone selecionado do banco atraves do ID do telefone
	public void deletar(Integer idTelefone) throws SQLException {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
		String sql = "DELETE FROM telefone WHERE ID_Telefone = ?";

		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, idTelefone);
		stmt.execute();

		
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}
	}

	// Metodo pra verificar se DDD e numero que o usuario esta tentando cadastrar ja existe no banco
	public boolean verificaSeTelefoneExiste(Telefone tel) throws SQLException {

		boolean existeDDD = false;
		boolean existeNumero = false;

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		
		try {
		String sql1 = "SELECT ddd FROM telefone WHERE ddd = " + tel.getDdd();

		String sql2 = "SELECT numero FROM telefone WHERE numero = " + tel.getNumero();

		stmt = conexao.prepareStatement(sql1);
		ResultSet resultSet = null;
		resultSet = stmt.executeQuery();

		while (resultSet.next()) {
			existeDDD = true;
		}
		stmt.close();
		stmt = null;
		stmt = conexao.prepareStatement(sql2);
		resultSet = null;
		resultSet = stmt.executeQuery();
		
		while (resultSet.next()) {
			existeNumero = true;
		}	
		
		
		if(existeDDD == true && existeNumero == true) {		
			return true;		
		} else {
			return false;
		}
		
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			stmt.close();
			JDBC.fecharConexao();
		}
		
	
	}

}
