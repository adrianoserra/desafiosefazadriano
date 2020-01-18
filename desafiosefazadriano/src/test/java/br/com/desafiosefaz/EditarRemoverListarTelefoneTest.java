package br.com.desafiosefaz;

import java.sql.Connection;

import static org.hamcrest.Matchers.is;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import desafiosefazadriano.controle.TelefoneControle;
import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class EditarRemoverListarTelefoneTest {

	Usuario usuario01;
	Telefone telefone01;
	TelefoneControle telefoneControle = new TelefoneControle();
	UsuarioControle usuarioControle = new UsuarioControle();

	/*
	 * Testar funcionalidade de edição de telefone a partir de um já existente no
	 * banco.
	 */

	@Test
	public void testEditarTelefone() {

		/* ==================== Montagem do cenário ===================== */

		// criando variáveel booleana para captar resultado da verificação
		boolean negar = true;

		// alterando valores do telefone
		telefone01.setDdd(11);
		telefone01.setNumero("88888888");
		telefone01.setTipo("telefone");

		/* ================== Execução ================== */

		try {
			telefoneControle.atualizar(telefone01);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Verificando no banco se telefone foi realmente alterado através do número
		// passando o ID telefone como referência
		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT numero FROM telefone WHERE ID_Telefone = '" + telefone01.getId() + "' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("numero").equals("88888888")) {
					negar = false;
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

		assertFalse(negar);
	}

	/*
	 * Testar funcionalidade de remoção de telefone a partir de um já existente no
	 * banco.
	 */

	@Test
	public void testRemoverTelefone() {

		/* ==================== Montagem do cenário ===================== */

		// criando variáveel booleana para captar resultado da verificação
		boolean negar = true;

		/* ================== Execução ================== */

		try {
			telefoneControle.deletar(telefone01.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verificando no banco se telefone foi removido do usuário passando o ID
		// telefone como referência
		Connection conexao = JDBC.getConexao();
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT numero FROM telefone WHERE ID_Telefone = '" + telefone01.getId() + "' ";
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
	 * Testar funcionalidade de listagem de telefone a partir de um já existente no
	 * banco.
	 */

	@Test
	public void testListarTelefone() {
		/* ==================== Montagem do cenário ===================== */
		List<Telefone> listaTelefone = new ArrayList<>();

		/* ================== Execução ================== */

		try {
			listaTelefone = telefoneControle.listar(usuario01);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/* ================== Verificação ================== */

		assertThat(listaTelefone.size(), is(1));

	}

	/*
	 * Testar método de verificar durante o cadastro/edição de telefone se o número
	 * e DDD já existem no banco. Verificando a partir de um telefone não existente
	 * no banco. Se não existe, retornará False;
	 */

	@Test
	public void testVerificaSeTelefoneExisteNegar() {

		/* ==================== Montagem do cenário ===================== */
		boolean existe = false;
		Telefone telefone02 = new Telefone(81, "978766666", "celular");

		/* ================== Execução ================== */

		try {
			existe = telefoneControle.verificaSeTelefoneExiste(telefone02);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/* ================== Verificação ================== */

		assertFalse(existe);
	}

	/*
	 * Testar método de verificar durante o cadastro/edição de telefone se o número
	 * e DDD já existem no banco. Verificando a partir de um telefone existente no
	 * banco. Se existe, retornará True;
	 */

	@Test
	public void testVerificaSeTelefoneExistePermitir() {

		/* ==================== Montagem do cenário ===================== */
		boolean existe = false;

		/* ================== Execução ================== */

		try {
			existe = telefoneControle.verificaSeTelefoneExiste(telefone01);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/* ================== Verificação ================== */

		assertTrue(existe);
	}

	/*
	 * Executar antes dos métodos. Cria um usuário, cadastra no banco. Pesquisa esse
	 * usuário no banco para pegar o ID_Usuario e setar nele. Cria um telefone e
	 * insere nesse usuário. Pesquisa no banco por esse telefone e insere o
	 * ID_Telefone no telefone
	 */

	@Before
	public void cadastrarUsuarioComTelefoneNoBanco() {

		
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
		
		telefone01 = new Telefone(81, "999999999", "celular");
		
		try {
			telefoneControle.cadastrar(usuario01, telefone01);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		conexao = JDBC.getConexao();
		stmt = null;

		try {	
			String sql = "SELECT ID_Telefone FROM telefone WHERE numero = '999999999' ";
			ResultSet resultSet = null;
			stmt = conexao.prepareStatement(sql);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				telefone01.setId(resultSet.getInt("ID_Telefone"));
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
	 * Limpar tabela do banco após o término de cada teste
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
