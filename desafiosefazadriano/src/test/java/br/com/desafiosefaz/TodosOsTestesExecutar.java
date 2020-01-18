package br.com.desafiosefaz;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AutenticadorTest.class, CadastrarTelefoneTest.class, EditarRemoverListarTelefoneTest.class, EditarUsuarioTest.class, 
	ListarTodosUsuariosTest.class, RemoverUsuarioTest.class, TelefoneValidacaoCadastroEdicaoMetodosTest.class, 
	UsuarioValidacaoCadastroEdicaoMetodosTest.class})
public class TodosOsTestesExecutar {
	

}
