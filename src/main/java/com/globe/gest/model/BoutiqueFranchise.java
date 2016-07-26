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
@Table(name="boutiqueFranchise")

public class BoutiqueFranchise implements Serializable {
	
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

	@NotNull(message = "{error.boutiqueFranchise.ID_DEALER.null}")
    @NotEmpty(message = "{error.boutiqueFranchise.ID_DEALER.empty}")
    @Size(max = 50, message = "{error.boutiqueFranchise.ID_DEALER.max}")
    @Column(name = "ID_DEALER", length = 20)
    private int ID_DEALER;
    
    

	public int getID_DEALER() {
		return ID_DEALER;
	}

	public void setID_DEALER(int iD_DEALER) {
		ID_DEALER = iD_DEALER;
	}

	@Override
    public String toString() {
        return String.format("%s(ID_AUDITE=%d, ID_DEALER=%d)", 
                this.getClass().getSimpleName(), this.getID_AUDITE(),
                this.getID_DEALER());
    }

    

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof BoutiqueFranchise) {
            final BoutiqueFranchise other = (BoutiqueFranchise) o;
            return 
            		Objects.equal(getID_AUDITE(),other.getID_AUDITE())
            		&&Objects.equal(getID_DEALER(), other.getID_DEALER());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_AUDITE(),getID_DEALER());
    }

	
	
	

}
