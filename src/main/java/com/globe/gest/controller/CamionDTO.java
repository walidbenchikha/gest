package com.globe.gest.controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public class CamionDTO implements Serializable {

    private static final long serialVersionUID = -1631797874369735181L;
    static Logger logger = LoggerFactory.getLogger(CamionDTO.class);
    
    private int ID_AUDITE;	
    
    private String dtype;

    @NotNull(message = "{error.audite.nom_audite.null}")
    @NotEmpty(message = "{error.audite.nom_audite.empty}")
    @Size(max = 50, message = "{error.audite.nom_audite.max}")
    private String nom_audite;
    
  
    private int isValid;
  
    @NotNull(message = "{error.shops.matricule.null}")
    @NotEmpty(message = "{error.shops.matricule.empty}")
    @Size(max = 50, message = "{error.camion.matricule.max}")
	  private String matricule;
    
    
  
    public int getID_AUDITE() {
		return ID_AUDITE;
	}

	public void setID_AUDITE(int iD_AUDITE) {
		ID_AUDITE = iD_AUDITE;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getNom_audite() {
		return nom_audite;
	}

	public void setNom_audite(String nom_audite) {
		this.nom_audite = nom_audite;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	@Override
    public String toString() {
        return String.format("%s(id_audite=%d, nom_audite=%s, dtype=%s, isvalid=%s, matricule=%s)", 
                this.getClass().getSimpleName(), 
                this.getID_AUDITE(), 
                this.getNom_audite(), 
                this.getDtype(), 
                this.getIsValid(), 
                this.getMatricule());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof CamionDTO) {
            final CamionDTO other = (CamionDTO) o;
            return Objects.equal(getID_AUDITE(), other.getID_AUDITE())
                    && Objects.equal(getNom_audite(), other.getNom_audite())
                    && Objects.equal(getDtype(), other.getDtype())
                    && Objects.equal(getIsValid(), other.getIsValid())
                    && Objects.equal(getMatricule(), other.getMatricule());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_AUDITE(), getNom_audite(), getDtype(), getIsValid(), getMatricule());
    }

}
