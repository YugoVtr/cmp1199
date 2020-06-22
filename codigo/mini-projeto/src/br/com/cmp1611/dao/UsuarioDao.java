package br.com.cmp1611.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.cmp1611.modelo.Usuario;
import br.com.cmp1611.persistencia.Conexao;

public class UsuarioDao extends Conexao {
	public ArrayList<Usuario> listarTodos() {
		String select = "SELECT * FROM USUARIO"; 
		return this.listarTodosQuery(select); 
	}
	
	public ArrayList<Usuario> listarTodosQuery(String select) {
		Connection conexao = this.getConnection(); 
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); 
		Usuario usuario = new Usuario(); 
		
		try {
			
			Statement statement = conexao.createStatement(); 
			ResultSet result = statement.executeQuery(select); 
			
			while (result.next()) {
				usuario = new Usuario(); 
				usuario.setEmail( result.getString("email") );
				usuario.setNome( result.getString("nome") );
				usuario.setSenha( result.getString("senha") );
				usuarios.add( usuario ); 
			}
			
		} catch (Exception e) {
			System.out.println(e);
			usuarios = null;
		}
		
		return usuarios; 
	}
	
	public Boolean auth(Usuario usr) { 
		String select = "SELECT * FROM USUARIO WHERE email = '%s' AND senha = '%s'"; 
		
		String hash = getSHA512(usr.getSenha()); 
		String formatedSelect = String.format(select, usr.getEmail(), hash);
		
		ArrayList<Usuario> res = this.listarTodosQuery(formatedSelect); 
		usr.setMensagem("Não encontrado");
		return res.size() > 0 ;
	}
	
    private String getSHA512(String input){
    	String toReturn = null;
    	
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-512");
		    digest.reset();
		    digest.update(input.getBytes("utf8"));
		    toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	
		return toReturn;
    }
}
