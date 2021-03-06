package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.ShopsDAO;
import com.globe.gest.dao.VisiteDAO;
import com.globe.gest.model.Visite;

@Service
@Transactional
public class VisiteServiceImpl implements VisiteService {

	static Logger logger = LoggerFactory.getLogger(VisiteServiceImpl.class);

	@Autowired
	private VisiteDAO visiteDAO;

	@Override
	public void addVisite(Visite visite) {
		visiteDAO.addVisite(visite);
	}


	@Override
	public List<Visite> getVisite() {
		return visiteDAO.getVisite();
	}

	@Override
	public List<Visite> getVisited() {
		return visiteDAO.getVisited();
	}
//	@Override
//	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, String gouvernorat,
//			String ville, String localisation) {
//		return shopsDAO.getShopsByName(nom_audite, stype, operator, gouvernorat, ville, localisation);
//	}

	@Override
	public Visite getVisite(int id) {
		return visiteDAO.getVisite(id);
	}

	@Override
	public void deleteVisite(int id_visite) {
		visiteDAO.deleteVisite(id_visite);
	}

	@Override
	public void updateVisite(Visite visite) {
		visiteDAO.updateVisite(visite);
	}

}
