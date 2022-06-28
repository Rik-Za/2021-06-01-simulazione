package it.polito.tdp.genes.model;

public class Evento implements Comparable<Evento> {
	
	private int mese;
	private double prob;
	private int ingID;
	
	
	public Evento(int mese, double prob, int ingID) {
		super();
		this.mese = mese;
		this.prob = prob;
		this.ingID = ingID;
	}
	public int getMese() {
		return mese;
	}
	public void setMese(int mese) {
		this.mese = mese;
	}
	
	public double getProb() {
		return prob;
	}
	public void setProb(double prob) {
		this.prob = prob;
	}
	public int getIngID() {
		return ingID;
	}
	public void setIngID(int ingID) {
		this.ingID = ingID;
	}
	@Override
	public int compareTo(Evento o) {
		return this.mese-o.getMese();
	}
	
	
	
	

}
