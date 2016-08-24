package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Audite;
import com.globe.gest.model.STAT1;
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
		shopsToUpdate.setOperator(shops.getOperator());
		shopsToUpdate.setLocalisation(shops.getLocalisation());
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
	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, int gouvernorat,
			int ville, int localisation) {

		String sql = "FROM Shops s  where 1=1 ";

		if (!("".equals(nom_audite))) {
			sql += " and s.nom_audite like :nom ";
		}

		if (!("Tout".equals(operator))) {
			sql += " and s.operator.nom_op = :op ";
		}

		if (!("Tout".equals(stype))) {
			sql += " and s.stype = :type ";
		}

		if (gouvernorat!=0) {
			sql += " and s.localisation.ville.gouvernorat.ID_Gouv = :gouv ";
			
		}

		if (ville!=0 && gouvernorat!=0) {
			sql += " and s.localisation.ville.ID_ville = :ville ";
		}
		if (gouvernorat!=0 && ville!=0 && localisation!=0) {
			sql += " and s.localisation.ID_LOC = :loc ";
		}

		Query query = getCurrentSession().createQuery(sql);

		if (!("".equals(nom_audite))) {
			query.setParameter("nom", nom_audite + "%");
		}

		if (!("Tout".equals(operator))) {
			query.setParameter("op", operator);
		}

		if (!("Tout".equals(stype))) {
			query.setParameter("type", stype);
		}

		if (gouvernorat!=0) {
			query.setParameter("gouv", gouvernorat);
			
		}

		if (ville!=0 && gouvernorat!=0) {
			query.setParameter("ville", ville);
		}

		if (gouvernorat!=0 && ville!=0 && localisation!=0) {
			query.setParameter("loc", localisation);
		}

		return query.list();

	}
	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Shops> findShopsMarkers(String operator,String auditor) {
//		
//
//		String sql = "FROM Shops shop  where 1=1 ";
//
//		if ( !("Tout".equals(auditor))) {
//			sql += " and shop.user.username like :auditor ";
//		}
//
//
//		if ( !("Tout".equals(operator))) {
//			sql += " and shop.operator.nom_op like :operator ";
//		}
//		
////		if (!("".equals(governorate)) && !("All".equals(governorate))) {
////			sql += " and shop.gouvernorat like :governorate ";
////		}
////		
////		if (!("".equals(dateCompare))) {
////			sql += " and ue.Date = :dateCompare ";
////		}
////
////		if (!("".equals(operator)) && !("All".equals(operator))) {
////			sql += " and ue.operator in (:operator) ";
////		}
////
////		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
////			sql += " and ue.Time >= :timeStart and  ue.Time < :timeEnd";
////		}
//		
//		
//		Query query = getCurrentSession().createQuery(sql);
//
//		if (!("Tout".equals(auditor))) {
//			query.setParameter("auditor", "%" + auditor + "%");
//		}
//
//		if (!("Tout".equals(operator))) {
//			query.setParameter("operator", "%" + operator + "%");
//			
//		}
//		
////		if (!("".equals(governorate)) && !("All".equalsIgnoreCase(governorate))) {
////			query.setParameter("governorate", "%" + governorate + "%");
////		}
////		if (!("".equals(dateCompare))) {
////			query.setParameter("dateCompare", dateCompare);
////		}
////
////		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
////			query.setParameter("timeStart", timeStart);
////			query.setParameter("timeEnd", timeEnd);
////		}
////
////		if (!("".equals(operator)) && !("All".equals(operator))) {
////			query.setParameterList("operator", operatorsArr);
////		}
//
//
//			System.out.println("***************************la requette est "+ sql +"******");
//			return query.list();
//		
//	}
//	
	
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Audite> findShopsMarkers(String operator,String auditor) {
		

		String sql = "FROM Audite shop  where 1=1 ";

		if ( !("Tout".equals(auditor))) {
			sql += " and shop.user.username like :auditor ";
		}


		if ( !("Tout".equals(operator))) {
			sql += " and shop.operator.nom_op like :operator ";
		}
		
//		if (!("".equals(governorate)) && !("All".equals(governorate))) {
//			sql += " and shop.gouvernorat like :governorate ";
//		}
//		
//		if (!("".equals(dateCompare))) {
//			sql += " and ue.Date = :dateCompare ";
//		}
//
//		if (!("".equals(operator)) && !("All".equals(operator))) {
//			sql += " and ue.operator in (:operator) ";
//		}
//
//		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
//			sql += " and ue.Time >= :timeStart and  ue.Time < :timeEnd";
//		}
		
		
		Query query = getCurrentSession().createQuery(sql);

		if (!("Tout".equals(auditor))) {
			query.setParameter("auditor", "%" + auditor + "%");
		}

		if (!("Tout".equals(operator))) {
			query.setParameter("operator", "%" + operator + "%");
			
		}
		
//		if (!("".equals(governorate)) && !("All".equalsIgnoreCase(governorate))) {
//			query.setParameter("governorate", "%" + governorate + "%");
//		}
//		if (!("".equals(dateCompare))) {
//			query.setParameter("dateCompare", dateCompare);
//		}
//
//		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
//			query.setParameter("timeStart", timeStart);
//			query.setParameter("timeEnd", timeEnd);
//		}
//
//		if (!("".equals(operator)) && !("All".equals(operator))) {
//			query.setParameterList("operator", operatorsArr);
//		}


			System.out.println("**********************la requette est "+ sql +"******");
			return query.list();
		
	}

	@Override
	public List<STAT1> findSTAT1(String operator, String auditor) {
		System.out.println("fara7  ");
		
	String sql=" select  a.operator.nom_op , count(a) from Audite a where 1=1";
	System.out.println("fara7  "+ sql );
	if ( !("Tout".equals(auditor))) {
		sql += " and a.user.username like :auditor ";
	}


	if ( !("Tout".equals(operator))) {
		sql += " and a.operator.nom_op like :operator ";
	}
		
		sql += "group by a.operator.nom_op"; 
		
//		if (!("".equals(dateCompare))) {
//			sql += " and ue.Date = :dateCompare ";
//		}
//
//		if (!("".equals(operator)) && !("All".equals(operator))) {
//			sql += " and ue.operator in (:operator) ";
//		}
//
//		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
//			sql += " and ue.Time >= :timeStart and  ue.Time < :timeEnd";
//		}
		
		Query query = getCurrentSession().createQuery(sql);

		if (!("Tout".equals(auditor))) {
			query.setParameter("auditor", "%" + auditor + "%");
		}

		if (!("Tout".equals(operator))) {
			query.setParameter("operator", "%" + operator + "%");
			
		}
		
		
//		if (!("".equals(dateCompare))) {
//			query.setParameter("dateCompare", dateCompare);
//		}
//
//		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
//			query.setParameter("timeStart", timeStart);
//			query.setParameter("timeEnd", timeEnd);
//		}
//
//		if (!("".equals(operator)) && !("All".equals(operator))) {
//			query.setParameterList("operator", operatorsArr);
//		}
		System.out.println("***************************la requette est "+ sql +"******");
		List<STAT1> St = (List<STAT1>) query.list();
			
			return St;
	}
	
		
//	@Override
//	public List<STAT1> findSTAT1(String operator, String auditor) {
//		System.out.println("fara7  ");
//		
//	String sql=" select  DISTINCT a.operator.nom_op , a.dtype,  count(a) from Audite a where 1=1";
//	System.out.println("fara7  "+ sql );
//	if ( !("Tout".equals(auditor))) {
//		sql += " and a.user.username like :auditor ";
//	}
//
//
//	if ( !("Tout".equals(operator))) {
//		sql += " and a.operator.nom_op like :operator ";
//	}
//		
//		sql += "group by a.operator.nom_op , a.dtype"; 
//		
////		if (!("".equals(dateCompare))) {
////			sql += " and ue.Date = :dateCompare ";
////		}
////
////		if (!("".equals(operator)) && !("All".equals(operator))) {
////			sql += " and ue.operator in (:operator) ";
////		}
////
////		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
////			sql += " and ue.Time >= :timeStart and  ue.Time < :timeEnd";
////		}
//		
//		Query query = getCurrentSession().createQuery(sql);
//
//		if (!("Tout".equals(auditor))) {
//			query.setParameter("auditor", "%" + auditor + "%");
//		}
//
//		if (!("Tout".equals(operator))) {
//			query.setParameter("operator", "%" + operator + "%");
//			
//		}
//		
//		
////		if (!("".equals(dateCompare))) {
////			query.setParameter("dateCompare", dateCompare);
////		}
////
////		if (!("".equals(timeStart)) && !("".equals(timeEnd))) {
////			query.setParameter("timeStart", timeStart);
////			query.setParameter("timeEnd", timeEnd);
////		}
////
////		if (!("".equals(operator)) && !("All".equals(operator))) {
////			query.setParameterList("operator", operatorsArr);
////		}
//		System.out.println("***************************la requette est "+ sql +"******");
//		List<STAT1> St = (List<STAT1>) query.list();
//			
//			return St;
//	}
//	
//	

}
