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
	public List<Institution> getInstitution() {
		return institutionDAO.getInstitution();
	}
	
	
	
	

}
