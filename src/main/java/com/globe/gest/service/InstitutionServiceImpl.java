package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.InstitutionDAO;
import com.globe.gest.model.Institution;


@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {
	
	static Logger logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);
	
	
	@Autowired
    private InstitutionDAO institutionDAO;
	
	@Override
	public void addInstitution(Institution institution) {
		institutionDAO.addInstitution(institution);
	}

    @Override
    public Institution getInstitution(int institutionId)  {
        return institutionDAO.getInstitution(institutionId);
    }

	@Override
	public void updateInstitution(Institution institution){
		institutionDAO.updateInstitution(institution);
	}

	@Override
	public void deleteInstitution(int institutionId)  {
		institutionDAO.deleteInstitution(institutionId);
	}
	
	@Override
	public List<Institution> getInstitution() {
		return institutionDAO.getInstitution();
	}
	
	
	
	

}
