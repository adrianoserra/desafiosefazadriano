package desafiosefazadriano.util;


import org.primefaces.PrimeFaces;

public class ExibirMensagem {
	
	// Metodo pra chamar o dialog especifico no front para exibir uma mensagem relacionado ao usuarioBean 
	public void exibirDialog(String tipo) {
		PrimeFaces current = PrimeFaces.current();
		if (tipo.equals("alerta")) {
			current.executeScript("PF('DialogVarAlerta').show();");
		}
		if (tipo.equals("ConfirmacaoExclusao")) {
			current.executeScript("PF('DialogVarAlertaExclusao').show();");
		}
		if (tipo.equals("confirmação")) {
			current.executeScript("PF('DialogVarConfirmacao').show();");
		}
		if (tipo.equals("erro")) {
			current.executeScript("PF('DialogVarErro').show();");
		}
	}
	// Metodo pra chamar o dialog especifico no front para exibir uma mensagem relacionado ao telefoneBean
	public void exibirDialogTelefone(String tipo) {
		PrimeFaces current = PrimeFaces.current();
		if (tipo.equals("alerta")) {
			current.executeScript("PF('DialogVarAlertaTelefone').show();");
		}
		if (tipo.equals("ConfirmacaoExclusao")) {
			current.executeScript("PF('DialogVarAlertaExclusaoTelefone').show();");
		}
		if (tipo.equals("confirmação")) {
			current.executeScript("PF('DialogVarConfirmacaoTelefone').show();");
		}
		if (tipo.equals("erro")) {
			current.executeScript("PF('DialogVarErroTelefone').show();");
		}
	}

	

}
