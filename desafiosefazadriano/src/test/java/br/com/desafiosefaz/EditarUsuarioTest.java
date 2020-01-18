package br.com.desafiosefaz;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class EditarUsuarioTest {

	Usuario usuario01;
	UsuarioControle usuarioControle = new UsuarioControle();

	/**
	 * Testar funcionalidade de editar usuários cadastrados no banco.
	 * 
	 */

	@Test
	public void testEditarUsuario() {

		/* ==================== Montagem do cenário ===================== */

		// criando variáveel booleana para captar resultado da verificação
		boolean negar = true;

		// alterando valores do usuário
		usuario01.setNome("editar");
		usuario01.setEmail("editar@editar.com");
		usuario01.setSenha("editar");

		/* ================== Execução ================== */

		try {
			usuarioControle.atualizar(usuario01);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verificando no banco se usuário foi realmente alterado através do email

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT email FROM usuario WHERE email = 'editar@editar.com' ";
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

		assertFalse(negar);

	}

	/*
	 * Testar método de verificação de cadastro que verifica se o usuário que está
	 * tentando se cadastrar, já está cadastrado no banco através do email. Se
	 * estiver já cadastrado retornará true;
	 */

	@Test
	public void testVerificarSeUsuarioJaEstaCadastradoNegar() {

		/* ==================== Montagem do cenário ===================== */
		boolean cadastrado = false;

		/* ================== Execução ================== */
		try {
			cadastrado = usuarioControle.verificaSeUsuarioEstaCadastrado(usuario01.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/* ================== Verificação ================== */

		assertTrue(cadastrado);
	}

	/*
	 * Testar método de verificação de cadastro que verifica se o usuário que está
	 * tentando se cadastrar, já está cadastrado no banco através do email. Se não
	 * estiver cadastrado retornará false;
	 */

	@Test
	public void testVerificarSeUsuarioJaEstaCadastradoPermitir() {

		/* ==================== Montagem do cenário ===================== */
		boolean cadastrado = false;
		String email = "naotemnobanco@hotmail.com";

		/* ================== Execução ================== */
		try {
			cadastrado = usuarioControle.verificaSeUsuarioEstaCadastrado(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/* ================== Verificação ================== */

		assertFalse(cadastrado);
	}

	/*
	 * Montagem do cenário = Inserir um usuário no banco, consultar o ID gerado pra
	 * ele no banco e colocar manualmente no usuário.
	 */

	@Before
	public void cadastrarUsuarioNoBanco() {
		
		usuario01 = new Usuario("Adriano Serra", "testandoeditar@gmail.com", "1234");
		
		try {
			usuarioControle.cadastrar(usuario01);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;
		try {
			

			String sql = "SELECT ID_Usuario FROM usuario WHERE email = 'testandoeditar@gmail.com' ";
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
