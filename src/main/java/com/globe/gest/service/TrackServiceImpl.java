package com.globe.gest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globe.gest.dao.ShopsDAO;
import com.globe.gest.dao.TrackDAO;
import com.globe.gest.model.Shops;
import com.globe.gest.model.Track;

@Service
@Transactional
public class TrackServiceImpl implements TrackService {

	static Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);

	@Autowired
	private TrackDAO trackDAO;

	

	@Override
		public List findUserTrack(int auditor) {
			
	return trackDAO.findUserTrack(auditor);
			
		}
	
	
	

}
