package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Shops;


@Repository
public class ShopsDAOImpl implements ShopsDAO {

	static Logger logger = LoggerFactory.getLogger(ShopsDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addShops(Shops shops) {
		// logger.debug("UserDAOImpl.addUser() - [" + shops.getUsername() +
		// "]");
		
		getCurrentSession().save(shops);
		logger.debug("***********************************");
		logger.debug("***********************************");
		logger.debug(shops.toString());
	}

	@Override
	public Shops getShops(int shopsId) {
		logger.debug("UserDAOImpl.getUser() - [" + shopsId + "]");
		Shops shopsObject = (Shops) getCurrentSession().get(Shops.class, shopsId);
		return shopsObject;
	}

	@Override
	public void updateShops(Shops shops) {
		Shops shopsToUpdate = getShops(shops.getID_AUDITE());

		shopsToUpdate.setNom_audite(shops.getNom_audite());
		shopsToUpdate.setDtype(shops.getDtype());
		shopsToUpdate.setIsValid(shops.getIsValid());
		shopsToUpdate.setLatitude_boutique(shops.getLatitude_boutique());
		shopsToUpdate.setLongitude_boutique(shops.getLongitude_boutique());
		shopsToUpdate.setAdresse_boutique(shops.getAdresse_boutique());
		shopsToUpdate.setPhone_boutique(shops.getPhone_boutique());
		shopsToUpdate.setStype(shops.getStype());
		getCurrentSession().update(shopsToUpdate);

	}

	@Override
	public void deleteShops(int shopsId) {
		Shops shops = getShops(shopsId);
		if (shops != null) {
			getCurrentSession().delete(shops);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Shops> getShops() {
		String hql = "from Audite where dtype='shops'";
		return getCurrentSession().createQuery(hql).list();
	}
	
	
	 @Override
	    @SuppressWarnings("unchecked")
	    public List<Shops> getShopsByName(String nom_audite,String stype) {
		 System.out.println("///////////////////////////////////////"); 
		 System.out.println(stype+"!");
	    	if (nom_audite==""){
	    		if (stype.equals("Tout")){
	    			System.out.println("Touuuuuuuuuuuuuuuuuuuuuuuuuuuuuut");
	    			String hql = "FROM Shops s ";
	    			return getCurrentSession().createQuery(hql).list();
	    		}
	    		else{
	    		String hql = "FROM Shops s where s.stype=:type";
	    		return getCurrentSession().createQuery(hql).setParameter("type",stype).list();
	    		
	    		}
	    	}
	    	else{
	    		if (stype.equals("Tout")){
	    			String hql = "FROM Shops s where s.nom_audite=:name";
	    			return getCurrentSession().createQuery(hql).setParameter("name",nom_audite).list();
	    		}
	    		else{
	    		String hql = "FROM Shops s where s.nom_audite=:name and s.stype=:type";
    	       
	    	        return getCurrentSession().createQuery(hql).setParameter("name",nom_audite).setParameter("type",stype).list();
	    		}
	    		
	    	}
	    }

}
