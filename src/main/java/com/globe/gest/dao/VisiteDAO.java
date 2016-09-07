package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Visite;

public interface VisiteDAO {

	public void addVisite(Visite visite);
//
//	public Shops getShops(int shopsId);
//
//	public void updateShops(Shops shops);
//
//	public void deleteShops(int shopsId);

	public List<Visite> getVisite();
	
	public List<Visite> getVisited();

	public Visite getVisite(int id);

	public void deleteVisite(int id_visite);

	public void updateVisite(Visite visite);

//	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, String gouvernorat,
//			String ville, String localisation);

}
