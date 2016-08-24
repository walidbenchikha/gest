package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.ShopsDAO;
import com.globe.gest.model.Audite;
import com.globe.gest.model.STAT1;
import com.globe.gest.model.Shops;

@Service
@Transactional
public class ShopsServiceImpl implements ShopsService {

	static Logger logger = LoggerFactory.getLogger(ShopsServiceImpl.class);

	@Autowired
	private ShopsDAO shopsDAO;

	@Override
	public void addShops(Shops shops) {
		shopsDAO.addShops(shops);
	}

	@Override
	public Shops getShops(int shopsId) {
		return shopsDAO.getShops(shopsId);
	}

	@Override
	public void updateShops(Shops shops) {
		shopsDAO.updateShops(shops);
	}

	@Override
	public void deleteShops(int shopsId) {
		shopsDAO.deleteShops(shopsId);
	}

	@Override
	public List<Shops> getShops() {
		return shopsDAO.getShops();
	}

	@Override
	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, int gouvernorat,
			int ville, int localisation) {
		return shopsDAO.getShopsByName(nom_audite, stype, operator, gouvernorat, ville, localisation);
	}

//	@Override
//		public List<Shops> findShopsMarkers(String operator, String auditor) {
//			
//	return shopsDAO.findShopsMarkers(operator,auditor);
//			
//		}
//	
	
	@Override
	public List<Audite> findShopsMarkers(String operator, String auditor) {
		
return shopsDAO.findShopsMarkers(operator,auditor);
		
	}

@Override
public List<STAT1> findSTAT1(String operator, String auditor) {
	System.out.println("fara7  ");
	return shopsDAO.findSTAT1(operator, auditor);
}
	
	

}
