package com.globe.gest.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;


@Entity
@Table(name="Gouvernorat")
public class Gouvernorat implements Serializable {
	
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Operator.class);
    
    @Id
    @GeneratedValue
    @Column(name="ID_Gouv")
    private int ID_Gouv;
    
    @NotNull(message = "{error.gouvernorat.Nom_Gouver.null}")
    @NotEmpty(message = "{error.gouvernorat.Nom_Gouver.empty}")
    @Size(max = 50, message = "{error.gouvernorat.Nom_Gouver.max}")
    @Column(name = "Nom_Gouver", length = 64)
	  private String Nom_Gouver;
    
    
    @JsonIgnore
    @OneToMany(mappedBy="gouvernorat", fetch=FetchType.EAGER)
	public Set<Ville> ville;

	public Set<Ville> getVille() {
		return ville;
	}

	public void setVille(Set<Ville> ville) {
		this.ville = ville;
	}

	public int getID_Gouv() {
		return ID_Gouv;
	}

	public void setID_Gouv(int iD_Gouv) {
		ID_Gouv = iD_Gouv;
	}

	public String getNom_Gouver() {
		return Nom_Gouver;
	}

	public void setNom_Gouver(String nom_Gouver) {
		Nom_Gouver = nom_Gouver;
	}
	
	
	@Override
    public String toString() {
        return String.format("%s(ID_Gouv=%d, Nom_Gouver='%s')", 
                this.getClass().getSimpleName(), this.getID_Gouv(),
                this.getNom_Gouver());
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Gouvernorat) {
            final Gouvernorat other = (Gouvernorat) o;
            return 
            		Objects.equal(getID_Gouv(),other.getID_Gouv())
            		&&Objects.equal(getNom_Gouver(), other.getNom_Gouver());
                   
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_Gouv(),getNom_Gouver());
    }

    
    
    

}
