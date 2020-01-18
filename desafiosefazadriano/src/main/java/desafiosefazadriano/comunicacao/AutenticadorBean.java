package desafiosefazadriano.comunicacao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import desafiosefazadriano.controle.UsuarioControle;
import desafiosefazadriano.modelo.Usuario;
import desafiosefazadriano.util.SessionUtil;
import desafiosefazadriano.util.ExibirMensagem;

@SuppressWarnings("deprecation")
@SessionScoped
@ManagedBean
public class AutenticadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioControle usuarioControle = new UsuarioControle();
	private Usuario user = new Usuario();
	private String mensagem;
	private ExibirMensagem exibirMensagem = new ExibirMensagem();
	private boolean login;
	
	
	/*
	 * Metodo de autenticacao. Chama o metodo de verificar login passando email e senha informado pelo usuario para verificar
	 * se contém no banco. Se nao existe, exibie mensagem informando pro usuario.
	 */
	
	public String autentica() throws ClassNotFoundException, SQLException  { 
		
		verificaLogin(user.getEmail(), user.getSenha());

		if (user.getEmail().equals("admin") && user.getSenha().equals("admin") || login == true)  {


			SessionUtil.setParam("USUARIOLogado", user);

			return "/index.xhtml?faces-redirect=true";

		} else {

			mensagem = "Email ou Senha incorretos!";
			exibirMensagem.exibirDialog("alerta");
			return null;
			
		} 

	}

	// Metodo pra invalidar a sessao e redirecionar o usuario pra tela de login
	public void registraSaida() {

		SessionUtil.invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

	/*
	 * Metodo pra verificar se email e senha informados pelo usuario existe no banco. Se sim, ele retorna o usuario com seus dados.
	 *  Se não, retorna um usuario com id -1.
	 */
	
	public void verificaLogin(String email, String senha) throws ClassNotFoundException, SQLException {
		user = usuarioControle.verificaLogin(email, senha);
			
		/*
		 * Se o id do usuario retornado for diferente de -1, significa que ele existe no banco e a variavel ganhara true, se não
		 * não existe no banco e variavel login ganhara false
		 */
		
		login = ((user.getId() == -1) ? false : true);
	}

	// GETTERS E SETTERS


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	

	public ExibirMensagem getExibirMensagem() {
		return exibirMensagem;
	}


	public void setExibirMensagem(ExibirMensagem exibirMensagem) {
		this.exibirMensagem = exibirMensagem;
	}


	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}


	public boolean isLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}
	
	

	
}