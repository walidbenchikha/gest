package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.OperatorDAO;
import com.globe.gest.model.Operator;
import com.globe.gest.model.Role;

@Service
@Transactional
public class OperatorServiceImpl implements OperatorService {
	static Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);

	@Autowired
	private OperatorDAO operatorDAO;

	@Override
	public Operator getOperator(int id) {
		return operatorDAO.getOperator(id);
	}
	
	@Override
    public List<Operator> getOperators() {
        return operatorDAO.getOperators();
    }

}
