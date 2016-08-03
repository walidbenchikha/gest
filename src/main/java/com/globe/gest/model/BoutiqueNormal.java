package com.globe.gest.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "boutiquenormal")
@DiscriminatorValue(value = "normal")
public class BoutiqueNormal extends Shops implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
	static Logger logger = LoggerFactory.getLogger(Strategy.class);

	/*
	 * @NotNull(message = "{error.boutiquenormal.ID_OP.null}")
	 * 
	 * @NotEmpty(message = "{error.boutiquenormal.ID_OP.empty}")
	 * 
	 * @Size(max = 50, message = "{error.boutiquenormal.ID_OP.max}")
	 * 
	 * @Column(name = "ID_OP", length = 20) private int ID_OP;
	 * 
	 * public int getID_OP() { return ID_OP; }
	 * 
	 * public void setID_OP(int iD_OP) { ID_OP = iD_OP; }
	 * 
	 * @Override public String toString() { return String.format("%s( ID_OP=%d)"
	 * , this.getClass().getSimpleName(), this.getID_OP()); }
	 * 
	 * 
	 * 
	 * @Override public boolean equals(Object o) { if (this == o) return true;
	 * if (o == null) return false;
	 * 
	 * if (o instanceof BoutiqueNormal) { final BoutiqueNormal other =
	 * (BoutiqueNormal) o; return
	 * 
	 * Objects.equal(getID_OP(), other.getID_OP());
	 * 
	 * } return false; }
	 * 
	 * @Override public int hashCode() { return Objects.hashCode(getID_OP()); }
	 * 
	 * 
	 * 
	 * 
	 */

}
