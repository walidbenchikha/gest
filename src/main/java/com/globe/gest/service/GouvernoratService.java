package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Gouvernorat;

public interface GouvernoratService {

//	public Operator getOperator(int id);

	public List<Gouvernorat> getGouvernorat();

	public int getGouvernorat(String gouvernorat);

}
