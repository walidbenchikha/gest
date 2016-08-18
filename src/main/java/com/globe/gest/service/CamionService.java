package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Camion;

public interface CamionService {

	public void addCamion(Camion camion);

	public Camion getCamion(int camionId);

	public void updateCamion(Camion camion);

	public void deleteCamion(int camionId);

	public List<Camion> getCamion();

	public List<Camion> getCamionByName(String nom_audite, String operator, int gouvernorat, int ville,
			int localisation);

}
