package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;


@Entity
@Table(name="SHOPS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shops implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Strategy.class);
    
    
    @Id
    @GeneratedValue
    @Column(name="ID_AUDITE")
    private int ID_AUDITE;

    public int getID_AUDITE() {
		return ID_AUDITE;
	}

	public void setID_AUDITE(int iD_AUDITE) {
		ID_AUDITE = iD_AUDITE;
	}

	@NotNull(message = "{error.shops.latitude_boutique.null}")
    @NotEmpty(message = "{error.shops.latitude_boutique.empty}")
    @Size(max = 50, message = "{error.shops.latitude_boutique.max}")
    @Column(name = "latitude_boutique", length = 20)
	  private String latitude_boutique;
    
    @NotNull(message = "{error.shops.longitude_boutique.null}")
    @NotEmpty(message = "{error.shops.longitude_boutique.empty}")
    @Size(max = 50, message = "{error.shops.longitude_boutique.max}")
    @Column(name = "longitude_boutique", length = 20)
    private String longitude_boutique;
    
    
    @NotNull(message = "{error.shops.adresse_boutique.null}")
    @NotEmpty(message = "{error.shops.adresse_boutique.empty}")
    @Size(max = 50, message = "{error.shops.adresse_boutique.max}")
    @Column(name = "adresse_boutique", length = 20)
    private String adresse_boutique;
    
    @NotNull(message = "{error.shops.phone_boutique.null}")
    @NotEmpty(message = "{error.shops.phone_boutique.empty}")
    @Size(max = 50, message = "{error.shops.phone_boutique.max}")
    @Column(name = "phone_boutique", length = 20)
    private String phone_boutique;
    
    
    
    
    
    
    @Override
    public String toString() {
        return String.format("%s(ID_AUDITE=%d, latitude_boutique='%s', longitude_boutique=%s,adresse_boutique=%s , phone_boutique=%s)", 
                this.getClass().getSimpleName(), this.getID_AUDITE(),
                this.getLatitude_boutique(), this.getLongitude_boutique(), this.getAdresse_boutique(),this.getPhone_boutique());
    }

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Shops) {
            final Shops other = (Shops) o;
            return 
            		Objects.equal(getID_AUDITE(),other.getID_AUDITE())
            		&&Objects.equal(getLatitude_boutique(), other.getLatitude_boutique())
                    && Objects.equal(getLongitude_boutique(), other.getLongitude_boutique())
                    && Objects.equal(getAdresse_boutique(), other.getAdresse_boutique())
                    && Objects.equal(getPhone_boutique(), other.getPhone_boutique());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_AUDITE(),getLatitude_boutique(), getLongitude_boutique(),getAdresse_boutique(),getPhone_boutique());
    }

	
	
	
	
	
}
