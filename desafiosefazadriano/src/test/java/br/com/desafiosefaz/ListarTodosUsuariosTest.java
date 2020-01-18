package br.com.desafiosefaz;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.conexao.JDBC;

public class ListarTodosUsuariosTest {
	
	/*
	 * Testar funcionalidade de listar todos os usuários existentes no banco.
	 */

	@Test
	public void testListar () {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando alguns usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "1234");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@hotmail.com", "4321");
		UsuarioControle usuarioControle = new UsuarioControle();
		List<Usuario> listaUsuarioBanco = new ArrayList<>();
		
		// inserindo no banco os usuários criados
		try {
			usuarioControle.cadastrar(usuario01);
			usuarioControle.cadastrar(usuario02);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/* ================== Execução ================== */
		
		// Armazenando na variável lista os usuários cadastrados no banco 
		try {
			listaUsuarioBanco = usuarioControle.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/* ================== Verificação ================== */
		
		assertThat(listaUsuarioBanco.size(), is(2));
		assertEquals(listaUsuarioBanco.get(0).getNome(), ("Adriano Serra"));
		assertEquals(listaUsuarioBanco.get(1).getNome(), ("Serra Adriano"));

	}
	
	/*
	 * Limpar a tabela do banco antes de cada método.
	 */
	
	@Before
	public void limparTabelaBancoAntes() {

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
	
	/*
	 * Limpar a tabela do banco após cada método.
	 */
	
	@After
	public void limparTabelaBancoDepois() {

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
