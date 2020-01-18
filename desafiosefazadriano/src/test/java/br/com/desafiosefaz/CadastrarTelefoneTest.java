package br.com.desafiosefaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import desafiosefazadriano.controle.TelefoneControle;
import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class CadastrarTelefoneTest {

	Usuario usuario01;
	TelefoneControle telefoneControle = new TelefoneControle();
	UsuarioControle usuarioControle = new UsuarioControle();

	/**
	 * Testar funcionalidade de cadastrar telefone em um usuário já cadastrado no
	 * banco.
	 * 
	 */

	@Test
	public void testCadastrarTelefone() {

		/* ==================== Montagem do cenário ===================== */

		Telefone telefone01 = new Telefone(81, "999999999", "celular");
		boolean cadastrado = false;

		/* ================== Execução ================== */

		try {
			telefoneControle.cadastrar(usuario01, telefone01);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verificando no banco se o telefone realmente foi cadastrado para o usuário

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT ID_Usuario FROM telefone WHERE numero = '999999999' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				if (usuario01.getId() == resultSet.getInt("ID_Usuario")) {
					cadastrado = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				JDBC.fecharConexao();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		/* ================== Verificação ================== */

		assertTrue(cadastrado);

	}

	@Before
	public void cadastrarUsuarioNoBanco() {
		
		usuario01 = new Usuario("Adriano Serra", "telefone@telefone.com", "1234");
		
		try {
			usuarioControle.cadastrar(usuario01);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			

			String sql = "SELECT ID_Usuario FROM usuario WHERE email = 'telefone@telefone.com' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				usuario01.setId(resultSet.getInt("ID_Usuario"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				JDBC.fecharConexao();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@After
	public void limparTabelaBanco() {

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM usuario WHERE ID_Usuario > 0";
			stmt = conexao.prepareStatement(sql);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				JDBC.fecharConexao();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
