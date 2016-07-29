package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Audite;
import com.globe.gest.model.Institution;

@Repository
public class InstitutionDAOImpl implements InstitutionDAO {
	
	
	static Logger logger = LoggerFactory.getLogger(InstitutionDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Institution> getInstitution() {
        String hql = "from Audite where dtype='institution'";
    	//String hql = "select AUDITE.ID_AUDITE , AUDITE.Nom_Audité from Audite AUDITE join Audite.Shops";
        return getCurrentSession().createQuery(hql).list();
    }
	

}
