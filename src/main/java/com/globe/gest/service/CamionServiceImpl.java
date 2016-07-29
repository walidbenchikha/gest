package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.CamionDAO;
import com.globe.gest.dao.InstitutionDAO;
import com.globe.gest.model.Camion;
import com.globe.gest.model.Institution;


@Service
@Transactional
public class CamionServiceImpl implements CamionService {
	
	static Logger logger = LoggerFactory.getLogger(CamionServiceImpl.class);
	
	
	@Autowired
    private CamionDAO camionDAO;
	
	@Override
	public List<Camion> getCamion() {
		return camionDAO.getCamion();
	}
	
	
	
	

}
