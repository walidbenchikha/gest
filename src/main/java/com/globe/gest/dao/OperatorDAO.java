package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Operator;

public interface OperatorDAO {

	public Operator getOperator(int id);

	public List<Operator> getOperators();
}
