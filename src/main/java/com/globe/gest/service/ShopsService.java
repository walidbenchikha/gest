package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Shops;

public interface ShopsService {

	public void addShops(Shops shops);

	public Shops getShops(int shopsId);

	public void updateShops(Shops shops);

	public void deleteShops(int shopsId);

	public List<Shops> getShops();

}
