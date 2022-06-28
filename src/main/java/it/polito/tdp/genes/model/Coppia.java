package it.polito.tdp.genes.model;

public class Coppia {
	
	private Genes g1;
	private Genes g2;
	private double peso;
	public Coppia(Genes g1, Genes g2) {
		super();
		this.g1 = g1;
		this.g2 = g2;
	}
	public Genes getG1() {
		return g1;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public Genes getG2() {
		return g2;
	}
	public void setG2(Genes g2) {
		this.g2 = g2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
