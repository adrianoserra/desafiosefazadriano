package desafiosefazadriano.comunicacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Telefone;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.ExibirMensagem;
import desafiosefazadriano.util.SessionUtil;
import desafiosefazadriano.util.Utilidade;

@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean
public class UsuarioBean {
	
	/*OBS:
	 * Variáveis que começam com "rend" fazem referencia ao rendered do front-end. Servem para eu ocultar ou desocultar
	 * a "janela" onde e inserido os dados para cadastro e edicao e os botoes de cadastrar e atualizar um usuario. 
	 * False para ocultar e True para desocultar.
	 */

	private UsuarioControle usuarioControle = new UsuarioControle();
	private Usuario USUARIOLogado;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	private String confirmarSenha;
	private boolean rendEsconderCadastroUsuario = false;
	private boolean rendEsconderBtnAtualizarUsuario = false;
	private boolean rendEsconderBtnCadastrarUsuario = true;
	private String mensagem;
	private ExibirMensagem exibirMensagem = new ExibirMensagem();
	private Telefone zerarTelefone = new Telefone();
	@ManagedProperty(value = "#{telefoneBean}")
	private TelefoneBean telefoneBean;
	
	// Adiciona na variável USUARIOLogado quem fez login no sistema.
	public UsuarioBean() {
		USUARIOLogado = (Usuario) SessionUtil.getParam("USUARIOLogado");
	}

	/*
	 * Metodo de cadastrar um usuario. Primeiro valida os campos informados pelo usuário atraves do metodo 
	 * validarCadastroEdicaoUsuario, se validado, verifica tambem se o usuário ja esta cadastrado.
	 * Se tudo ok, chama o método de cadastrar presente no DAO pelo controle, passando os campos que estão
	 * na variável usuario, esconde a "janela", exibe a mensagem de confirmacao pro usuario.
	 * 
	 */
	
