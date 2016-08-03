package com.globe.gest.controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopsDTO implements Serializable {

	static Logger logger = LoggerFactory.getLogger(ShopsDTO.class);
	private static final long serialVersionUID = -1631797874369735181L;

	@NotNull(message = "{error.shops.adresse_boutique.null}")
	@NotEmpty(message = "{error.shops.adresse_boutique.empty}")
	@Size(max = 50, message = "{error.shops.adresse_boutique.max}")
	private String adresse_boutique;

	private String dtype;
	
	private int ID_AUDITE;
	
	private int isValid;

	@NotNull(message = "{error.shops.latitude_boutique.null}")
	@NotEmpty(message = "{error.shops.latitude_boutique.empty}")
	@Size(max = 50, message = "{error.shops.latitude_boutique.max}")
	private String latitude_boutique;

	@NotNull(message = "{error.shops.longitude_boutique.null}")
	@NotEmpty(message = "{error.shops.longitude_boutique.empty}")
	@Size(max = 50, message = "{error.shops.longitude_boutique.max}")
	private String longitude_boutique;

	@NotNull(message = "{error.audite.nom_audite.null}")
	@NotEmpty(message = "{error.audite.nom_audite.empty}")
	@Size(max = 50, message = "{error.audite.nom_audite.max}")
	private String nom_audite;

	@NotNull(message = "{error.shops.phone_boutique.null}")
	@NotEmpty(message = "{error.shops.phone_boutique.empty}")
	@Size(max = 50, message = "{error.shops.phone_boutique.max}")
	private String phone_boutique;

	
	private String stype;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopsDTO other = (ShopsDTO) obj;
		if (ID_AUDITE != other.ID_AUDITE)
			return false;
		if (adresse_boutique == null) {
			if (other.adresse_boutique != null)
				return false;
		} else if (!adresse_boutique.equals(other.adresse_boutique))
			return false;
		if (dtype == null) {
			if (other.dtype != null)
				return false;
		} else if (!dtype.equals(other.dtype))
			return false;
		if (isValid != other.isValid)
			return false;
		if (latitude_boutique == null) {
			if (other.latitude_boutique != null)
				return false;
		} else if (!latitude_boutique.equals(other.latitude_boutique))
			return false;
		if (longitude_boutique == null) {
			if (other.longitude_boutique != null)
				return false;
		} else if (!longitude_boutique.equals(other.longitude_boutique))
			return false;
		if (nom_audite == null) {
			if (other.nom_audite != null)
				return false;
		} else if (!nom_audite.equals(other.nom_audite))
			return false;
		if (phone_boutique == null) {
			if (other.phone_boutique != null)
				return false;
		} else if (!phone_boutique.equals(other.phone_boutique))
			return false;
		if (stype == null) {
			if (other.stype != null)
				return false;
		} else if (!stype.equals(other.stype))
			return false;
		return true;
	}

	public String getAdresse_boutique() {
		return adresse_boutique;
	}

	public String getDtype() {
		return dtype;
	}

	public int getID_AUDITE() {
		return ID_AUDITE;
	}

	public int getIsValid() {
		return isValid;
	}

	public String getLatitude_boutique() {
		return latitude_boutique;
	}

	public String getLongitude_boutique() {
		return longitude_boutique;
	}

	public String getNom_audite() {
		return nom_audite;
	}

	public String getPhone_boutique() {
		return phone_boutique;
	}

	public String getStype() {
		return stype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_AUDITE;
		result = prime * result + ((adresse_boutique == null) ? 0 : adresse_boutique.hashCode());
		result = prime * result + ((dtype == null) ? 0 : dtype.hashCode());
		result = prime * result + isValid;
		result = prime * result + ((latitude_boutique == null) ? 0 : latitude_boutique.hashCode());
		result = prime * result + ((longitude_boutique == null) ? 0 : longitude_boutique.hashCode());
		result = prime * result + ((nom_audite == null) ? 0 : nom_audite.hashCode());
		result = prime * result + ((phone_boutique == null) ? 0 : phone_boutique.hashCode());
		result = prime * result + ((stype == null) ? 0 : stype.hashCode());
		return result;
	}

	public void setAdresse_boutique(String adresse_boutique) {
		this.adresse_boutique = adresse_boutique;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public void setID_AUDITE(int iD_AUDITE) {
		ID_AUDITE = iD_AUDITE;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public void setLatitude_boutique(String latitude_boutique) {
		this.latitude_boutique = latitude_boutique;
	}

	public void setLongitude_boutique(String longitude_boutique) {
		this.longitude_boutique = longitude_boutique;
	}

	public void setNom_audite(String nom_audite) {
		this.nom_audite = nom_audite;
	}

	public void setPhone_boutique(String phone_boutique) {
		this.phone_boutique = phone_boutique;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	@Override
	public String toString() {
		return "ShopsDTO [ID_AUDITE=" + ID_AUDITE + ", dtype=" + dtype + ", isValid=" + isValid + ", nom_audite="
				+ nom_audite + ", latitude_boutique=" + latitude_boutique + ", longitude_boutique=" + longitude_boutique
				+ ", adresse_boutique=" + adresse_boutique + ", phone_boutique=" + phone_boutique + ", stype=" + stype
				+ "]";
	}

}
