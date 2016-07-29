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
@Table(name="CAMION")
@DiscriminatorValue( value="camion" )
public class Camion  extends Audite implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Strategy.class);
    
    
    

//	@NotNull(message = "{error.shops.latitude_boutique.null}")
//    @NotEmpty(message = "{error.shops.latitude_boutique.empty}")
    @Size(max = 50, message = "{error.camion.matricule.max}")
    @Column(name = "matricule", length = 20)
	  private String matricule;
    

    
    
    
    public String getMatricule() {
		return matricule;
	}



	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}



	@Override
    public String toString() {
        return String.format("%s(latitude_boutique='%s', longitude_boutique=%s,adresse_boutique=%s , phone_boutique=%s)", 
                this.getClass().getSimpleName(),
                this.getMatricule());
    }

    

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Camion) {
            final Camion other = (Camion) o;
            return 
            		
            		Objects.equal(getMatricule(), other.getMatricule());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMatricule());
    }

	
	
	
	
	
}
