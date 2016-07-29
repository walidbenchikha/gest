package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.globe.gest.model.Shops;
import com.globe.gest.model.User;

@Repository
public class ShopsDAOImpl implements ShopsDAO {
	
	
	static Logger logger = LoggerFactory.getLogger(ShopsDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Shops> getShops() {
        String hql = "from Audite where dtype='shops'";
    	//String hql = "select AUDITE.ID_AUDITE , AUDITE.Nom_Audit� from Audite AUDITE join Audite.Shops";
        return getCurrentSession().createQuery(hql).list();
    }
	

}
