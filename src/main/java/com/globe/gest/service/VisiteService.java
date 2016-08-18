package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Shops;
import com.globe.gest.model.Visite;

public interface VisiteService {

	public void addVisite(Visite visite);
//
//	public Shops getShops(int shopsId);
//
//	public void updateShops(Shops shops);
//
//	public void deleteShops(int shopsId);

	public List<Visite> getVisite();
	public List<Visite> getVisited();

//	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, String gouvernorat,
//			String ville, String localisation);

}
