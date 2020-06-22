package br.com.cmp1611.controle;

import java.util.ArrayList;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.cmp1611.dao.HorarioDao;
import br.com.cmp1611.dao.PontoDao;
import br.com.cmp1611.modelo.Horario;
import br.com.cmp1611.modelo.Ponto;

public class Passageiro extends SelectorComposer<Window> {

	@Wire
	Listbox listboxPontos;
	
	@Wire
	Listbox listPesquisar;

	@Listen("onChange=#listboxPontos")
	public void buscarHorarios() {			
		preencherPesquisaPontos();
	}
	
	@Listen("onCreate=#windowConsultar")
	public void criarJanela() {			
		preencherPesquisaPontos();
	}

	public void preencherPesquisaPontos() {
		PontoDao pontoDao = new PontoDao();
		ArrayList<Ponto> listaPontos = new ArrayList<Ponto>();
		listaPontos = pontoDao.listarTodos();
		
		for (int i=0; i<listaPontos.size(); i++) {
			Listitem li = (Listitem) new Listitem();
			Listcell lca = (Listcell) new Listcell( Integer.toString( listaPontos.get(i).getNumeroPonto() ));
			li.appendChild(lca);
			this.listboxPontos.appendChild(li); 
		}		
	}
	
	public void limparPesquisa() {
		this.listPesquisar.getItems().clear(); 
	}
	
	@Listen("onClick=#atualizarLista")
	public void clickAtualizar() {			
		preencherPesquisa();
	}
	
	@Listen("onClick=#limparLista")
	public void btnLimpar() {			
		limparPesquisa();
	}
	
	public void preencherPesquisa() {
		this.limparPesquisa();
		HorarioDao horarioDao = new HorarioDao(); 
		ArrayList<Horario> horarios = new ArrayList<Horario>();
		
		Listcell lc = (Listcell) listboxPontos.getSelectedItem().getChildren().get(0); 
		int ponto = Integer.parseInt(lc.getLabel().toString());  
		
		if( ponto > 0) {
			horarios = horarioDao.listarPorPonto( ponto ); 
			for(int i=0; i<horarios.size(); i++) {
				Listitem li = new Listitem(); 
				
				Listcell lc01 = new Listcell(); 
				lc01.setLabel( Integer.toString( horarios.get(i).getNumeroLinha() ));
				
				Listcell lc02 = new Listcell(); 
				lc02.setLabel(horarios.get(i).getHorarioParada());
				
				li.appendChild(lc01); 
				li.appendChild(lc02); 
				listPesquisar.appendChild(li); 
			}

		} else {
			Messagebox.show("Selecione um ponto", "Espere!", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
}
