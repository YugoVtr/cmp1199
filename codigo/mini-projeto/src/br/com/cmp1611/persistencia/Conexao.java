package br.com.cmp1611.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String login = "root"; 
	private String senha = "aluno"; 
	private String host = "localhost:3306"; 
	private String dbName = "cmp1611"; 
	private String url = "jdbc:mysql://" + host + "/" + dbName; 
	private Connection conexao = null;
	
	public Conexao() {}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			this.conexao = (Connection) DriverManager.getConnection(url, login, senha);
		} catch (ClassNotFoundException e) {
			return null;
		} catch (SQLException e) {
			return null; 
		}
		return this.conexao; 
	}
	
}

