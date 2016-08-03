package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.CamionDAO;
import com.globe.gest.model.Camion;


@Service
@Transactional
public class CamionServiceImpl implements CamionService {
	
	static Logger logger = LoggerFactory.getLogger(CamionServiceImpl.class);
	
	
	@Autowired
    private CamionDAO camionDAO;
	
	@Override
	public void addCamion(Camion camion) {
		camionDAO.addCamion(camion);
	}

    @Override
    public Camion getCamion(int camionId)  {
        return camionDAO.getCamion(camionId);
    }

	@Override
	public void updateCamion(Camion camion){
		camionDAO.updateCamion(camion);
	}

	@Override
	public void deleteCamion(int camionId)  {
		camionDAO.deleteCamion(camionId);
	}
	
	@Override
	public List<Camion> getCamion() {
		return camionDAO.getCamion();
	}
	
	
	
	

}
