package com.globe.gest.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TovisitDTO implements Serializable {

	static Logger logger = LoggerFactory.getLogger(TovisitDTO.class);
	private static final long serialVersionUID = -1631797874369735181L;

	
	private int id_visite;

	private String date_a_visiter;
	
	private String date_visite;
	
	private int note;

	private int ID_AUDITE;
	
	private int id_user;
	
	private int id_auditor;

	public int getId_visite() {
		return id_visite;
	}

	public void setId_visite(int id_visite) {
		this.id_visite = id_visite;
	}

	public String getDate_a_visiter() {
		return date_a_visiter;
	}

	public void setDate_a_visiter(String date_a_visiter) {
		this.date_a_visiter = date_a_visiter;
	}

	public String getDate_visite() {
		return date_visite;
	}

	public void setDate_visite(String date_visite) {
		this.date_visite = date_visite;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getID_AUDITE() {
		return ID_AUDITE;
	}

	public void setID_AUDITE(int iD_AUDITE) {
		ID_AUDITE = iD_AUDITE;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_auditor() {
		return id_auditor;
	}

	public void setId_auditor(int id_auditor) {
		this.id_auditor = id_auditor;
	}

	@Override
	public String toString() {
		return "TovisitDTO [id_visite=" + id_visite + ", date_a_visiter=" + date_a_visiter + ", date_visite="
				+ date_visite + ", note=" + note + ", ID_AUDITE=" + ID_AUDITE + ", id_user=" + id_user + ", id_auditor="
				+ id_auditor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_AUDITE;
		result = prime * result + ((date_a_visiter == null) ? 0 : date_a_visiter.hashCode());
		result = prime * result + ((date_visite == null) ? 0 : date_visite.hashCode());
		result = prime * result + id_auditor;
		result = prime * result + id_user;
		result = prime * result + id_visite;
		result = prime * result + note;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TovisitDTO other = (TovisitDTO) obj;
		if (ID_AUDITE != other.ID_AUDITE)
			return false;
		if (date_a_visiter == null) {
			if (other.date_a_visiter != null)
				return false;
		} else if (!date_a_visiter.equals(other.date_a_visiter))
			return false;
		if (date_visite == null) {
			if (other.date_visite != null)
				return false;
		} else if (!date_visite.equals(other.date_visite))
			return false;
		if (id_auditor != other.id_auditor)
			return false;
		if (id_user != other.id_user)
			return false;
		if (id_visite != other.id_visite)
			return false;
		if (note != other.note)
			return false;
		return true;
	}
	
	
}
