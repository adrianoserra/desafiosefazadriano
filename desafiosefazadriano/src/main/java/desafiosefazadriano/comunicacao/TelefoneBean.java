package desafiosefazadriano.comunicacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import desafiosefazadriano.controle.TelefoneControle;
import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.ExibirMensagem;
import desafiosefazadriano.util.Utilidade;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean
public class TelefoneBean {
	
	/*OBS:
	 * Variáveis que começam com "rend" fazem referencia ao rendered do front-end. Servem para eu ocultar ou desocultar
	 * a "janela" onde é inserido os dados para cadastro e edição e os botões de cadastrar e atualizar um telefone. 
	 * False para ocultar e True para desocultar.
	 */

	private TelefoneControle telefoneControle = new TelefoneControle();
	private Telefone telefone = new Telefone();
	private Usuario usuario = new Usuario();
	private List<Telefone> telefones = new ArrayList<>();
	private boolean btnAtualizar = true;
	private boolean rendEsconderCadastroTelefone = false;
	private boolean rendEsconderBtnCadastrarTelefone = true;
	private boolean rendEsconderBtnAtualizarTelefone = false;
	private boolean ListarTelefoneUsuarioAposCadastrar = false;
	private boolean rendEsconderListaTelefone = false;
	private String mensagem;
	private ExibirMensagem exibirMensagem = new ExibirMensagem();
	
	/*
	 * Método de cadastrar um telefone para o usuario selecionado. Valida os campos, se tudo ok, chama o metodo de cadastrar
	 * presente no DAO pelo controle, passando o usuario selecionado e os campos preenchidoos presentes na variavel telefone.
	 * Apos cadastrar, chama o metodo de listar os telefones do usuario selecionado. Esconde a tela de cadastro/edicao
	 * do telefone, zera as variaveis telefone e usuario e exibe a mensagem de confirmacao pro usuario.
	 */

