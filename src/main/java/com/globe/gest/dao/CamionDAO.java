package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.exception.DuplicateUserException;
import com.globe.gest.exception.UserNotFoundException;
import com.globe.gest.model.Audite;
import com.globe.gest.model.Camion;
import com.globe.gest.model.Institution;
import com.globe.gest.model.User;

public interface CamionDAO {
	
	 public void addCamion(Camion camion);

	    public Camion getCamion(int camionId);

	    public void updateCamion(Camion camion);

	    public void deleteCamion(int camionId);
	 public List<Camion> getCamion();

}
