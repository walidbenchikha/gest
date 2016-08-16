package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.model.Gouvernorat;

public interface GouvernoratDAO {

//	public Operator getOperator(int id);

	public List<Gouvernorat> getGouvernorat();

	public int getGouvernorat(String gouvernorat);
}
