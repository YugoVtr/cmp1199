package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Horario;
import br.com.cmp1611.modelo.Linha;
import br.com.cmp1611.persistencia.Conexao;

public class HorarioDao extends Conexao {
	public ArrayList<Horario> listarTodos() {
		String select = "SELECT * FROM HORARIO ORDER BY horario_parada ASC"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<Horario> listarPorPonto(int numeroPonto) {
		String select = "SELECT * FROM HORARIO WHERE numero_ponto = %s"; 
		String formatedSelect = String.format(select, numeroPonto);
		return this.listarTodosQuery(formatedSelect); 
	}
	
	public ArrayList<Horario> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Horario> horarios = new ArrayList<Horario>(); 
		Horario horario = new Horario(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				horario = new Horario(); 
				horario.setNumeroLinha( result.getInt("numero_linha") );
				horario.setNumeroPonto( result.getInt("numero_ponto") );
				horario.setHorarioParada( result.getString("horario_parada") );
				horarios.add( horario ); 
			}
			
		} catch (Exception e) {
			horarios = null;
		}
		
		return horarios; 
	}
	

	public Horario incluir(Horario horario) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO HORARIO (numero_linha, numero_ponto, horario_parada) VALUES (?, ?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setInt(1, horario.getNumeroLinha());
			prepare.setInt(2, horario.getNumeroPonto());
			prepare.setString(3, horario.getHorarioParada());
			
			prepare.executeUpdate(); 
			horario.setMensagem("Inclusão efetuada com sucesso");
			
			return horario;
			
		} catch (Exception e) {
			horario.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
	
	
	public Horario alterar(Horario horario) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String update = "UPDATE HORARIO SET horario_parada=? WHERE numero_linha=? AND numero_ponto=?"; 
			prepare = conn.prepareStatement(update); 
			
			prepare.setString(1, horario.getHorarioParada());
			prepare.setInt(2, horario.getNumeroLinha());
			prepare.setInt(3, horario.getNumeroPonto());
			
			prepare.execute(); 
			horario.setMensagem("Alteração efetuada com sucesso");
			
			return horario;
			
		} catch (Exception e) {
			horario.setMensagem("Problemas ao efetuar alteração. Revise as informações");
			return null; 
		}
	}
}
