package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Operator;

public interface OperatorService {

	public Operator getOperator(int id);

	public List<Operator> getOperators();

}
