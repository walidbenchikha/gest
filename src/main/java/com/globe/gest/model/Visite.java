package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.common.base.Objects;

@Entity
@Table(name = "Visites")
public class Visite implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
	static Logger logger = LoggerFactory.getLogger(Visite.class);


	@Id
	@GeneratedValue
	@Column(name = "ID_Visite")
	private int id_visite;


	@Column(name = "date_a_visiter", length = 64)
	private String date_a_visiter;
	
	@Column(name = "date_Visite", length = 64)
	private String date_visite;
	
	@Column(name = "note", length = 64)
	private int note;
	


	@ManyToOne
	@JoinColumn(name="ID_AUDITE")
	private Audite audite;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_auditor")
	private User auditor;

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

	public Audite getAudite() {
		return audite;
	}

	public void setAudite(Audite audite) {
		this.audite = audite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	@Override
	public String toString() {
		return "Visite [id_visite=" + id_visite + ", date_a_visiter=" + date_a_visiter + ", date_visite=" + date_visite
				+ ", note=" + note + ", audite=" + audite + ", user=" + user + ", auditor=" + auditor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audite == null) ? 0 : audite.hashCode());
		result = prime * result + ((auditor == null) ? 0 : auditor.hashCode());
		result = prime * result + ((date_a_visiter == null) ? 0 : date_a_visiter.hashCode());
		result = prime * result + ((date_visite == null) ? 0 : date_visite.hashCode());
		result = prime * result + id_visite;
		result = prime * result + note;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Visite other = (Visite) obj;
		if (audite == null) {
			if (other.audite != null)
				return false;
		} else if (!audite.equals(other.audite))
			return false;
		if (auditor == null) {
			if (other.auditor != null)
				return false;
		} else if (!auditor.equals(other.auditor))
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
		if (id_visite != other.id_visite)
			return false;
		if (note != other.note)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	

}
