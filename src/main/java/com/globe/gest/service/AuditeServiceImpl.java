package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.AuditeDAO;
import com.globe.gest.model.Audite;
import com.globe.gest.model.Operator;


@Service
@Transactional
public class AuditeServiceImpl implements AuditeService {
	
	static Logger logger = LoggerFactory.getLogger(AuditeServiceImpl.class);
	
	
	@Autowired
    private AuditeDAO auditeDAO;
	
	@Override
	public List<Audite> getAudite() {
		return auditeDAO.getAudite();
	}
	
	@Override
	public Audite getAudite(int id) {
		return auditeDAO.getAudite(id);
	}

	
	
	

}
