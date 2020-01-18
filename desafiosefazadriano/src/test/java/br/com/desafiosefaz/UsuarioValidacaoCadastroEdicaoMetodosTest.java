package br.com.desafiosefaz;

import org.junit.Test;
import static org.junit.Assert.*;

import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.Utilidade;

public class UsuarioValidacaoCadastroEdicaoMetodosTest {
	
	/**
	 * Testar cadastro com campo nome vazio.
	 * Se campo nome estiver vazio retornará true.
	 */
	
	@Test
	public void testVerificarCampoVazio() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuário
		Usuario usuario01 = new Usuario("", "adri@gmail.com", "1234");
		
		// criando variáveel booleana para captar resultado da verificação
		boolean negar = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável nome vazio no método para verificação
		negar = Utilidade.verificarCampoVazio(usuario01.getNome());
		
		/* ================== Verificação ================== */
		assertTrue(negar);
	}
	
	/**
	 * Testar método de verificação de senha com mínimo de 4 dígitos, com o usuário digitando menor que 4 dígitos. 
	 * Se for menor que 4 dígitos, retornará true.
	 */
	
	@Test
	public void testVerificarSenhaMinimo4DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "123");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@gmail.com", "1");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhaMinimo4Digitos(usuario01.getSenha());
		negar2 = Utilidade.verificarSenhaMinimo4Digitos(usuario02.getSenha());
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
	}
	
	/**
	 * Testar método de verificação de senha com mínimo de 4 dígitos, com o usuário digitando 4 ou mais dígitos dentro do permitido.
	 * Se for maior que 4 dígitos, retornará false.
	 */
	
	@Test
	public void testVerificarSenhaMinimo4DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "1234");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@gmail.com", "123456");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhaMinimo4Digitos(usuario01.getSenha());
		negar2 = Utilidade.verificarSenhaMinimo4Digitos(usuario02.getSenha());
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
	}
	
	/**
	 * Testar método de verificação de senha com máximo de 12 dígitos, com o usuário digitando 13 ou mais dígitos.
	 * Se for 13 ou mais retornará true;
	 */
	
	@Test
	public void testVerificarSenhaMaximo12DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "0123456789123");
		Usuario usuario02 = new Usuario("Serra Adriano ", "dri@gmail.com", "012345678912345");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhaMaximo12Digitos(usuario01.getSenha());
		negar2 = Utilidade.verificarSenhaMaximo12Digitos(usuario02.getSenha());
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
	}
	
	/**
	 * Testar método de verificação de senha com máximo de 12 dígitos, com o usuário digitando 12 ou menos dígitos dentro do mínimo.
	 * Se for 12 ou menos retornará false;
	 */
	
	@Test
	public void testVerificarSenhaMaximo12DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@gmail.com", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhaMaximo12Digitos(usuario01.getSenha());
		negar2 = Utilidade.verificarSenhaMaximo12Digitos(usuario02.getSenha());
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
	}
	
	/**
	 * Testar método de verificação de senhas iguais, com o usuário digitando-as diferentes.
	 * Se forem diferentes retornará true;
	 */
	
	@Test
	public void testVerificarSenhasIguaisNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@gmail.com", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		//criando variáveis de confirmação de senha para realização da verificação
		String confirmarSenhaUsuario01 = "11111111";
		String confirmarSenhaUsuario02 = "33333333";
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhasIguais(usuario01.getSenha(), confirmarSenhaUsuario01);
		negar2 = Utilidade.verificarSenhasIguais(usuario02.getSenha(), confirmarSenhaUsuario02);
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
	}
	
	/**
	 * Testar método de verificação de senhas iguais, com o usuário digitando-as iguais.
	 * Se forem iguais retornará false;
	 */
	
	@Test
	public void testVerificarSenhasIguaisPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "adri@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Serra Adriano", "dri@gmail.com", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		//criando variáveis de confirmação de senha para realização da verificação
		String confirmarSenhaUsuario01 = "0123456789";
		String confirmarSenhaUsuario02 = "012345678912";
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarSenhasIguais(usuario01.getSenha(), confirmarSenhaUsuario01);
		negar2 = Utilidade.verificarSenhasIguais(usuario02.getSenha(), confirmarSenhaUsuario02);
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
	}
	
	/**
	 * Testar método de verificação de nome válido, com o usuário digitando nomes inválidos = com (caracteres especiais e números).
	 * Se for um nome inválido retornará true;
	 */
	
	@Test
	public void testValidarNomeNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("#22#", "adri@gmail.com", "1234");
		Usuario usuario02 = new Usuario(". Adriano", "ddri@gmail.com", "1234");
		Usuario usuario03 = new Usuario("Ser@ra 32Adriano", "drri@gmail.com", "1234");
		Usuario usuario04 = new Usuario("###@@!", "drii@gmail.com", "1234");
		Usuario usuario05 = new Usuario("23412", "aadri@gmail.com", "1234");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		boolean negar3 = false;
		boolean negar4 = false;
		boolean negar5 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.validarNome(usuario01.getNome());
		negar2 = Utilidade.validarNome(usuario02.getNome());
		negar3 = Utilidade.validarNome(usuario03.getNome());
		negar4 = Utilidade.validarNome(usuario04.getNome());
		negar5 = Utilidade.validarNome(usuario05.getNome());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
		assertTrue(negar3);
		assertTrue(negar4);
		assertTrue(negar5);
	}
	
	/**
	 * Testar método de verificação de nome válido, com o usuário digitando nomes válidos = sem (caracteres especiais e números).
	 * Se for um nome inválido retornará true;
	 */
	
	@Test
	public void testValidarNomePermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Cleber", "adri@gmail.com", "1234");
		Usuario usuario02 = new Usuario("Adriano Serra", "dri@gmail.com", "1234");
		Usuario usuario03 = new Usuario("Serra", "dri@gmail.com", "1234");
		Usuario usuario04 = new Usuario("Adriano Serra de Andrade", "dri@gmail.com", "1234");
		Usuario usuario05 = new Usuario("Feli", "dri@gmail.com", "1234");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		boolean negar3 = false;
		boolean negar4 = false;
		boolean negar5 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.validarNome(usuario01.getNome());
		negar2 = Utilidade.validarNome(usuario02.getNome());
		negar3 = Utilidade.validarNome(usuario03.getNome());
		negar4 = Utilidade.validarNome(usuario04.getNome());
		negar5 = Utilidade.validarNome(usuario05.getNome());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
		assertFalse(negar3);
		assertFalse(negar4);
		assertFalse(negar5);
	}
	
	/**
	 * Testar método de verificação de nome com máximo de 70 dígitos, com o usuário digitando 71 ou mais dígitos.
	 * Se for 71 ou mais retornará true;
	 */
	
	/**
	 * Testar método de verificação de email válido, com o usuário digitando emails inválidos.
	 * Se for um email inválido retornará true;
	 */
	
	@Test
	public void testValidarEmailNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "invalido", "1234");
		Usuario usuario02 = new Usuario("Serra Adriano", "inv@", "1234");
		Usuario usuario03 = new Usuario("Adriano", "a@b.", "1234");
		Usuario usuario04 = new Usuario("Serra", "@bsd.com", "1234");
		Usuario usuario05 = new Usuario("adriS", "asd@gm", "1234");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		boolean negar3 = false;
		boolean negar4 = false;
		boolean negar5 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.validarEmail(usuario01.getEmail());
		negar2 = Utilidade.validarEmail(usuario02.getEmail());
		negar3 = Utilidade.validarEmail(usuario03.getEmail());
		negar4 = Utilidade.validarEmail(usuario04.getEmail());
		negar5 = Utilidade.validarEmail(usuario05.getEmail());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
		assertTrue(negar3);
		assertTrue(negar4);
		assertTrue(negar5);
	}
	
	/**
	 * Testar método de verificação de email válido, com o usuário digitando emails válidos.
	 * Se for um email válido retornará false;
	 */
	
	@Test
	public void testValidarEmailPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "teste@gmail.com", "1234");
		Usuario usuario02 = new Usuario("Serra Adriano", "teste@hotmail.com", "1234");
		Usuario usuario03 = new Usuario("Adriano", "teste@yahoo.com.br", "1234");
		Usuario usuario04 = new Usuario("Serra", "teste@sindifiscope.org.br", "1234");
		Usuario usuario05 = new Usuario("adriS", "teste@bol.com.br", "1234");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		boolean negar3 = false;
		boolean negar4 = false;
		boolean negar5 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.validarEmail(usuario01.getEmail());
		negar2 = Utilidade.validarEmail(usuario02.getEmail());
		negar3 = Utilidade.validarEmail(usuario03.getEmail());
		negar4 = Utilidade.validarEmail(usuario04.getEmail());
		negar5 = Utilidade.validarEmail(usuario05.getEmail());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
		assertFalse(negar3);
		assertFalse(negar4);
		assertFalse(negar5);
	}
	
	@Test
	public void testVerificarNomeMaximo70DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Pedro de Alcântara Francisco Antônio Joãoo Carlos Xavier de Paula Pedro", "adri@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim de Bragança e Bourbon", "dri@gmail.com", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarNomeMaximo70Digitos(usuario01.getNome());
		negar2 = Utilidade.verificarNomeMaximo70Digitos(usuario02.getNome());
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
	}
	
	/**
	 * Testar método de verificação de nome com máximo de 70 dígitos, com o usuário digitando 70 ou menos dígitos.
	 * Se for 70 ou menos retornará false;
	 */
	
	@Test
	public void testVerificarNomeMaximo70DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula Pedro", "adri@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Pedro de Alcântara Francisco Antônio José Bragança e Bourbon", "dri@gmail.com", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarNomeMaximo70Digitos(usuario01.getNome());
		negar2 = Utilidade.verificarNomeMaximo70Digitos(usuario02.getNome());
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
	}
	
	/**
	 * Testar método de verificação de email com máximo de 80 dígitos, com o usuário digitando 81 ou mais dígitos.
	 * Se for 81 ou mais retornará true;
	 */
	
	@Test
	public void testVerificarEmailMaximo80DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "pedrodeAlcantarajoaoFranciscooAntoniojoaoCarlosxavierdepaulapedroxavier@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Serra Adriano", "pedrodeAlcantarajoaoFranciscooAntoniojoaoCarlosxavierdepaulapedroxavier@yahoo.com.br", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarEmailMaximo80Digitos(usuario01.getEmail());
		negar2 = Utilidade.verificarEmailMaximo80Digitos(usuario02.getEmail());
		
		/* ================== Verificação ================== */
		assertTrue(negar1);
		assertTrue(negar2);
	}
	
	/**
	 * Testar método de verificação de email com máximo de 80 dígitos, com o usuário digitando 80 ou menos dígitos.
	 * Se for 80 ou menos retornará false;
	 */
	
	@Test
	public void testVerificarEmailMaximo80DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */

		// criando usuários
		Usuario usuario01 = new Usuario("Adriano Serra", "edrodeAlcantarajoaoFranciscooAntoniojoaoCarlosxavierdepaulapedroxavier@gmail.com", "0123456789");
		Usuario usuario02 = new Usuario("Serra Adriano", "pedrodeAlcepaulapedroxavier@yahoo.com.br", "012345678912");
		
		// criando variáveis booleanas para captar resultado da verificação
		boolean negar1 = false;
		boolean negar2 = false;
		
		/* ================== Execução ================== */
		
		//Verificando se o usuario passará na validação do método 
		negar1 = Utilidade.verificarEmailMaximo80Digitos(usuario01.getEmail());
		negar2 = Utilidade.verificarEmailMaximo80Digitos(usuario02.getEmail());
		
		/* ================== Verificação ================== */
		assertFalse(negar1);
		assertFalse(negar2);
	}
	

}
