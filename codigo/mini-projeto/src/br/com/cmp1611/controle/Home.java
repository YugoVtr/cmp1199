package br.com.cmp1611.controle;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.cmp1611.dao.UsuarioDao;
import br.com.cmp1611.modelo.Usuario;

public class Home extends SelectorComposer<Window> {
	
	@Wire
	Button passageiro; 
	
	@Wire
	Button gestor; 
	
	@Wire
	Textbox email; 
	
	@Wire
	Textbox senha; 
	
	
	@Listen("onClick=#passageiro")
	public void selectPassageiro()
	{ 
		Executions.sendRedirect("/consultar.zul");
	}
	
	@Listen("onClick=#gestor")
	public void selectGestor()
	{ 
		Executions.sendRedirect("/login.zul");
	}
	
	@Listen("onClick=#submit")
	public void submitGestor()
	{ 	
		Usuario usr = new Usuario(email.getText(), senha.getText());
		UsuarioDao dao = new UsuarioDao(); 
		
		if( dao.auth(usr) ) {
			Executions.sendRedirect("/gestor.zul");
		} else {
			Messagebox.show(usr.getMensagem(), "Usuario Invalido", Messagebox.OK, Messagebox.INFORMATION); 
		}
	}
}
