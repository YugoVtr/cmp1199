package br.com.cmp1611.controle;

import java.sql.SQLException;
import java.util.ArrayList;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.cmp1611.dao.LinhaDao;
import br.com.cmp1611.dao.UsuarioDao;
import br.com.cmp1611.modelo.Linha;
import br.com.cmp1611.modelo.Usuario;

public class Gestor extends SelectorComposer<Window> {
	
	@Wire
	Listbox listPesquisar;
	
	@Wire
	Textbox descricao; 
	
	@Wire
	Intbox numeroLinha; 
	
	@Wire
	Textbox pesquisa;
	
	public void limparPesquisa() {
		this.pesquisa.setText("");
		this.listPesquisar.getItems().clear(); 
	}
	
	public void preencherPesquisa() {
		this.limparPesquisa(); 
		LinhaDao linhaDao = new LinhaDao(); 
		ArrayList<Linha> linhas = new ArrayList<Linha>();
		
		linhas = linhaDao.listarTodos(); 
		
		for(int i=0; i<linhas.size(); i++) {
			Listitem li = new Listitem(); 
			
			Listcell lc01 = new Listcell(); 
			lc01.setLabel(Integer.toString(linhas.get(i).getNumeroLinha()));
			
			Listcell lc02 = new Listcell(); 
			lc02.setLabel(linhas.get(i).getDescricao());
			
			li.appendChild(lc01); 
			li.appendChild(lc02); 
			this.listPesquisar.appendChild(li); 
		}
	}
	
	@Listen("onCreate=#linhaWindow")
	public void criarJanela() {			
		preencherPesquisa();
	}
	
	@Listen("onClick=#atualizarLista")
	public void clickAtualizar() {			
		preencherPesquisa();
	}

	@Listen("onClick=#limparLista")
	public void clickLimparPesquisa() {			
		limparPesquisa();
	}
	
	@Listen("onSelect=#listPesquisar") 
	public void selecionaLinha() {
		int indice = this.listPesquisar.getSelectedIndex(); 
		LinhaDao linhaDao = new LinhaDao(); 
		Linha linha = new Linha(); 
		
		if (indice >= 0) {
			Listcell lc = (Listcell) this.listPesquisar.getSelectedItem().getChildren().get(0); 
			linha = linhaDao.listarPorNumeroLinha( Integer.parseInt(lc.getLabel().toString()) ); 
			if( linha != null ) {
				this.numeroLinha.setText( Integer.toString(linha.getNumeroLinha()) );
				this.descricao.setText( linha.getDescricao() );
			}
		}
	}
	
	@Listen("onClick=#btnLimpar")
	public void limparFormulario() {			
		this.numeroLinha.setText( "" );
		this.descricao.setText( "" );
	}
	
	@Listen("onClick=#btnAlterar")
	public void alterar() throws SQLException {			
		LinhaDao linhaDao = new LinhaDao(); 
		Linha linha = new Linha(); 
		linha.setNumeroLinha( Integer.parseInt( this.numeroLinha.getText() ));
		linha.setDescricao(this.descricao.getText());
		linhaDao.alterar(linha); 
		Messagebox.show(linha.getMensagem(), "Concluido", Messagebox.OK, Messagebox.INFORMATION);
		this.limparFormulario();
		this.clickAtualizar(); 
	}
	
	@Listen("onClick=#btnIncluir")
	public void salvar() throws SQLException {			
		LinhaDao linhaDao = new LinhaDao(); 
		Linha linha = new Linha(); 
		linha.setNumeroLinha( Integer.parseInt( this.numeroLinha.getText() ));
		linha.setDescricao(this.descricao.getText());
		linhaDao.incluir(linha); 
		Messagebox.show(linha.getMensagem(), "Concluido", Messagebox.OK, Messagebox.INFORMATION);
		this.limparFormulario();
		this.clickAtualizar(); 
	}
	
	@Listen("onClick=#btnDeletar")
	public void deletar() throws SQLException {			
		LinhaDao linhaDao = new LinhaDao(); 
		Linha linha = new Linha(); 
		linha.setNumeroLinha( Integer.parseInt( this.numeroLinha.getText() ));
		linha.setDescricao(this.descricao.getText());
		linhaDao.deletar(linha); 
		Messagebox.show(linha.getMensagem(), "Concluido", Messagebox.OK, Messagebox.INFORMATION);
		this.limparFormulario();
		this.clickAtualizar(); 
	}
	
	@Listen("onBlur=#pesquisa")
	public void pesquisarPorDescricao() { 	
		String termo = this.pesquisa.getText(); 
		LinhaDao linhaDao = new LinhaDao(); 
		ArrayList<Linha> linhas = new ArrayList<Linha>();
		
		linhas = linhaDao.listarPorDescricao(termo); 
		this.limparPesquisa();
		for(int i=0; i<linhas.size(); i++) {
			Listitem li = new Listitem(); 
			
			Listcell lc01 = new Listcell(); 
			lc01.setLabel(Integer.toString(linhas.get(i).getNumeroLinha()));
			
			Listcell lc02 = new Listcell(); 
			lc02.setLabel(linhas.get(i).getDescricao());
			
			li.appendChild(lc01); 
			li.appendChild(lc02); 
			this.listPesquisar.appendChild(li); 
		}
	}
}
