package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Localisation> getLocalisations(int ville) {
		String hql = "FROM Localisation l where l.ville.ID_ville=:ville ";
		
	Query query=  getCurrentSession().createQuery(hql).setString("ville", String.valueOf(ville));
		// query=  getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public int getLocalisation(String localisation) {
		String hql = "select l.ID_LOC FROM Localisation l where l.Nom_Loc =:loc ";
		
		Query query=  getCurrentSession().createQuery(hql).setString("loc",localisation);
		return  (int) query.uniqueResult();

	}

}
