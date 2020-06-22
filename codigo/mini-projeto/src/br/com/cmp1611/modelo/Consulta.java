package br.com.cmp1611.modelo;

public class Consulta extends ErroMensagem {

	private int id; 
	private String email; 
	private String dataHoraConsulta; 
	private int numeroPonto; 
	
	public Consulta() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDataHoraConsulta() {
		return dataHoraConsulta;
	}
	
	public void setDataHoraConsulta(String dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}
	
	public int getNumeroPonto() {
		return numeroPonto;
	}

	public void setNumeroPonto(int numeroPonto) {
		this.numeroPonto = numeroPonto;
	}
}
