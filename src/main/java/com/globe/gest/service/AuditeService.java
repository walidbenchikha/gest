package com.globe.gest.service;

import java.util.List;

import com.globe.gest.model.Audite;
import com.globe.gest.model.Operator;

public interface AuditeService {
	
	public List<Audite> getAudite();
	public Audite getAudite(int id);

}
