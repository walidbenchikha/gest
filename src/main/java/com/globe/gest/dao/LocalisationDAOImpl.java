package com.globe.gest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Localisation;

@Repository
public class LocalisationDAOImpl implements LocalisationDAO {
	static Logger logger = LoggerFactory.getLogger(LocalisationDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Localisation getLocalisation(int loc_id) {
		Localisation localisationObject = (Localisation) getCurrentSession().get(Localisation.class, loc_id);

		return localisationObject;

	}

}
