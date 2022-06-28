package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	
	//Modello
	private Graph<Genes, DefaultWeightedEdge> grafo;
	
	//Parametri simulazione
	private int numeroIng;
	private Genes geneInizio;
	private Map<Integer,Ingegnere> ingegneri;
	
	//Coda degli eventi ---------------------> Assegnata a ciascun ingegnere
	//private PriorityQueue<Evento> queue;
	
	//Output
	private Map<String,Genes> risultato;
	
	public Simulatore(Graph<Genes, DefaultWeightedEdge> grafo) {
		this.grafo=grafo;
		this.risultato=new LinkedHashMap<>();
		this.ingegneri=new HashMap<>();
	}
	
	public void init(Genes g, int n) {
		this.risultato.clear();
		this.geneInizio=g;
		this.numeroIng=n;
		this.risultato.put(geneInizio.getGeneId(), geneInizio);
		this.ingegneri.clear();
		for(Genes gen: this.grafo.vertexSet())
			gen.setIngAllocati(0);
		for(int i=0; i<numeroIng;i++) {
			Ingegnere ing = new Ingegnere(new PriorityQueue<Evento>(), i);
			ing.setGene(geneInizio);
			this.ingegneri.put(i, ing);
		}
		for(int i=0;i<36;i++) {
			for(Ingegnere in: ingegneri.values()) {
				double prob = Math.random();
				Evento e = new Evento(i, prob,in.getIngID());
				in.getQueue().add(e);
			}
				
		}	
	}
	
	public void run() {
		for(Ingegnere in: this.ingegneri.values()) {
			while(!in.getQueue().isEmpty()) {
				Evento e = in.getQueue().poll();
				processEvent(e);
			}
		}
		for(Ingegnere in: this.ingegneri.values())
			risultato.get(in.getGene().getGeneId()).incrementaIngAllocati();
	}

	private void processEvent(Evento e) {
		Genes gg=ingegneri.get(e.getIngID()).getGene();
		if(e.getProb()>0.30) {
			List<Adiacenza> vicini = trovaVicini(gg);
			double prob= Math.random();
			double cont=0;
			for(Adiacenza a: vicini) {
				if(cont<prob && prob<cont+a.getProbabilità()) {
					gg=a.getG();
					ingegneri.get(e.getIngID()).setGene(gg);
					if(!risultato.containsKey(gg.getGeneId()));
						risultato.put(gg.getGeneId(),gg);
					return;
				}else
					cont=a.getProbabilità();
			}
		}
	}

	private List<Adiacenza> trovaVicini(Genes gene) {
		List<Adiacenza> ris = new ArrayList<>();
		double tot=0;
		for(Genes g: Graphs.neighborListOf(this.grafo, gene))
			tot+=this.grafo.getEdgeWeight(this.grafo.getEdge(gene, g));
		for(Genes g: Graphs.neighborListOf(this.grafo, gene)) {
			DefaultWeightedEdge e = this.grafo.getEdge(gene, g);
			Adiacenza a = new Adiacenza(g, this.grafo.getEdgeWeight(e));
			a.setProbabilità(this.grafo.getEdgeWeight(e)/tot);
			ris.add(a);
		}
		Collections.sort(ris, new ComparatorProbabilita());
		return ris;
	}
	
	public Map<String,Genes> getRisultato(){
		return this.risultato;
	}

}
