package br.com.cmp1611.modelo;

import java.time.LocalTime;

public class Horario extends ErroMensagem {
	private int numeroLinha; 
	private int numeroPonto;
	private String horarioParada; 
	
	public Horario() {
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

	public String getHorarioParada() {
		return horarioParada;
	}

	public void setHorarioParada(String horarioParada) {
		this.horarioParada = horarioParada;
	}
}
