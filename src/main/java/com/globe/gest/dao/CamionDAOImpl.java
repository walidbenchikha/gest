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
		camionToUpdate.setOperator(camion.getOperator());
		camionToUpdate.setLocalisation(camion.getLocalisation());
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
	public List<Camion> getCamionByName(String nom_audite, String operator, int gouvernorat, int ville,
			int localisation) {
		String sql = "FROM Camion s  where 1=1 ";

		if (!("".equals(nom_audite))) {
			sql += " and s.nom_audite like :nom ";
		}

		if (!("Tout".equals(operator))) {
			sql += " and s.operator.nom_op = :op ";
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

}
