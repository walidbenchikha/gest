package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;



@Entity
@Table(name="AUDITE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Audite implements Serializable {
	
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

	
	@NotNull(message = "{error.audite.Nom_Audité.null}")
    @NotEmpty(message = "{error.audite.Nom_Audité.empty}")
    @Size(max = 50, message = "{error.audite.Nom_Audité.max}")
    @Column(name = "Nom_Audité", length = 64)
    private String Nom_Audité;
	
	
	@NotNull(message = "{error.audite.isValid.null}")
    @NotEmpty(message = "{error.audite.isValid.empty}")
    @Size(max = 50, message = "{error.audite.isValid.max}")
    @Column(name = "isValid", length = 64)
    private int isValid;
	
	
	@NotNull(message = "{error.audite.ID_OP.null}")
    @NotEmpty(message = "{error.audite.ID_OP.empty}")
    @Size(max = 50, message = "{error.audite.ID_OP.max}")
    @Column(name = "ID_OP", length = 64)
    private int ID_OP;
	
	
	@NotNull(message = "{error.audite.ID_Gouv.null}")
    @NotEmpty(message = "{error.audite.ID_Gouv.empty}")
    @Size(max = 50, message = "{error.audite.ID_Gouv.max}")
    @Column(name = "ID_Gouv", length = 64)
    private int ID_Gouv;

	public String getNom_Audité() {
		return Nom_Audité;
	}

	public void setNom_Audité(String nom_Audité) {
		Nom_Audité = nom_Audité;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public int getID_OP() {
		return ID_OP;
	}

	public void setID_OP(int iD_OP) {
		ID_OP = iD_OP;
	}

	public int getID_Gouv() {
		return ID_Gouv;
	}

	public void setID_Gouv(int iD_Gouv) {
		ID_Gouv = iD_Gouv;
	}
	
	
	@Override
    public String toString() {
        return String.format("%s(ID_AUDITE=%d, Nom_Audité=%s, isValid=%d, ID_OP=%d, ID_Gouv=%d)", 
                this.getClass().getSimpleName(), 
                this.getID_AUDITE(), 
                this.getNom_Audité(), 
                this.getIsValid(),
                this.getID_OP(),
                this.getID_Gouv());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Audite) {
            final Audite other = (Audite) o;
            return Objects.equal(getID_AUDITE(), other.getID_AUDITE())
                    && Objects.equal(getNom_Audité(), other.getNom_Audité())
                    && Objects.equal(getIsValid(), other.getIsValid())
                    && Objects.equal(getID_OP(), other.getID_OP())
                    && Objects.equal(getID_Gouv(), other.getID_Gouv());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_AUDITE(), getNom_Audité(), getIsValid(), getID_OP(), getID_Gouv());
    }
	
	
	

}
