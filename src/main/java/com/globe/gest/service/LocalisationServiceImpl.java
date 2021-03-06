package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.LocalisationDAO;
import com.globe.gest.model.Localisation;


@Service
@Transactional
public class LocalisationServiceImpl implements LocalisationService {
	static Logger logger = LoggerFactory.getLogger(LocalisationServiceImpl.class);

	@Autowired
	private LocalisationDAO localisationDAO;

	@Override
	public Localisation getLocalisation(int id) {
		return localisationDAO.getLocalisation(id);
	}
	
	@Override
    public List<Localisation> getLocalisations(int ville) {
        return localisationDAO.getLocalisations(ville);
    }
	
	@Override
	public int getLocalisation(String localisation) {
		return localisationDAO.getLocalisation(localisation);
	}
	
	@Override
	public int getVille(int localisation) {
		return localisationDAO.getVille(localisation);
	}


}
