package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
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
		System.out.println("DAAOOOOOOOOOOOOOOOo");		
		Institution institutionToUpdate = getInstitution(institution.getID_AUDITE());
		System.out.println(institution.getID_AUDITE());		
		System.out.println(institutionToUpdate.toString());	
		institutionToUpdate.setNom_audite(institution.getNom_audite());
		System.out.println("DAAOOOOOOOOOOOOOOOo");	
		institutionToUpdate.setDtype(institution.getDtype());
		institutionToUpdate.setIsValid(institution.getIsValid());
		institutionToUpdate.setLatitude_boutique(institution.getLatitude_boutique());
		institutionToUpdate.setLongitude_boutique(institution.getLongitude_boutique());
		institutionToUpdate.setAdresse_boutique(institution.getAdresse_boutique());
		institutionToUpdate.setPhone_boutique(institution.getPhone_boutique());
		institutionToUpdate.setRaison_sociale(institution.getRaison_sociale());
		institutionToUpdate.setOperator(institution.getOperator());
		institutionToUpdate.setLocalisation(institution.getLocalisation());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Institution> getInstitutionByName(String nom_audite, String operator, int gouvernorat, int ville,
			int localisation) {

		String sql = "FROM Institution s  where 1=1 ";

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