	public void cadastrar() {
		try {
			if (validarCadastroEdicaoUsuario() && localVerificarSeUsuarioJaEstaCadastrado()) {
				usuarioControle.cadastrar(usuario);
				rendEsconderCadastroUsuario = false;
				mensagem = "Cadastro realizado com sucesso!";
				exibirMensagem.exibirDialog("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar cadastrar usuário, entre em contato com o administrador!";
			exibirMensagem.exibirDialog("erro");
		}

	}
	
	/*
	 *  Metodo que faz aparecer / desaparecer a "janela" de cadastro de usuario.
	 *  Verifica se ela esta fechada, se estiver, ela abrirá zerando o campo confirmar senha e zerando a variável usuario.
	 *  Se estiver aberta, ela fechara zerando o campo de confirmar senha.
	 */

	public void aparecerCadastroUsuario() {

		if (rendEsconderCadastroUsuario == false) {
			confirmarSenha = "";
			usuario = new Usuario();
			rendEsconderCadastroUsuario = true;
			rendEsconderBtnAtualizarUsuario = false;
			rendEsconderBtnCadastrarUsuario = true;
		} else {
			rendEsconderCadastroUsuario = false;
			confirmarSenha = "";
		}
	}

	/*
	 * Metodo para abrir a "janela" de edicao com os dados preenchidos do usuario selecionado.
	 * Verifica se esta fechada, se estiver, abre com os dados do usuario selecionado, zerando o campo confirmar senha,
	 * escondendo o botao de cadastrar e desocultando o botao de atualizar.
	 * Se ja estiver aberta, ela ocultara ao clicar.
	 */
	public void editarNaTela(Usuario user) {

		if (rendEsconderCadastroUsuario == false) {
			usuario = user;
			confirmarSenha = "";
			rendEsconderCadastroUsuario = true;
			rendEsconderBtnAtualizarUsuario = true;
			rendEsconderBtnCadastrarUsuario = false;
		} else {
			rendEsconderCadastroUsuario = false;

		}
	}
	
	/*
	 * Metodo de atualizar um usuario. Primeiro valida os campos informados pelo usuário atraves do metodo 
	 * validarCadastroEdicaoUsuario, se tudo ok, chama o metodo de atualizar presente no DAO pelo controle, 
	 * passando os campos que estão na variável usuario e logo em seguida, zera o usuario.
	 * Esconde a janela, troca o botao de atualizar pelo de cadastro, exibe a mensagem de confirmacao pro usuario.
	 * 
	 */
	

	public void atualizar() {
		try {
			if (validarCadastroEdicaoUsuario()) {
				usuarioControle.atualizar(usuario);
				usuario = new Usuario();
				rendEsconderCadastroUsuario = false;
				rendEsconderBtnAtualizarUsuario = false;
				rendEsconderBtnCadastrarUsuario = true;
				mensagem = "Usuário atualizado com sucesso!";
				exibirMensagem.exibirDialog("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar atualizar usuário, entre em contato com o administrador!";
			exibirMensagem.exibirDialog("erro");
		}
	}
	
	/*
	 * Metodo de deletar um usuario. Faz a verificacao se a janela de cadastro/edicao esta aberta.
	 * Chama o metodo de deletar presente no DAO pelo controle passando o ID do usuario a ser deletado.
	 * Remove o usuario da lista, faz o "joguinho" de ocultar a janela de cadastro/edicao caso seja necessario, 
	 * ocultando tambem, a janela de cadastro/edicao do telefone e a lista do telefone,
	 * deletando todos os telefones cadastrados para o usuario selecionado acessando pela variavel telefoneBean nos metodos 
	 * presentes la e exibe a mensagem de confirmacao pro usuario.
	 * 
	 */

	public void deletar() {
		try {
			if (rendEsconderCadastroUsuario == true) {
				usuarioControle.deletar(usuario.getId());
				usuarios.remove(usuario);
				rendEsconderCadastroUsuario = false;
				rendEsconderBtnAtualizarUsuario = false;
				rendEsconderBtnCadastrarUsuario = true;
				telefoneBean.setRendEsconderCadastroTelefone(false);
				telefoneBean.setRendEsconderListaTelefone(false);
				telefoneBean.setTelefone(zerarTelefone);
				mensagem = "Usuário excluído com sucesso!";
				exibirMensagem.exibirDialog("confirmação");
			} else {
				usuarioControle.deletar(usuario.getId());
				usuarios.remove(usuario);
				telefoneBean.setTelefone(zerarTelefone);
				telefoneBean.setRendEsconderCadastroTelefone(false);
				telefoneBean.setRendEsconderListaTelefone(false);
				mensagem = "Usuário excluído com sucesso!";
				exibirMensagem.exibirDialog("confirmação");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar remover usuário, entre em contato com o administrador!";
			exibirMensagem.exibirDialog("erro");
		}
	}
	
	/*
	 * Método para listar os usuarios aproveitando o get da lista de usuarios.
	 * Acessa o metodo de listar presente no DAO pelo controle e lista todos os usuarios cadastrado no banco.
	 */
	public List<Usuario> getUsuarios() {
		try {
		return usuarioControle.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar listar usuários cadastrados, entre em contato com o administrador!";
			exibirMensagem.exibirDialog("erro");
			return usuarios;
		}
		
	}
	
	/*
	 * Método para verificar se o usuário logado e o admin, se for, colocara a string "admin" do lado do botao de deslogar,
	 * se não for, retornara o nome do usuario que está logado para colocar do lado do botao de deslogar.
	 */
	
	public String retornaNome() {

		if (USUARIOLogado.getNome() == null) {
			return "Admin, ";
		} else {
			return USUARIOLogado.getNome() + ", ";
		}
	}

	// Validar Cadastro/Edicao Usuário. Se passar em todas as validacoes, retornara true;

	public boolean validarCadastroEdicaoUsuario() {
		// Chama o metodo para converter para minusculo o email digitado pelo usuario.
		usuario.setEmail(Utilidade.converterEmailParaMinusculo(usuario.getEmail()));

		// Chama o metodo para verificar se o campo nome esta vazio, se sim, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarCampoVazio(usuario.getNome())) {
			mensagem = "O campo nome é obrigatório!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}

		// Chama o metodo para verificar se o campo nome tem no maximo 70 digitos, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarNomeMaximo70Digitos(usuario.getNome())) {
			mensagem = "Digite um nome com no máximo 70 caracteres!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}
		
		// Chama o metodo para verificar se no campo nome tem somente letras, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.validarNome(usuario.getNome())) {
			mensagem = "Só será aceito letras no nome!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}

		// Chama o metodo para verificar se o campo email esta vazio, se sim, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarCampoVazio(usuario.getEmail())) {
			mensagem = "O campo email é obrigatório!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}
		
		// Chama o metodo para verificar se o campo email tem no maximo 80 digitos, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarEmailMaximo80Digitos(usuario.getEmail())) {
			mensagem = "Digite um email com no máximo 80 caracteres!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}

		// Chama o metodo para verificar se o campo email é realmente um email, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.validarEmail(usuario.getEmail())) {
			mensagem = "Informe um email válido!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}
		
		// Chama o metodo para verificar se o campo senha esta vazio, se sim, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarCampoVazio(usuario.getSenha())) {
			mensagem = "O campo senha é obrigatório!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}
		// Chama o metodo para verificar se o campo senha tem no minimo 4 digitos, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarSenhaMinimo4Digitos(usuario.getSenha())) {
			mensagem = "Digite pelo menos 4 caracteres!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}
		
		// Chama o metodo para verificar se o campo senha tem no maximo 12 digitos, se nao, exibe a mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarSenhaMaximo12Digitos(usuario.getSenha())) {
			mensagem = "Digite no máximo 12 caracteres!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}

		// Chama o metodo para verificar se o campo senha e confirmar senha estao iguais, se nao, exibe mensagem informando pro usuario e retorna false;
		if (Utilidade.verificarSenhasIguais(usuario.getSenha(), confirmarSenha)) {
			mensagem = "As senhas informadas estão diferentes!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		}

		// Se passou em todas as validacoes, zera o campo confirmar senha e retorna true;
		confirmarSenha = "";
		return true;

	}

	/*
	 * Metodo para verificar se usuario ja esta cadastrado no banco atraves do email.
	 * Chama o metodo presente no DAO pelo controle, se ja estiver cadastrado, retornara false e exibira mensagem pro usuario, se nao, 
	 * retornara true;
	 */
	
	public boolean localVerificarSeUsuarioJaEstaCadastrado() {
		try {
		if (usuarioControle.verificaSeUsuarioEstaCadastrado(usuario.getEmail())) {
			mensagem = "Usuário já cadastrado!";
			exibirMensagem.exibirDialog("alerta");
			return false;
		} else {
			return true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
			mensagem = "Erro ao tentar verificar se usuário já está cadastrado, entre em contato com o administrador!";
			exibirMensagem.exibirDialog("erro");
			return false;
		}
		
	}
	
	// Metodo para abrir dialog no front de confirmacao de exclusao. Perguntando ao usuario se ele realmente quer deletar o usuario selecionado.

	public void abrirExclusao() {
		exibirMensagem.exibirDialog("ConfirmacaoExclusao");
	}

	// Getters e Setters
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isRendEsconderBtnAtualizarUsuario() {
		return rendEsconderBtnAtualizarUsuario;
	}

	public void setRendEsconderBtnAtualizarUsuario(boolean rendEsconderBtnAtualizarUsuario) {
		this.rendEsconderBtnAtualizarUsuario = rendEsconderBtnAtualizarUsuario;
	}

	public boolean isRendEsconderBtnCadastrarUsuario() {
		return rendEsconderBtnCadastrarUsuario;
	}

	public void setRendEsconderBtnCadastrarUsuario(boolean rendEsconderBtnCadastrarUsuario) {
		this.rendEsconderBtnCadastrarUsuario = rendEsconderBtnCadastrarUsuario;
	}

	public boolean isRendEsconderCadastroUsuario() {
		return rendEsconderCadastroUsuario;
	}

	public void setRendEsconderCadastroUsuario(boolean rendEsconderCadastroUsuario) {
		this.rendEsconderCadastroUsuario = rendEsconderCadastroUsuario;
	}

	public TelefoneBean getTelefoneBean() {
		return telefoneBean;
	}

	public void setTelefoneBean(TelefoneBean telefoneBean) {
		this.telefoneBean = telefoneBean;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Telefone getZerarTelefone() {
		return zerarTelefone;
	}

	public void setZerarTelefone(Telefone zerarTelefone) {
		this.zerarTelefone = zerarTelefone;
	}

	public Usuario getUSUARIOLogado() {
		return USUARIOLogado;
	}

	public void setUSUARIOLogado(Usuario uSUARIOLogado) {
		USUARIOLogado = uSUARIOLogado;
	}

}
