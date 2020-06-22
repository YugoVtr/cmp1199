package br.com.cmp1611.modelo;

public class PontoDaLinha extends ErroMensagem {
	private int numeroLinha; 
	private int numeroPonto;
	private int posicaoNaLinha; 
	
	public PontoDaLinha() {
		super();
	}
	
	public int getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public int getNumeroPonto() {
		return numeroPonto;
	}

	public void setNumeroPonto(int numeroPonto) {
		this.numeroPonto = numeroPonto;
	}

	public int getPosicaoNaLinha() {
		return posicaoNaLinha;
	}

	public void setPosicaoNaLinha(int posicaoNaLinha) {
		this.posicaoNaLinha = posicaoNaLinha;
	}
}
