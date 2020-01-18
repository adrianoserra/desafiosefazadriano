package br.com.desafiosefaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class RemoverUsuarioTest {

	Usuario usuario01;
	UsuarioControle usuarioControle = new UsuarioControle();

	/**
	 * Testar funcionalidade de remover usuários cadastrados no banco.
	 * 
	 */

	@Test
	public void testDeletarUsuario() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando variáveel booleana para captar resultado da verificação
		boolean negar = true;
		
		/* ================== Execução ================== */

		try {
			usuarioControle.deletar(usuario01.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verificando no banco se usuário foi realmente removido através do email

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT nome FROM usuario WHERE email = 'remover@remover.com' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				negar = false;
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

		assertTrue(negar);

	}
	
	/*
	 * Montagem do cenário = Inserir um usuário no banco, consultar o ID gerado pra
	 * ele no banco e colocar manualmente no usuário.
	 */

	@Before
	public void cadastrarUsuarioNoBanco() {
		
		usuario01 = new Usuario("Adriano Serra", "remover@remover.com", "1234");
		
		try {
			usuarioControle.cadastrar(usuario01);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT ID_Usuario FROM usuario WHERE email = 'remover@remover.com' ";
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
	
	/*
	 * Limpar a tabela do banco após cada método.
	 */

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
