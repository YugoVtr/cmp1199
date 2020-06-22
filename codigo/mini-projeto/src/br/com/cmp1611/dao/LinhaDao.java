package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Linha;
import br.com.cmp1611.persistencia.Conexao;

public class LinhaDao extends Conexao {
	public ArrayList<Linha> listarTodos() {
		String select = "SELECT * FROM LINHA ORDER BY numero_linha"; 
		return this.listarTodosQuery(select); 
	}
	
	public Linha listarPorNumeroLinha(int numeroLinha) {
		String select = "SELECT * FROM LINHA WHERE numero_linha = %s"; 
		String formatedSelect = String.format(select, numeroLinha);
		ArrayList<Linha> linhas = this.listarTodosQuery(formatedSelect); 
		return linhas.get(0); 
	}
	
	public ArrayList<Linha> listarPorDescricao(String descricao) {
		String select = "SELECT * FROM LINHA WHERE descricao LIKE '%"+ descricao +"%'"; 
		return this.listarTodosQuery(select);  
	}
	
	public ArrayList<Linha> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Linha> linhas = new ArrayList<Linha>(); 
		Linha linha = new Linha(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				linha = new Linha(); 
				linha.setNumeroLinha( result.getInt("numero_linha") );
				linha.setDescricao( result.getString("descricao") );
				linhas.add( linha ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			linhas = null;
		}
		
		return linhas; 
	}
	

	public Linha incluir(Linha linha) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO LINHA (numero_linha, descricao) VALUES (?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setInt(1, linha.getNumeroLinha());
			prepare.setString(2, linha.getDescricao());
			
			prepare.executeUpdate(); 
			linha.setMensagem("Inclusão efetuada com sucesso");
			
			return linha;
			
		} catch (Exception e) {
			linha.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
	
	
	public Linha alterar(Linha linha) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String update = "UPDATE LINHA SET descricao=? WHERE numero_linha=?"; 
			prepare = conn.prepareStatement(update); 
			
			prepare.setString(1, linha.getDescricao());
			prepare.setInt(2, linha.getNumeroLinha());
			
			prepare.execute(); 
			linha.setMensagem("Alteração efetuada com sucesso");
			
			return linha;
			
		} catch (Exception e) {
			linha.setMensagem("Problemas ao efetuar alteração. Revise as informações");
			return null; 
		}
	}
	
	public Linha deletar(Linha linha) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String delete = "DELETE FROM LINHA WHERE numero_linha=?"; 
			prepare = conn.prepareStatement(delete); 
			
			prepare.setInt(1, linha.getNumeroLinha());
			
			prepare.execute(); 
			linha.setMensagem("Delete feito com sucesso");
			
			return linha;
			
		} catch (Exception e) {
			linha.setMensagem("Problemas ao efetuar a remoção");
			return null; 
		}
	}
}