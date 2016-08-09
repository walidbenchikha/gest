package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.GouvernoratDAO;
import com.globe.gest.model.Gouvernorat;

@Service
@Transactional
public class GouvernoratServiceImpl implements GouvernoratService {
	static Logger logger = LoggerFactory.getLogger(GouvernoratServiceImpl.class);

	@Autowired
	private GouvernoratDAO gouvernoratDAO;

//	@Override
//	public Operator getOperator(int id) {
//		return operatorDAO.getOperator(id);
//	}
	
	@Override
    public List<Gouvernorat> getGouvernorat() {
        return gouvernoratDAO.getGouvernorat();
    }

}
