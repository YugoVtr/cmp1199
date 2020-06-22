package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Ponto;
import br.com.cmp1611.persistencia.Conexao;

public class PontoDao extends Conexao {
	public ArrayList<Ponto> listarTodos() {
		String select = "SELECT * FROM PONTO ORDER BY numero_ponto"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<Ponto> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Ponto> pontos = new ArrayList<Ponto>(); 
		Ponto ponto = new Ponto(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				ponto = new Ponto(); 
				ponto.setNumeroPonto( result.getInt("numero_ponto") );
				ponto.setEndereco( result.getString("endereco") );
				ponto.setPontoReferencia( result.getString("ponto_referencia") );
				pontos.add( ponto ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			pontos = null;
		}
		
		return pontos; 
	}
	

	public Ponto incluir(Ponto ponto) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO PONTO (numero_ponto, endereco, ponto_referencia) VALUES (?, ?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setInt(1, ponto.getNumeroPonto());
			prepare.setString(2, ponto.getEndereco());
			prepare.setString(3, ponto.getPontoReferencia());
			
			prepare.executeUpdate(); 
			ponto.setMensagem("Inclusão efetuada com sucesso");
			
			return ponto;
			
		} catch (Exception e) {
			ponto.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
	
	
	public Ponto alterar(Ponto ponto) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String update = "UPDATE PONTO SET endereco=?, ponto_referencia=? WHERE numero_ponto=?"; 
			prepare = conn.prepareStatement(update); 
			
			prepare.setString(1, ponto.getEndereco());
			prepare.setString(2, ponto.getPontoReferencia());
			prepare.setInt(3, ponto.getNumeroPonto());
			
			prepare.execute(); 
			ponto.setMensagem("Alteração efetuada com sucesso");
			
			return ponto;
			
		} catch (Exception e) {
			ponto.setMensagem("Problemas ao efetuar alteração. Revise as informações");
			return null; 
		}
	}
}
