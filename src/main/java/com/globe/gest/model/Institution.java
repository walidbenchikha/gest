package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;


@Entity
@Table(name="institution")
@DiscriminatorValue( value="institution" )

public class Institution  extends Audite implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Institution.class);
    
    
    

//	@NotNull(message = "{error.shops.latitude_boutique.null}")
//    @NotEmpty(message = "{error.shops.latitude_boutique.empty}")
    @Size(max = 50, message = "{error.shops.latitude_boutique.max}")
    @Column(name = "latitude_boutique", length = 20)
	  private String latitude_boutique;
    
//    @NotNull(message = "{error.shops.longitude_boutique.null}")
//    @NotEmpty(message = "{error.shops.longitude_boutique.empty}")
    @Size(max = 50, message = "{error.shops.longitude_boutique.max}")
    @Column(name = "longitude_boutique", length = 20)
    private String longitude_boutique;
    
    
//    @NotNull(message = "{error.shops.adresse_boutique.null}")
//    @NotEmpty(message = "{error.shops.adresse_boutique.empty}")
    @Size(max = 50, message = "{error.shops.adresse_boutique.max}")
    @Column(name = "adresse_boutique", length = 20)
    private String adresse_boutique;
    
//    @NotNull(message = "{error.shops.phone_boutique.null}")
//    @NotEmpty(message = "{error.shops.phone_boutique.empty}")
    @Size(max = 50, message = "{error.shops.phone_boutique.max}")
    @Column(name = "phone_boutique", length = 20)
    private String phone_boutique;
    
    
    @Size(max = 50, message = "{error.shops.raison_sociale.max}")
    @Column(name = "raison_sociale", length = 20)
    private String raison_sociale;
    
    
    
    public String getRaison_sociale() {
		return raison_sociale;
	}

	public void setRaison_sociale(String raison_sociale) {
		this.raison_sociale = raison_sociale;
	}

	@Override
    public String toString() {
        return String.format("%s(latitude_boutique='%s', longitude_boutique=%s,adresse_boutique=%s , phone_boutique=%s)", 
                this.getClass().getSimpleName(),
                this.getLatitude_boutique(), this.getLongitude_boutique(), this.getAdresse_boutique(),this.getPhone_boutique(),this.getRaison_sociale());
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

        if (o instanceof Institution) {
            final Institution other = (Institution) o;
            return 
            		
            		Objects.equal(getLatitude_boutique(), other.getLatitude_boutique())
                    && Objects.equal(getLongitude_boutique(), other.getLongitude_boutique())
                    && Objects.equal(getAdresse_boutique(), other.getAdresse_boutique())
                    && Objects.equal(getPhone_boutique(), other.getPhone_boutique())
                    && Objects.equal(getRaison_sociale(), other.getRaison_sociale());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLatitude_boutique(), getLongitude_boutique(),getAdresse_boutique(),getPhone_boutique(),getRaison_sociale());
    }

	
	
	
	
	
}
