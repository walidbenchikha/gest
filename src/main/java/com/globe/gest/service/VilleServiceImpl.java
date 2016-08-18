package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.VilleDAO;
import com.globe.gest.model.Ville;

@Service
@Transactional
public class VilleServiceImpl implements VilleService {
	static Logger logger = LoggerFactory.getLogger(VilleServiceImpl.class);

	@Autowired
	private VilleDAO villeDAO;

//	@Override
//	public Operator getOperator(int id) {
//		return operatorDAO.getOperator(id);
//	}
	
	@Override
    public List<Ville> getVille(int gouvernorat) {
        return villeDAO.getVille(gouvernorat);
    }
	
	@Override
    public int getVille(String ville) {
        return villeDAO.getVille(ville);
    }
	
	@Override
    public int getGouvernorat(int ville) {
        return villeDAO.getGouvernorat(ville);
    }

	@Override
	public List<Ville> getVilleTest(int i) {
		
		return villeDAO.getVilleTest(i);
	}
	

}
