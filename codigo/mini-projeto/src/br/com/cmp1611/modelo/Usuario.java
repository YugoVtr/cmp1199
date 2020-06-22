package br.com.cmp1611.modelo;

public class Usuario extends ErroMensagem {
	private String email; 
	private String nome; 
	private String senha; 
	private int tipoUsuario;
	
	public Usuario() {
		super(); 
	}
	
	public Usuario(String email, String senha) {
		super(); 
		this.email = email; 
		this.senha = senha; 
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
