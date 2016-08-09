package com.globe.gest.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;


@Entity
@Table(name="operator")
public class Operator implements Serializable {
	
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Operator.class);
    
    
    @Id
    @GeneratedValue
    @Column(name="ID_OP")
    private int ID_OP;
    
    @NotNull(message = "{error.operator.nom_op.null}")
    @NotEmpty(message = "{error.operator.nom_op.empty}")
    @Size(max = 50, message = "{error.operator.nom_op.max}")
    @Column(name = "nom_op", length = 50)
	  private String nom_op;
    
    @NotNull(message = "{error.operator.description_op.null}")
    @NotEmpty(message = "{error.operator.description_op.empty}")
    @Size(max = 50, message = "{error.operator.description_op.max}")
    @Column(name = "description_op", length = 100)
	  private String description_op;
    
    @OneToMany(mappedBy="operator")
    private Set<User> users;
    
    
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy="operator")
    private Set<Audite> audite;
    

	public Set<Audite> getAudite() {
		return audite;
	}

	public void setAudite(Set<Audite> audite) {
		this.audite = audite;
	}

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

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//	
	@Override
    public String toString() {
        return String.format("%s(ID_OP=%d, nom_op='%s', description_op=%s)", 
                this.getClass().getSimpleName(), this.getID_OP(),
                this.getNom_op(), this.getDescription_op());
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Operator) {
            final Operator other = (Operator) o;
            return 
            		Objects.equal(getID_OP(),other.getID_OP())
            		&&Objects.equal(getNom_op(), other.getNom_op())
                    && Objects.equal(getDescription_op(), other.getDescription_op());
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_OP(),getNom_op(), getDescription_op());
    }

    
    

}
