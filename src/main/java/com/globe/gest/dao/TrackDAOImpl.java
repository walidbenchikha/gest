package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Track;

@Repository
public class TrackDAOImpl implements TrackDAO {

	static Logger logger = LoggerFactory.getLogger(TrackDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public List findUserTrack(int auditor) {
		String sql = "select t.latitude , t.longitude FROM Track t  where 1=1 ";

		if (!("Tout".equals(auditor))) {
			sql += " and t.auditor.id =:auditor ";
		}

		Query query = getCurrentSession().createQuery(sql);

		if (!("Tout".equals(auditor))) {
			query.setParameter("auditor", auditor);
		}
		
		System.out.println("***************************la requette du tracking est "+ sql +"******");
		return query.list();
		
		
		

		
	}
	
	

}
