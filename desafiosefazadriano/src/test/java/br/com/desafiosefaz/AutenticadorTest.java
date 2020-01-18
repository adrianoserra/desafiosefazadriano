package br.com.desafiosefaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class AutenticadorTest {

	Usuario usuario01;
	UsuarioControle usuarioControle = new UsuarioControle();
	
	/*
	 * Testar método que verifica se usuário está cadastrado no banco para poder fazer login através do email e senha.
	 * Se não existir no banco, retornará usuario com id = -1;
	 */

	@Test
	public void testVerificaLoginNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		Usuario usuarioFake = new Usuario("Serra Adriano", "naoexistenobanco@gmail.com", "1234");
		Usuario usuarioTeste = new Usuario();

		
		/* ================== Execução ================== */
		
		try {
			usuarioTeste = usuarioControle.verificaLogin(usuarioFake.getEmail(), usuarioFake.getSenha());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/* ================== Verificação ================== */
		
		assertThat(usuarioTeste.getId(), is(-1));
		

	}
	
	/*
	 * Testar método que verifica se usuário está cadastrado no banco para poder fazer login através do email e senha.
	 * Se existir no banco, retornará o usuário.
	 */

	@Test
	public void testVerificaLoginPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		Usuario usuarioTeste = new Usuario();

		
		/* ================== Execução ================== */
		
		try {
			usuarioTeste = usuarioControle.verificaLogin(usuario01.getEmail(), usuario01.getSenha());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(usuarioTeste.getNome(), ("Verifica Ok"));
		assertEquals(usuarioTeste.getEmail(), ("testandologin@gmail.com"));
		

	}
	
	

	/*
	 * Montagem do cenário = Inserir um usuário no banco, consultar o ID gerado pra
	 * ele no banco e colocar manualmente no usuário.
	 */

	@Before
	public void cadastrarUsuarioNoBanco() {

		try {
			usuario01 = new Usuario("Verifica Ok", "testandologin@gmail.com", "1234");
			usuarioControle.cadastrar(usuario01);
		} catch (SQLException e) {
			e.printStackTrace();
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
