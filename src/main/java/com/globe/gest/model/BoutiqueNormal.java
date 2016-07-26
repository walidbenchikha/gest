package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;


@Entity
@Table(name="boutiquenormal")
public class BoutiqueNormal implements Serializable {
	
	
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

	@NotNull(message = "{error.boutiquenormal.ID_OP.null}")
    @NotEmpty(message = "{error.boutiquenormal.ID_OP.empty}")
    @Size(max = 50, message = "{error.boutiquenormal.ID_OP.max}")
    @Column(name = "ID_OP", length = 20)
    private int ID_OP;
    
    public int getID_OP() {
		return ID_OP;
	}

	public void setID_OP(int iD_OP) {
		ID_OP = iD_OP;
	}

	@Override
    public String toString() {
        return String.format("%s(ID_AUDITE=%d, ID_OP=%d)", 
                this.getClass().getSimpleName(), this.getID_AUDITE(),
                this.getID_OP());
    }

    

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof BoutiqueNormal) {
            final BoutiqueNormal other = (BoutiqueNormal) o;
            return 
            		Objects.equal(getID_AUDITE(),other.getID_AUDITE())
            		&&Objects.equal(getID_OP(), other.getID_OP());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_AUDITE(),getID_OP());
    }

	
	
	
	
	
	
	
	

}
