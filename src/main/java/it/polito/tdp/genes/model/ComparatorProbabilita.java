package it.polito.tdp.genes.model;

import java.util.Comparator;

public class ComparatorProbabilita implements Comparator<Adiacenza> {

	@Override
	public int compare(Adiacenza o1, Adiacenza o2) {
		return Double.compare(o1.getProbabilità(), o2.getProbabilità());
	}

}
