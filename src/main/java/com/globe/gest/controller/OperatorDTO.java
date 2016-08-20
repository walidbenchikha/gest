package com.globe.gest.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public class OperatorDTO implements Serializable {

    private static final long serialVersionUID = -2707641174043923410L;
    static Logger logger = LoggerFactory.getLogger(OperatorDTO.class);
    
    private int ID_OP;

    @NotNull(message = "{error.operator.nom_op.null}")
    @NotEmpty(message = "{error.operator.nom_op.empty}")
    @Size(max = 50, message = "{error.operator.nom_op.max}")
	  private String nom_op;
    
    @NotNull(message = "{error.operator.description_op.null}")
    @NotEmpty(message = "{error.operator.description_op.empty}")
    @Size(max = 50, message = "{error.operator.description_op.max}")
	  private String description_op;

	public int getID_OP() {
		return ID_OP;
	}

	public void setID_OP(int iD_OP) {
		ID_OP = iD_OP;
	}

	public String getNom_op() {
		return nom_op;
	}

	public void setNom_op(String nom_op) {
		this.nom_op = nom_op;
	}

	public String getDescription_op() {
		return description_op;
	}

	public void setDescription_op(String description_op) {
		this.description_op = description_op;
	}

	@Override
	public String toString() {
		return "OperatorDTO [ID_OP=" + ID_OP + ", nom_op=" + nom_op + ", description_op=" + description_op + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_OP;
		result = prime * result + ((description_op == null) ? 0 : description_op.hashCode());
		result = prime * result + ((nom_op == null) ? 0 : nom_op.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatorDTO other = (OperatorDTO) obj;
		if (ID_OP != other.ID_OP)
			return false;
		if (description_op == null) {
			if (other.description_op != null)
				return false;
		} else if (!description_op.equals(other.description_op))
			return false;
		if (nom_op == null) {
			if (other.nom_op != null)
				return false;
		} else if (!nom_op.equals(other.nom_op))
			return false;
		return true;
	}
    
    
}
