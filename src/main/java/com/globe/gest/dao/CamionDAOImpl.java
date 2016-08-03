package com.globe.gest.dao;

import java.util.List;

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

}
