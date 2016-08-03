package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void addInstitution(Institution institution) {
		// logger.debug("UserDAOImpl.addUser() - [" + institution.getUsername()
		// +
		// "]");
		getCurrentSession().save(institution);
	}

	@Override
	public Institution getInstitution(int institutionId) {
		logger.debug("UserDAOImpl.getUser() - [" + institutionId + "]");
		Institution institutionObject = (Institution) getCurrentSession().get(Institution.class, institutionId);
		return institutionObject;
	}

	@Override
	public void updateInstitution(Institution institution) {
		Institution institutionToUpdate = getInstitution(institution.getID_AUDITE());

		institutionToUpdate.setNom_audite(institution.getNom_audite());
		institutionToUpdate.setDtype(institution.getDtype());
		institutionToUpdate.setIsValid(institution.getIsValid());
		institutionToUpdate.setLatitude_boutique(institution.getLatitude_boutique());
		institutionToUpdate.setLongitude_boutique(institution.getLongitude_boutique());
		institutionToUpdate.setAdresse_boutique(institution.getAdresse_boutique());
		institutionToUpdate.setPhone_boutique(institution.getPhone_boutique());
		institutionToUpdate.setRaison_sociale(institution.getRaison_sociale());
		getCurrentSession().update(institutionToUpdate);

	}

	@Override
	public void deleteInstitution(int institutionId) {
		Institution institution = getInstitution(institutionId);
		if (institution != null) {
			getCurrentSession().delete(institution);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Institution> getInstitution() {
		String hql = "from Audite where dtype='institution'";
		return getCurrentSession().createQuery(hql).list();
	}

}
