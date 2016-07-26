package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.ShopsDAO;
import com.globe.gest.model.Shops;


@Service
@Transactional
public class ShopsServiceImpl implements ShopsService {
	
	static Logger logger = LoggerFactory.getLogger(ShopsServiceImpl.class);
	
	
	@Autowired
    private ShopsDAO shopsDAO;
	
	@Override
	public List<Shops> getShops() {
		return shopsDAO.getShops();
	}
	
	
	
	

}
