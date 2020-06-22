package br.com.cmp1611.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Consulta;
import br.com.cmp1611.persistencia.Conexao;

public class ConsultaDao extends Conexao {
	public ArrayList<Consulta> listarTodos() {
		String select = "SELECT * FROM CONSULTA ORDER BY data_hora_consulta DESC"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<Consulta> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Consulta> consultas = new ArrayList<Consulta>(); 
		Consulta consulta = new Consulta(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				consulta = new Consulta(); 
				consulta.setId( result.getInt("id") );
				consulta.setEmail( result.getString("email") );
				consulta.setDataHoraConsulta( result.getString("data_hora_consulta") );
				consulta.setNumeroPonto( result.getInt("numero_ponto") );
				consultas.add( consulta ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			consultas = null;
		}
		
		return consultas; 
	}
	

	public Consulta incluir(Consulta consulta) throws SQLException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement prepare;
			
			String insert = "INSERT INTO CONSULTA (email, data_hora_consulta, numero_ponto) VALUES (?, ?, ?)"; 
			prepare = conn.prepareStatement(insert); 
			prepare.setString(1, consulta.getEmail());
			prepare.setString(2, consulta.getDataHoraConsulta());
			prepare.setInt(3, consulta.getNumeroPonto());
			
			prepare.executeUpdate(); 
			consulta.setMensagem("Inclusão efetuada com sucesso");
			
			return consulta;
			
		} catch (Exception e) {
			consulta.setMensagem("Não deu pra inserir, Tente novamente com novos dados.");
			return null; 
		}
	}
}
