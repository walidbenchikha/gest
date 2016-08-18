package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Institution;

public interface InstitutionDAO {

	public void addInstitution(Institution institution);

	public Institution getInstitution(int institutionId);

	public void updateInstitution(Institution institution);

	public void deleteInstitution(int institutionId);

	public List<Institution> getInstitution();

	public List<Institution> getInstitutionByName(String nom_audite, String operator, int gouvernorat, int ville,
			int localisation);

}
