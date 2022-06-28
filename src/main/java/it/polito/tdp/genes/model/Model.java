package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private List<Genes> vertici;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private Map<String, Genes> idMap;
	private List<Coppia> archi;
	
	public Model() {
		this.dao= new GenesDao();
		this.idMap= new HashMap<>();
		
	}
	
	public String creaGrafo() {
		this.grafo= new SimpleWeightedGraph<Genes, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		//aggiungo vertici
		this.vertici= new ArrayList<>(this.dao.getVertici(idMap));
		Graphs.addAllVertices(this.grafo, this.vertici);
		//aggiungo archi
		this.archi= new ArrayList<>(this.dao.getArchi(idMap));
		for(Coppia c: archi)
			Graphs.addEdge(this.grafo, c.getG1(), c.getG2(), c.getPeso());
		String s="GRAFO CREATO!\n";
		s+="#VERTICI: "+this.grafo.vertexSet().size()+"\n";
		s+="#ARCHI: "+this.grafo.edgeSet().size()+"\n";
		
		return s;
		
	}
	
	public List<Genes> getVertici(){
		Collections.sort(this.vertici);
		return this.vertici;
	}
	
	public List<Adiacenza> getAdiacenti(Genes genes) {
		List<Adiacenza> ris = new ArrayList<>();
		for(Genes g: Graphs.neighborListOf(this.grafo, genes)) {
			DefaultWeightedEdge e = this.grafo.getEdge(genes, g);
			Adiacenza a = new Adiacenza(g, this.grafo.getEdgeWeight(e));
			ris.add(a);
		}
		Collections.sort(ris);
		return ris;
			
	}
	
	public Map<String,Genes> simula(Genes g, int n){
		Simulatore sim = new Simulatore(grafo);
		sim.init(g, n);
		sim.run();
		return sim.getRisultato();
	}
		
	
	
}
