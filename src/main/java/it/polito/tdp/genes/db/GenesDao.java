package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Coppia;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genes> getVertici(Map<String,Genes> idMap){
		String sql = "SELECT DISTINCT (GeneID), Essential, Chromosome "
				+ "FROM genes "
				+ "WHERE Essential='Essential' ";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
				if(!idMap.containsKey(genes.getGeneId()))
					idMap.put(genes.getGeneId(), genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Coppia> getArchi(Map<String,Genes> idMap){
		String sql = "SELECT i.GeneID1 AS g1, i.GeneID2 AS g2, g1.Chromosome AS c1, g2.Chromosome AS c2, i.Expression_Corr AS peso "
				+ "FROM genes g1, genes g2, interactions i "
				+ "WHERE g1.GeneID=i.GeneID1 AND g2.GeneID=i.GeneID2 AND g1.Essential='Essential' AND g2.Essential='Essential' AND i.GeneID1<>i.GeneID2 "
				+ "GROUP BY g1, g2, c1, c2, peso ";
		List<Coppia> result = new ArrayList<Coppia>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Genes g1 = idMap.get(res.getString("g1"));
				Genes g2 = idMap.get(res.getString("g2"));
				Coppia c= new Coppia(g1, g2);
				double peso=0;
				if(g1.getChromosome()==g2.getChromosome())
					peso=2*Math.abs(res.getDouble("peso"));
				else
					peso=Math.abs(res.getDouble("peso"));
				c.setPeso(peso);
				result.add(c);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	
}
