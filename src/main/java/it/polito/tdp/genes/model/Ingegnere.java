package it.polito.tdp.genes.model;

import java.util.PriorityQueue;

public class Ingegnere {
	private Genes gene;
	private PriorityQueue<Evento> queue;
	private int ingID;
	
	

	public Ingegnere(PriorityQueue<Evento> queue, int ingID) {
		super();
		this.queue = queue;
		this.ingID=ingID;
	}

	public Genes getGene() {
		return gene;
	}

	public void setGene(Genes gene) {
		this.gene = gene;
	}

	public PriorityQueue<Evento> getQueue() {
		return queue;
	}

	public void setQueue(PriorityQueue<Evento> queue) {
		this.queue = queue;
	}

	public int getIngID() {
		return ingID;
	}

	public void setIngID(int ingID) {
		this.ingID = ingID;
	}
	
	
	
	

}
