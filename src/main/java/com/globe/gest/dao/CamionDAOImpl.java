package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Camion;

@Repository
public class CamionDAOImpl implements CamionDAO {

	static Logger logger = LoggerFactory.getLogger(CamionDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addCamion(Camion camion) {
		// logger.debug("UserDAOImpl.addUser() - [" + camion.getUsername() +
		// "]");
		getCurrentSession().save(camion);
	}

	@Override
	public Camion getCamion(int camionId) {
		logger.debug("UserDAOImpl.getUser() - [" + camionId + "]");
		Camion camionObject = (Camion) getCurrentSession().get(Camion.class, camionId);
		return camionObject;
	}

	@Override
	public void updateCamion(Camion camion) {
		Camion camionToUpdate = getCamion(camion.getID_AUDITE());

		camionToUpdate.setNom_audite(camion.getNom_audite());
		camionToUpdate.setDtype(camion.getDtype());
		camionToUpdate.setIsValid(camion.getIsValid());
		camionToUpdate.setMatricule(camion.getMatricule());
		getCurrentSession().update(camionToUpdate);

	}

	@Override
	public void deleteCamion(int camionId) {
		Camion camion = getCamion(camionId);
		if (camion != null) {
			getCurrentSession().delete(camion);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Camion> getCamion() {
		String hql = "from Audite where dtype='camion'";
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Camion> getCamionByName(String nom_audite, String operator, String gouvernorat, String ville,
			String localisation) {
		String sql = "FROM Camion s  where 1=1 ";

		if (!("".equals(nom_audite))) {
			sql += " and s.nom_audite like :nom ";
		}

		if (!("Tout".equals(operator))) {
			sql += " and s.operator.nom_op = :op ";
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
