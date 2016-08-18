package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Ville;

public interface VilleDAO {

//	public Operator getOperator(int id);

	public List<Ville> getVille(int gouvernorat);
	public int getVille(String ville);
	public int getGouvernorat(int ville);
	public List<Ville> getVilleTest(int i);
}
