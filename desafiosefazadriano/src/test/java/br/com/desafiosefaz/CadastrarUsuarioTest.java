package br.com.desafiosefaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class CadastrarUsuarioTest {

	/**
	 * Testar funcionalidade de inserir usuários válidos no banco conforme as regras
	 * da aplicação.
	 * 
	 */

	@Test
	public void testCadastrarUsuario() {

		/* ==================== Montagem do cenário ===================== */

		// criando usuário
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "1234");
		UsuarioControle usuarioControle = new UsuarioControle();

		// criando variáveel booleana para captar resultado da verificação
		boolean negar = false;

		/* ================== Execução ================== */

		// inserindo no banco de dados o usuário

		try {
			usuarioControle.cadastrar(usuario01);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verificando no banco se usuário foi realmente inserido através do email

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT email FROM usuario WHERE email = '" + usuario01.getEmail() + "' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				negar = true;
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
