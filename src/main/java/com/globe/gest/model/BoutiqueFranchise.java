package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
@Table(name="boutiqueFranchise")
@DiscriminatorValue(value="franchise")
public class BoutiqueFranchise extends Shops implements Serializable {
	
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Strategy.class);
    
    
    
/*
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
        return String.format("%s(ID_DEALER=%d)", 
                this.getClass().getSimpleName(),
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
            		
            		Objects.equal(getID_DEALER(), other.getID_DEALER());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_DEALER());
    }

	
	*/
	

}
