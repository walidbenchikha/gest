package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	static Logger logger = LoggerFactory.getLogger(VilleDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// @Override
	// public Operator getOperator(int operator_id) {
	// Operator operatorObject = (Operator)
	// getCurrentSession().get(Operator.class, operator_id);
	//
	// return operatorObject;
	//
	// }

	@Override
	@SuppressWarnings("unchecked")
	public List<Ville> getVille(int gouvernorat) {
		String hql = "FROM Ville v where v.gouvernorat.ID_Gouv =:gouv ";
		
		Query query=  getCurrentSession().createQuery(hql).setString("gouv", String.valueOf(gouvernorat));
		return query.list();
	}
	
	@Override
	public int getVille(String ville) {
		String hql = "select v.ID_ville FROM Ville v where v.Nom_Ville =:ville ";
		
		Query query=  getCurrentSession().createQuery(hql).setString("ville",ville);
		return  (int) query.uniqueResult();
	}

}
