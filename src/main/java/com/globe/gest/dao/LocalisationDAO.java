package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Localisation;


public interface LocalisationDAO {

	public Localisation getLocalisation(int id);
	public List<Localisation> getLocalisations(int ville);
	public int getLocalisation(String localisation);
	public int getVille(int localisation);

}
