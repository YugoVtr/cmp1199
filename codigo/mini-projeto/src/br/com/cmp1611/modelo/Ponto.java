package br.com.cmp1611.modelo;

public class Ponto extends ErroMensagem { 
	private int numeroPonto; 
	private String endereco; 
	private String pontoReferencia;
	
	public Ponto() {
		super();
	}
	
	public int getNumeroPonto() {
		return numeroPonto;
	}
	public void setNumeroPonto(int numeroPonto) {
		this.numeroPonto = numeroPonto;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	} 
}
