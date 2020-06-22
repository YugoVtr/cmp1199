package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.PontoDaLinha;
import br.com.cmp1611.persistencia.Conexao;

public class PontoDaLinhaDao extends Conexao {
	public ArrayList<PontoDaLinha> listarTodos() {
		String select = "SELECT * FROM PONTO_DA_LINHA ORDER BY posicao_na_linha ASC"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<PontoDaLinha> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<PontoDaLinha> pontosDasLinhas = new ArrayList<PontoDaLinha>(); 
		PontoDaLinha pontoDaLinha = new PontoDaLinha(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				pontoDaLinha = new PontoDaLinha(); 
				pontoDaLinha.setNumeroLinha( result.getInt("numero_linha") );
				pontoDaLinha.setNumeroPonto( result.getInt("numero_ponto") );
				pontoDaLinha.setPosicaoNaLinha( result.getInt("posicao_na_linha") );
				pontosDasLinhas.add( pontoDaLinha ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			pontosDasLinhas = null;
		}
		
		return pontosDasLinhas; 
	}
	

	public PontoDaLinha incluir(PontoDaLinha pontoDaLinha) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO PONTO_DA_LINHA (numero_linha, numero_ponto, posicao_na_linha) VALUES (?, ?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setInt(1, pontoDaLinha.getNumeroLinha());
			prepare.setInt(2, pontoDaLinha.getNumeroPonto());
			prepare.setInt(3, pontoDaLinha.getPosicaoNaLinha());
			
			prepare.executeUpdate(); 
			pontoDaLinha.setMensagem("Inclusão efetuada com sucesso");
			
			return pontoDaLinha;
			
		} catch (Exception e) {
			pontoDaLinha.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
	
	
	public PontoDaLinha alterar(PontoDaLinha pontoDaLinha) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String update = "UPDATE PONTO_DA_LINHA SET posicao_na_linha=? WHERE numero_linha=? AND numero_ponto=?"; 
			prepare = conn.prepareStatement(update); 
			
			prepare.setInt(1, pontoDaLinha.getPosicaoNaLinha());
			prepare.setInt(2, pontoDaLinha.getNumeroLinha());
			prepare.setInt(3, pontoDaLinha.getNumeroPonto());
			
			prepare.execute(); 
			pontoDaLinha.setMensagem("Alteração efetuada com sucesso");
			
			return pontoDaLinha;
			
		} catch (Exception e) {
			pontoDaLinha.setMensagem("Problemas ao efetuar alteração. Revise as informações");
			return null; 
		}
	}
}
