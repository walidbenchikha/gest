package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Audite;

@Repository
public class AuditeDAOImpl implements AuditeDAO {
	
	
	static Logger logger = LoggerFactory.getLogger(AuditeDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Audite> getAudite() {
        String hql = "from Audite a ";
    	//String hql = "select AUDITE.ID_AUDITE , AUDITE.Nom_Audit� from Audite AUDITE join Audite.Shops";
        return getCurrentSession().createQuery(hql).list();
    }
	

}
