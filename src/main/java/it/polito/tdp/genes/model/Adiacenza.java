package it.polito.tdp.genes.model;

public class Adiacenza implements Comparable<Adiacenza> {
	
	private Genes g;
	private double peso;
	private double probabilità;
	public Adiacenza(Genes g, double peso) {
		super();
		this.g = g;
		this.peso = peso;
	}
	public Genes getG() {
		return g;
	}
	public void setG(Genes g) {
		this.g = g;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	public double getProbabilità() {
		return probabilità;
	}
	public void setProbabilità(double probabilità) {
		this.probabilità = probabilità;
	}
	@Override
	public String toString() {
		return g.getGeneId() +" "+peso;
	}
	@Override
	public int compareTo(Adiacenza o) {
		return -Double.compare(this.peso, o.peso);
	}
	
	

}
