package br.com.desafiosefaz;

import org.junit.Test;
import static org.junit.Assert.*;

import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.util.Utilidade;

public class TelefoneValidacaoCadastroEdicaoMetodosTest {
	
	/**
	 * Testar cadastro de telefone com campo numero vazio.
	 * Se campo numero estiver vazio retornará true.
	 */
	
	@Test
	public void testVerificarCampoVazioNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo numero vazio no método para verificação
		negar = Utilidade.verificarCampoVazio(telefone01.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar);
	}
	
	/**
	 * Testar cadastro de telefone com nenhum campo vazio
	 * Se nenhum campo estiver vazio retornará false.
	 */
	
	@Test
	public void testVerificarCampoVazioPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo nome vazio no método para verificação
		negar01 = Utilidade.verificarCampoVazio(telefone01.getNumero());
		negar02 = Utilidade.verificarCampoVazio(telefone01.getTipo());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
	}
	
	
	/**
	 * Testar cadastro de telefone com valor do campo DDD inválido
	 * Se campo DDD estiver inválido retornará true.
	 */
	
	@Test
	public void testVerificarDDDValidoNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(44, "997961105", "celular");
		Telefone telefone02 = new Telefone(55, "34414685", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com DDD inválido no método para verificação
		negar01 = Utilidade.verificarDDDValido(telefone01.getDdd());
		negar02 = Utilidade.verificarDDDValido(telefone02.getDdd());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
	}
	
	/**
	 * Testar cadastro de telefone com valor do campo DDD válido
	 * Se campo DDD estiver válido retornará false.
	 */
	
	@Test
	public void testVerificarDDDValidoPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		Telefone telefone02 = new Telefone(11, "34414685", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com DDD válido no método para verificação
		negar01 = Utilidade.verificarDDDValido(telefone01.getDdd());
		negar02 = Utilidade.verificarDDDValido(telefone02.getDdd());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
	}
	
	/**
	 * Testar cadastro de telefone com campo número preenchido com letras.
	 * Se no campo número tiver alguma letra retornará true.
	 */
	
	@Test
	public void testVerificarSeContemApenasNumerosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "aaaaaaaaa", "celular");
		Telefone telefone02 = new Telefone(11, "344abs85", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número inválido no método para verificação
		negar01 = Utilidade.verificarSeContemApenasNumeros(telefone01.getNumero());
		negar02 = Utilidade.verificarSeContemApenasNumeros(telefone02.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
	}
	
	/**
	 * Testar cadastro de telefone com campo número válido.
	 * Se o campo número for válido retornará false.
	 */
	
	@Test
	public void testVerificarSeContemApenasNumerosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		Telefone telefone02 = new Telefone(11, "34414685", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número válido no método para verificação
		negar01 = Utilidade.verificarSeContemApenasNumeros(telefone01.getNumero());
		negar02 = Utilidade.verificarSeContemApenasNumeros(telefone02.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
	}
	
	
	/**
	 * Testar cadastro de telefone fazendo verificações com o usuário não selecionando celular no campo tipo.
	 * Se o usuário não selecionar celular no campo tipo retornará false;
	 */
	
	@Test
	public void testTipoIgualCelularNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "997961105", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo tipo telefone no método para verificação
		negar01 = Utilidade.tipoIgualCelular(telefone01.getTipo());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
	}
	
	/**
	 * Testar cadastro de telefone fazendo verificações com o usuário selecionando celular no campo tipo.
	 * Se o usuário selecionar celular no campo tipo retornará false;
	 */
	
	@Test
	public void testTipoIgualCelularPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo tipo celular no método para verificação
		negar01 = Utilidade.tipoIgualCelular(telefone01.getTipo());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular inválido.
	 * Se o campo número de celular  for inválido retornará true;
	 */
	
	@Test
	public void testValidarNumeroNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "333333333", "celular");
		Telefone telefone02 = new Telefone(11, "33333333", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular inválido no método para verificação
		negar01 = Utilidade.validarNumero(telefone01.getNumero());
		negar02 = Utilidade.validarNumero(telefone02.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular válido.
	 * Se o campo número de celular for válido retornará false;
	 */
	
	@Test
	public void testValidarNumeroPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		Telefone telefone02 = new Telefone(11, "997961104", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular inválido no método para verificação
		negar01 = Utilidade.validarNumero(telefone01.getNumero());
		negar02 = Utilidade.validarNumero(telefone02.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
	}

	
	/**
	 * Testar cadastro de telefone fazendo verificações com o usuário não selecionando telefone no campo tipo.
	 * Se o usuário não selecionar telefone no campo tipo retornará false;
	 */
	
	@Test
	public void testTipoIgualTelefoneNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo tipo celular no método para verificação
		negar01 = Utilidade.tipoIgualTelefone(telefone01.getTipo());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
	}
	
	
	/**
	 * Testar cadastro de telefone fazendo verificações com o usuário selecionando telefone no campo tipo.
	 * Se o usuário selecionar telefone no campo tipo retornará true;
	 */
	
	@Test
	public void testTipoIgualTelefonePermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefone
		Telefone telefone01 = new Telefone(81, "997961105", "telefone");
		
		
		// criando variáveel booleana para captar resultado da verificação
		boolean negar01 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo tipo telefone no método para verificação
		negar01 = Utilidade.tipoIgualTelefone(telefone01.getTipo());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de telefone com o usuário digitando menos de 8 dígitos.
	 * Se o usuário digitar menos de 8 dígitos, retornará true;
	 */
	
	@Test
	public void testTelefoneMenor8DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "3441468", "telefone");
		Telefone telefone02 = new Telefone(81, "3441", "telefone");
		Telefone telefone03 = new Telefone(81, "31", "telefone");
		
		
		// criando variáveel booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de telefone no método para verificação
		negar01 = Utilidade.telefoneMenor8Digitos(telefone01.getNumero());
		negar02 = Utilidade.telefoneMenor8Digitos(telefone02.getNumero());
		negar03 = Utilidade.telefoneMenor8Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de telefone com o usuário digitando 8 dígitos.
	 * Se o usuário digitar 8 dígitos, retornará false;
	 */
	
	@Test
	public void testTelefoneMenor8DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "34414685", "telefone");
		Telefone telefone02 = new Telefone(81, "21190608", "telefone");
		Telefone telefone03 = new Telefone(81, "21190607", "telefone");
		
		
		// criando variáveel booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de telefone no método para verificação
		negar01 = Utilidade.telefoneMenor8Digitos(telefone01.getNumero());
		negar02 = Utilidade.telefoneMenor8Digitos(telefone02.getNumero());
		negar03 = Utilidade.telefoneMenor8Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de telefone com o usuário digitando 9 ou mais dígitos.
	 * Se o usuário digitar 9 ou mais dígitos, retornará true;
	 */
	
	@Test
	public void testTelefoneMaiorIgual9DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "344146859", "telefone");
		Telefone telefone02 = new Telefone(81, "2119060807", "telefone");
		Telefone telefone03 = new Telefone(81, "344146899123", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de telefone no método para verificação
		negar01 = Utilidade.telefoneMaiorIgual9Digitos(telefone01.getNumero());
		negar02 = Utilidade.telefoneMaiorIgual9Digitos(telefone02.getNumero());
		negar03 = Utilidade.telefoneMaiorIgual9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de telefone com o usuário digitando 8 dígitos.
	 * Se o usuário digitar 8 dígitos, retornará false;
	 */
	
	@Test
	public void testTelefoneMaiorIgual9DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "34414685", "telefone");
		Telefone telefone02 = new Telefone(81, "21190608", "telefone");
		Telefone telefone03 = new Telefone(81, "21190607", "telefone");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de telefone no método para verificação
		negar01 = Utilidade.telefoneMaiorIgual9Digitos(telefone01.getNumero());
		negar02 = Utilidade.telefoneMaiorIgual9Digitos(telefone02.getNumero());
		negar03 = Utilidade.telefoneMaiorIgual9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 8 ou menos dígitos.
	 * Se o usuário digitar 8 ou menos dígitos, retornará true;
	 */
	
	@Test
	public void testCelularMenor9DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "97961105", "celular");
		Telefone telefone02 = new Telefone(81, "87451201", "celular");
		Telefone telefone03 = new Telefone(81, "87049413", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMenor9Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMenor9Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMenor9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 9 dígitos.
	 * Se o usuário digitar 9 dígitos, retornará false;
	 */
	
	@Test
	public void testCelularMenor9DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		Telefone telefone02 = new Telefone(81, "987451201", "celular");
		Telefone telefone03 = new Telefone(81, "987049413", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMenor9Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMenor9Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMenor9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}

	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 7 ou mais dígitos.
	 * Se o usuário digitar 7 ou mais dígitos, retornará true;
	 */
	
	@Test
	public void testCelularMaior7DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "97961105", "celular");
		Telefone telefone02 = new Telefone(81, "87049413333", "celular");
		Telefone telefone03 = new Telefone(81, "87481447", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMaior7Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMaior7Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMaior7Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 6 ou menos dígitos.
	 * Se o usuário digitar 6 ou menos dígitos, retornará false;
	 */
	
	@Test
	public void testCelularMaior7DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "974512", "celular");
		Telefone telefone02 = new Telefone(81, "9445", "celular");
		Telefone telefone03 = new Telefone(81, "9798652", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMaior7Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMaior7Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMaior7Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 7 ou menos dígitos.
	 * Se o usuário digitar 7 ou menos dígitos, retornará true;
	 */
	
	@Test
	public void testCelularMenor8DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "97961", "celular");
		Telefone telefone02 = new Telefone(81, "9796110", "celular");
		Telefone telefone03 = new Telefone(81, "2", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMenor8Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMenor8Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMenor8Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 8 ou mais dígitos.
	 * Se o usuário digitar 8 ou mais dígitos, retornará false;
	 */
	
	@Test
	public void testCelularMenor8DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "97961105", "celular");
		Telefone telefone02 = new Telefone(81, "987049413", "celular");
		Telefone telefone03 = new Telefone(81, "985190587", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMenor8Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMenor8Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMenor8Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando mais de 9 dígitos.
	 * Se o usuário digitar mais de 9 dígitos, retornará true;
	 */
	
	@Test
	public void testCelularMaior9DigitosNegar() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "9979611055", "celular");
		Telefone telefone02 = new Telefone(81, "997961100000", "celular");
		Telefone telefone03 = new Telefone(81, "98745121123", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMaior9Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMaior9Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMaior9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertTrue(negar01);
		assertTrue(negar02);
		assertTrue(negar03);
	}
	
	/**
	 * Testar cadastro de telefone com campo número de celular com o usuário digitando 9 ou menos dígitos.
	 * Se o usuário digitar 9 ou menos dígitos, retornará false;
	 */
	
	@Test
	public void testCelularMaior9DigitosPermitir() {
		
		/* ==================== Montagem do cenário ===================== */
		
		// criando telefones
		Telefone telefone01 = new Telefone(81, "997961105", "celular");
		Telefone telefone02 = new Telefone(81, "997961", "celular");
		Telefone telefone03 = new Telefone(81, "942314", "celular");
		
		
		// criando variável booleana para captar resultado da verificação
		boolean negar01 = false;
		boolean negar02 = false;
		boolean negar03 = false;
		
		/* ================== Execução ================== */
		
		//Colocando variável com campo número de celular no método para verificação
		negar01 = Utilidade.celularMaior9Digitos(telefone01.getNumero());
		negar02 = Utilidade.celularMaior9Digitos(telefone02.getNumero());
		negar03 = Utilidade.celularMaior9Digitos(telefone03.getNumero());
		
		
		/* ================== Verificação ================== */
		assertFalse(negar01);
		assertFalse(negar02);
		assertFalse(negar03);
	}
	
}
