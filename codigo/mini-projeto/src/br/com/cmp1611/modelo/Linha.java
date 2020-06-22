package br.com.cmp1611.modelo;

public class Linha extends ErroMensagem {
	private int numeroLinha; 
	private String descricao; 
	
	public Linha() {
		super();
	}
	
	public int getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
