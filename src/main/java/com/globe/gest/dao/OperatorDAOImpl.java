package com.globe.gest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globe.gest.model.Operator;

@Repository
public class OperatorDAOImpl implements OperatorDAO {
	static Logger logger = LoggerFactory.getLogger(OperatorDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Operator getOperator(int operator_id) {
		Operator operatorObject = (Operator) getCurrentSession().get(Operator.class, operator_id);

		return operatorObject;

	}
	
	@Override
    @SuppressWarnings("unchecked")
    public List<Operator> getOperators() {
        String hql = "FROM Operator r ORDER BY r.id";
        return getCurrentSession().createQuery(hql).list();
    }

}
