package com.globe.gest.service;

import java.util.List;


import com.globe.gest.model.Institution;

public interface InstitutionService {
	
	public void addInstitution(Institution institution);

    public Institution getInstitution(int institutionId);

    public void updateInstitution(Institution institution);
    
    public void deleteInstitution(int institutionId);
	
	public List<Institution> getInstitution();

	public List<Institution> getInstitutionByName(String nom_audite, String operator, String gouvernorat, String ville,
			String localisation);

}
