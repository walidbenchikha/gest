package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Shops;

public interface ShopsDAO {

	public void addShops(Shops shops);

	public Shops getShops(int shopsId);

	public void updateShops(Shops shops);

	public void deleteShops(int shopsId);

	public List<Shops> getShops();

	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, int gouvernorat,
			int ville, int localisation);

	public List<Shops> findShopsMarkers(String operator);

}
