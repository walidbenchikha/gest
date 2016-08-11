package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Shops;
import com.globe.gest.model.User;

public interface ShopsDAO {

	public void addShops(Shops shops);

	public Shops getShops(int shopsId);

	public void updateShops(Shops shops);

	public void deleteShops(int shopsId);

	public List<Shops> getShops();
	 public List<Shops> getShopsByName(String nom_audite, String stype);

}
