package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Onibus;
import br.com.cmp1611.persistencia.Conexao;

public class OnibusDao extends Conexao {
	public ArrayList<Onibus> listarTodos() {
		String select = "SELECT * FROM ONIBUS ORDER BY numero_linha"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<Onibus> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Onibus> onibus = new ArrayList<Onibus>(); 
		Onibus onibus = new Onibus(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				onibus = new Onibus(); 
				onibus.setPlaca( result.getString("placa") );
				onibus.setDescricao( result.getString("descricao") );
				onibus.setNumeroLinha( result.getInt("numero_linha") );
				onibus.add( onibus ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			onibus = null;
		}
		
		return onibus; 
	}
	

	public Onibus incluir(Onibus onibus) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO ONIBUS (placa, descricao, numero_linha) VALUES (?, ?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setString(1, onibus.getPlaca());
			prepare.setString(2, onibus.getDescricao());
			prepare.setInt(3, onibus.getNumeroLinha());
			
			prepare.executeUpdate(); 
			onibus.setMensagem("Inclusão efetuada com sucesso");
			
			return onibus;
			
		} catch (Exception e) {
			onibus.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
	
	
	public Onibus alterar(Onibus onibus) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String update = "UPDATE ONIBUS SET descricao=?, numero_linha=? WHERE placa=?"; 
			prepare = conn.prepareStatement(update); 
			
			prepare.setString(1, onibus.getDescricao());
			prepare.setInt(2, onibus.getNumeroLinha());
			prepare.setString(3, onibus.getPlaca());
			
			prepare.execute(); 
			onibus.setMensagem("Alteração efetuada com sucesso");
			
			return onibus;
			
		} catch (Exception e) {
			onibus.setMensagem("Problemas ao efetuar alteração. Revise as informações");
			return null; 
		}
	}
}