	public void cadastrar() {
		try {
			if (validarCadastroEdicaoTelefone()) {
				telefoneControle.cadastrar(usuario, telefone);
				listarTelefoneUsuario(usuario);
				rendEsconderCadastroTelefone = false;
				rendEsconderListaTelefone = true;
				telefone = new Telefone();
				usuario = new Usuario();
				mensagem = "Número cadastrado com sucesso!";
				exibirMensagem.exibirDialogTelefone("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar cadastrar telefone, entre em contato com o administrador!";
			exibirMensagem.exibirDialogTelefone("erro");
		}
	}
	
	/*
	 * Método de atualizar um telefone para o usuario selecionado. Valida os campos, se tudo ok, chama o metodo de atualizar
	 * presente no DAO pelo controle, passando os campos  preenchidoos presentes na variavel telefone.
	 * Apos atualizar,  esconde a tela de cadastro/edicao do telefone, zera a variavel telefone e exibe a mensagem de 
	 * confirmacao pro usuario.
	 * 
	 */

	public void atualizar() {
		try {
			if (validarCadastroEdicaoTelefone()) {
				telefoneControle.atualizar(telefone);
				telefone = new Telefone();
				rendEsconderCadastroTelefone = false;
				mensagem = "Número atualizado com sucesso!";
				exibirMensagem.exibirDialogTelefone("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar editar telefone, entre em contato com o administrador!";
			exibirMensagem.exibirDialogTelefone("erro");
		}
	}

	/*
	 * Método de deletar um telefone do usuario selecionado. Verifica se janela de cadastro esta aberta, caso esteja, ira fechar.
	 * Chama o metodo de deletar presente no DAO pelo controle, passando o ID do telefone. Remove da lista de telefones, 
	 * zera a variavel telefone e exibe a mensagem de confirmacao pro usuario.
	 * 
	 */
	
	public void deletar() {
		try {
			if (rendEsconderCadastroTelefone == true) {
				telefoneControle.deletar(telefone.getId());
				telefones.remove(telefone);
				rendEsconderCadastroTelefone = false;
				telefone = new Telefone();
				mensagem = "Número excluído com sucesso!";
				exibirMensagem.exibirDialogTelefone("confirmação");
			} else {
				telefoneControle.deletar(telefone.getId());
				telefones.remove(telefone);
				telefone = new Telefone();
				mensagem = "Telefone excluído com sucesso!";
				exibirMensagem.exibirDialogTelefone("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar excluir telefone, entre em contato com o administrador!";
			exibirMensagem.exibirDialogTelefone("erro");
		}
	}
	
	/*
	 * Metodo de listar telefone do usuario selecionado. Verifica se a lista de telefones esta oculta ou nao e lista todos os
	 * telefone do usuario selecionado e zera variavel telefone.
	 */

	public void listarTelefoneUsuario(Usuario user) {
		try {
			if (rendEsconderListaTelefone == false) {
				telefones = telefoneControle.listar(user);
				rendEsconderListaTelefone = true;
				telefone = new Telefone();
			} else {
				telefones = telefoneControle.listar(user);
				telefone = new Telefone();
				rendEsconderListaTelefone = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar listar telefone do usuário selecionado, entre em contato com o administrador!";
			exibirMensagem.exibirDialogTelefone("erro");
		}
	}
	
	/*
	 * Metodo para fazer desocultar ou ocultar a janela de cadastro de telefone do usuario selecionado.
	 */

	public void aparecerCadastroTelefone(Usuario user) {
		if (rendEsconderCadastroTelefone == false) {
			usuario = user;
			rendEsconderCadastroTelefone = true;
			rendEsconderBtnCadastrarTelefone = true;
			rendEsconderBtnAtualizarTelefone = false;
		} else {
			telefone = new Telefone();
			rendEsconderCadastroTelefone = false;
		}
	}
	
	/*
	 * Metodo para abrir janela de ocultar ou desocultar janela de edicao de telefone. Passa os campos a serem atualizados 
	 * pra variavel telefone. Se ja estiver aberta, é ocultada, zerando a variavel telefone.
	 */

	public void editarTelefone(Telefone tel) {
		if (rendEsconderCadastroTelefone == false) {
			telefone = tel;
			rendEsconderCadastroTelefone = true;
			rendEsconderBtnCadastrarTelefone = false;
			rendEsconderBtnAtualizarTelefone = true;
		} else {
			rendEsconderCadastroTelefone = false;
			rendEsconderBtnCadastrarTelefone = true;
			rendEsconderBtnAtualizarTelefone = false;
			telefone = new Telefone();
		}
	}
	
	// Metodo para abrir dialog no front de confirmacao de exclusao. Perguntando ao usuario se ele realmente quer deletar o telefone selecionado.

	public void abrirExclusao() {
		exibirMensagem.exibirDialogTelefone("ConfirmacaoExclusao");
	}

	// Validar Cadastro/Edição do Telefone do Usuário. Se passar em todas as validacoes, retornara true;

	public boolean validarCadastroEdicaoTelefone() {

		// Chama o metodo para verificar se o campo telefone esta vazio, se sim, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarCampoVazio(telefone.getNumero())) {
			mensagem = "O campo Número é obrigatório!";
			exibirMensagem.exibirDialogTelefone("alerta");
			return false;
		}

		// Chama o metodo para verificar se o campo tipo foi selecionado, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarCampoVazio(telefone.getTipo())) {
			mensagem = "O campo Tipo é obrigatório!";
			exibirMensagem.exibirDialogTelefone("alerta");
			return false;
		}

		// Chama o metodo para verificar se no campo numero tem somente numeros, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarSeContemApenasNumeros(telefone.getNumero())) {
			mensagem = "Digite um número válido!";
			exibirMensagem.exibirDialogTelefone("alerta");
			return false;
		}

		// Chama o metodo para verificar se no campo DDD tem um DDD válido, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarDDDValido(telefone.getDdd())) {
			mensagem = "Insira um DDD válido!";
			exibirMensagem.exibirDialogTelefone("alerta");
			return false;
		}
		
		/*
		 * Chama o metodo para verificar se o telefone ja esta cadastrado no banco presente no DAO pelo controle,
		 *  se ja estiver cadastrado, retornara false e exibira mensagem pro usuario, se nao, retornara true.
		 */
		try {
			if (telefoneControle.verificaSeTelefoneExiste(telefone)) {
				mensagem = "Número já cadastrado!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar verificar se telefone já está cadastrado, entre em contato com o administrador!";
			exibirMensagem.exibirDialogTelefone("erro");
		}

		// Chama o metodo que verifica se "telefone" foi selecionado no campo tipo.
		if (Utilidade.tipoIgualTelefone(telefone.getTipo())) {

			// Chama o metodo que verifica se o telefone digitado é menor que 8 digitos e o metodo que verifica se e maior ou igual a 9 digitos.
			if (Utilidade.telefoneMenor8Digitos(telefone.getNumero())
					|| Utilidade.telefoneMaiorIgual9Digitos(telefone.getNumero())) {
				mensagem = "Digite um Telefone válido!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}
		}

		// Chama o metodo que verifica se "celular" foi selecionado no campo tipo.
		if (Utilidade.tipoIgualCelular(telefone.getTipo())) {

			// Chama o metodo que atraves da mascara inseria no metodo verifica se e um telefone valido.
			if (Utilidade.validarNumero(telefone.getNumero())) {
				mensagem = "Digite um número de celular válido!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}
			
			// Chama o metodo que verifica se o numero digitado e menor que 9 digitos e o metodo que verifica se o numero digitado e maior que 7 digitos.
			if (Utilidade.celularMenor9Digitos(telefone.getNumero())
					&& Utilidade.celularMaior7Digitos(telefone.getNumero())) {
				mensagem = "Verifique se colocou o '9' na frente!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}

			// Chama o metodo que verifica se o numero digitado e mnor que 8 digitos.
			if (Utilidade.celularMenor8Digitos(telefone.getNumero())) {
				mensagem = "Digite um Celular válido!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}

			//Chama o metodo que verifica se o numero digitado e maior que 9 digitos.
			if (Utilidade.celularMaior9Digitos(telefone.getNumero())) {
				mensagem = "Digite um Celular válido!";
				exibirMensagem.exibirDialogTelefone("alerta");
				return false;
			}
		}
		// Passado em todos as validacoes, retornara true;
		return true;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isBtnAtualizar() {
		return btnAtualizar;
	}

	public void setBtnAtualizar(boolean btnAtualizar) {
		this.btnAtualizar = btnAtualizar;
	}

	public boolean isRendEsconderCadastroTelefone() {
		return rendEsconderCadastroTelefone;
	}

	public void setRendEsconderCadastroTelefone(boolean rendEsconderCadastroTelefone) {
		this.rendEsconderCadastroTelefone = rendEsconderCadastroTelefone;
	}

	public boolean isRendEsconderBtnCadastrarTelefone() {
		return rendEsconderBtnCadastrarTelefone;
	}

	public void setRendEsconderBtnCadastrarTelefone(boolean rendEsconderBtnCadastrarTelefone) {
		this.rendEsconderBtnCadastrarTelefone = rendEsconderBtnCadastrarTelefone;
	}

	public boolean isRendEsconderBtnAtualizarTelefone() {
		return rendEsconderBtnAtualizarTelefone;
	}

	public void setRendEsconderBtnAtualizarTelefone(boolean rendEsconderBtnAtualizarTelefone) {
		this.rendEsconderBtnAtualizarTelefone = rendEsconderBtnAtualizarTelefone;
	}

	public boolean isListarTelefoneUsuarioAposCadastrar() {
		return ListarTelefoneUsuarioAposCadastrar;
	}

	public void setListarTelefoneUsuarioAposCadastrar(boolean listarTelefoneUsuarioAposCadastrar) {
		ListarTelefoneUsuarioAposCadastrar = listarTelefoneUsuarioAposCadastrar;
	}

	public boolean isRendEsconderListaTelefone() {
		return rendEsconderListaTelefone;
	}

	public void setRendEsconderListaTelefone(boolean rendEsconderListaTelefone) {
		this.rendEsconderListaTelefone = rendEsconderListaTelefone;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TelefoneControle getTelefoneControle() {
		return telefoneControle;
	}

	public void setTelefoneControle(TelefoneControle telefoneControle) {
		this.telefoneControle = telefoneControle;
	}

	public ExibirMensagem getExibirMensagem() {
		return exibirMensagem;
	}

	public void setExibirMensagem(ExibirMensagem exibirMensagem) {
		this.exibirMensagem = exibirMensagem;
	}



}
