package desafiosefazadriano.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import desafiosefazadriano.controle.TelefoneControle;
import desafiosefazadriano.controle.UsuarioControle;


public class Utilidade {

	static UsuarioControle usuarioControle = new UsuarioControle();
	static TelefoneControle telefoneControle = new TelefoneControle();

	/* ============ Variáveis pros métodos de validação do usuário ============ */

	private static final String NOME_PATTERN = "^[ a-zA-Z áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$";
	private static final Pattern patternNome = Pattern.compile(NOME_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

	/* ============ Variáveis pros métodos de validação do telefone ============ */

	private static final String VerificarSeContemApenasNumero_PATTERN = "[0-9]+";
	private static final Pattern patternVerificarSeContemApenasNumero = Pattern
			.compile(VerificarSeContemApenasNumero_PATTERN, Pattern.CASE_INSENSITIVE);
	private static final String NUMERO_PATTERN = "^[9]{0,1}[6-9]{1}[0-9]{3}[0-9]{4}$";
	private static final Pattern patternNumero = Pattern.compile(NUMERO_PATTERN, Pattern.CASE_INSENSITIVE);

	
	  // ================ Métodos de validações Cadastro/Edição de Usuário ==================
	 

	// Metodo que recebe um email e retorna o email com as letras em minusculo
	public static String converterEmailParaMinusculo(String email) {
		return email.toLowerCase();
	}

	// Metodo pra verificar se o campo esta vazio
	public static boolean verificarCampoVazio(String campo) {
		if (campo == null) {
			campo = "";
		}
		if (campo.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	// Metodo pra verificar se a senha digitada tem no minimo 4 digitos
	public static boolean verificarSenhaMinimo4Digitos(String senha) {
		if (senha.length() < 4) {
			return true;
		} else {
			return false;
		}
	}

	// Metodo pra verificar se a senha digitada tem no maximo 12 digitos
	public static boolean verificarSenhaMaximo12Digitos(String senha) {
		if (senha.length() > 12) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se as senhas digitas sao iguais.
	public static boolean verificarSenhasIguais(String senha, String confirmarSenha) {
		if (confirmarSenha.equals(senha)) {
			return false;
		} else {
			return true;
		}
	}

	// Metodo pra verificar se no campo nome so tem letras.
	public static boolean validarNome(String nome) {
		Matcher matcher = patternNome.matcher(nome);
		if (matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

	// Metodo pra verificar se o email informado e valido.
	public static boolean validarEmail(String email) {
		Matcher matcher = patternEmail.matcher(email);
		if (matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

	// Metodo pra verificar se o nome digitado tem no maximo 70 digitos
	public static boolean verificarNomeMaximo70Digitos(String nome) {
		if (nome.length() > 70) {
			return true;
		} else {
			return false;
		}
	}

	// Metodo pra verificar se o email digitado tem no maximo 80 digitos
	public static boolean verificarEmailMaximo80Digitos(String email) {
		if (email.length() > 80) {
			return true;
		} else {
			return false;
		}
	}

	

	
	 // ============================== Métodos de validações Cadastro/Edição de  Telefone ==============================
	
	 

	// Metodo pra verificar se o DDD digitado e valido no brasil
	public static boolean verificarDDDValido(int ddd) {
		switch (ddd) {
		case 68:
		case 82:
		case 96:
		case 92:
		case 71:
		case 88:
		case 61:
		case 27:
		case 62:
		case 98:
		case 65:
		case 84:
		case 31:
		case 41:
		case 83:
		case 91:
		case 81:
		case 86:
		case 21:
		case 51:
		case 69:
		case 95:
		case 48:
		case 79:
		case 11:
		case 63:
			return false;
		default:
			return true;
		}
	}

	// Metodo pra verificar se no campo numero contem apenas numeros
	public static boolean verificarSeContemApenasNumeros(String numero) {
		Matcher matcher = patternVerificarSeContemApenasNumero.matcher(numero);
		if (matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	// Metodo pra verificar se foi selecionado celular no campo tipo
	public static boolean tipoIgualCelular (String tipo) {
		if(tipo.equals("celular")){
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se e um numero de celular valido.
	public static boolean validarNumero(String numero) {
		Matcher matcher = patternNumero.matcher(numero);
		if (matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	// Metodo pra verificar se foi selecionado telefone no campo tipo
	public static boolean tipoIgualTelefone (String tipo) {
		if(tipo.equals("telefone")){
			return true;
		} else {
			return false;
		}
	}
	// Metodo pra verificar se o numero digitado e menor que 8 digitos
	public static boolean telefoneMenor8Digitos(String numero) {
		if(numero.length() < 8) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se o numero digitado e maior ou igual a 9 digitos
	public static boolean telefoneMaiorIgual9Digitos(String numero) {
		if(numero.length() >= 9) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se o numero digitado e menor que 9 digitos
	public static boolean celularMenor9Digitos(String numero) {
		if(numero.length() < 9) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se o numero digitado e maior que 7 digitos
	public static boolean celularMaior7Digitos(String numero) {
		if(numero.length() > 7) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se o numero digitado e menor que 8 digitos
	public static boolean celularMenor8Digitos(String numero) {
		if(numero.length() < 8) {
			return true;
		} else {
			return false;
		}
	}
	
	// Metodo pra verificar se o numero digitado e maior que 9 digitos
	public static boolean celularMaior9Digitos(String numero) {
		if(numero.length() > 9) {
			return true;
		} else {
			return false;
		}
	}

}
