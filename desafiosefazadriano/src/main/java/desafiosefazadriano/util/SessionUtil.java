package desafiosefazadriano.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static HttpSession getSession() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		// Captura a sessao
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(
				false);
		return sessao;
	}

	// Metodo pra alterar o atributo da sessao
	public static void setParam(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	// Metodo pra capturar o atributo da sessao
	public static Object getParam(String key) {
		return getSession().getAttribute(key);
	}

	// Metodo pra remover o atributo da sessao
	public static void remove(String key) {
		getSession().removeAttribute(key);
	}

	// Metodo pra invalidar o atributo da sessao
	public static void invalidate() {
		getSession().invalidate();
	}
}
