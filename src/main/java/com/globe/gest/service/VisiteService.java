package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Shops;
import com.globe.gest.model.Visite;

public interface VisiteService {

	public void addVisite(Visite visite);

	public List<Visite> getVisite();
	public List<Visite> getVisited();

	public Visite getVisite(int id);

	public void deleteVisite(int id_visite);

	public void updateVisite(Visite visite);

//	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, String gouvernorat,
//			String ville, String localisation);

}
