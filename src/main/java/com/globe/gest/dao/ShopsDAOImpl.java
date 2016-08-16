package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
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
	public List<Shops> getShopsByName(String nom_audite, String stype, String operator, String gouvernorat,
			String ville, String localisation) {

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

		if (!("Tout".equals(localisation))) {
			sql += " and s.localisation.Nom_Loc = :loc ";
		}

		if (!("Tout".equals(ville)) && ("Tout".equals(localisation))) {
			sql += " and s.localisation.ville.Nom_Ville = :ville ";
		}
		if (!("Tout".equals(gouvernorat)) && ("Tout".equals(localisation)) && ("Tout".equals(ville))) {
			sql += " and s.localisation.ville.gouvernorat.Nom_Gouver = :gouv ";
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

		if (!("Tout".equals(localisation))) {
			query.setParameter("loc", localisation);
		}

		if (!("Tout".equals(ville)) && ("Tout".equals(localisation))) {
			query.setParameter("ville", ville);
		}

		if (!("Tout".equals(gouvernorat)) && ("Tout".equals(localisation)) && ("Tout".equals(ville))) {
			query.setParameter("gouv", gouvernorat);
		}

		return query.list();

	}

}
