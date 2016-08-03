package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "SHOPS")
@DiscriminatorValue(value = "shops")

public class Shops extends Audite implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
	static Logger logger = LoggerFactory.getLogger(Strategy.class);

	@Column(name = "stype")

	private String stype;

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	// @NotNull(message = "{error.shops.latitude_boutique.null}")
	// @NotEmpty(message = "{error.shops.latitude_boutique.empty}")
	@Size(max = 50, message = "{error.shops.latitude_boutique.max}")
	@Column(name = "latitude_boutique", length = 20)
	private String latitude_boutique;

	// @NotNull(message = "{error.shops.longitude_boutique.null}")
	// @NotEmpty(message = "{error.shops.longitude_boutique.empty}")
	@Size(max = 50, message = "{error.shops.longitude_boutique.max}")
	@Column(name = "longitude_boutique", length = 20)
	private String longitude_boutique;

	// @NotNull(message = "{error.shops.adresse_boutique.null}")
	// @NotEmpty(message = "{error.shops.adresse_boutique.empty}")
	@Size(max = 50, message = "{error.shops.adresse_boutique.max}")
	@Column(name = "adresse_boutique", length = 20)
	private String adresse_boutique;

	// @NotNull(message = "{error.shops.phone_boutique.null}")
	// @NotEmpty(message = "{error.shops.phone_boutique.empty}")
	@Size(max = 50, message = "{error.shops.phone_boutique.max}")
	@Column(name = "phone_boutique", length = 20)
	private String phone_boutique;

	public String getLatitude_boutique() {
		return latitude_boutique;
	}

	public void setLatitude_boutique(String latitude_boutique) {
		this.latitude_boutique = latitude_boutique;
	}

	public String getLongitude_boutique() {
		return longitude_boutique;
	}

	public void setLongitude_boutique(String longitude_boutique) {
		this.longitude_boutique = longitude_boutique;
	}

	public String getAdresse_boutique() {
		return adresse_boutique;
	}

	public void setAdresse_boutique(String adresse_boutique) {
		this.adresse_boutique = adresse_boutique;
	}

	public String getPhone_boutique() {
		return phone_boutique;
	}

	public void setPhone_boutique(String phone_boutique) {
		this.phone_boutique = phone_boutique;
	}

	@Override
	public String toString() {
		return "Shops [stype=" + stype + ", latitude_boutique=" + latitude_boutique + ", longitude_boutique="
				+ longitude_boutique + ", adresse_boutique=" + adresse_boutique + ", phone_boutique=" + phone_boutique
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresse_boutique == null) ? 0 : adresse_boutique.hashCode());
		result = prime * result + ((latitude_boutique == null) ? 0 : latitude_boutique.hashCode());
		result = prime * result + ((longitude_boutique == null) ? 0 : longitude_boutique.hashCode());
		result = prime * result + ((phone_boutique == null) ? 0 : phone_boutique.hashCode());
		result = prime * result + ((stype == null) ? 0 : stype.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shops other = (Shops) obj;
		if (adresse_boutique == null) {
			if (other.adresse_boutique != null)
				return false;
		} else if (!adresse_boutique.equals(other.adresse_boutique))
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

}
