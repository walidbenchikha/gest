package com.globe.gest.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="ville")
public class Ville implements Serializable {
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Ville.class);
	
	@Id
    @GeneratedValue
    @Column(name="ID_ville")
    private int ID_ville;
	
	@NotNull(message = "{error.ville.Nom_Ville.null}")
    @NotEmpty(message = "{error.ville.Nom_Ville.empty}")
    @Size(max = 50, message = "{error.ville.Nom_Ville.max}")
    @Column(name = "Nom_Ville", length = 64)
	  private String Nom_Ville;
	
	
	@ManyToOne
    @JoinColumn(name="ID_Gouv")
    private Gouvernorat gouvernorat;
	
	
	
	
	public Gouvernorat getGouvernorat() {
		return gouvernorat;
	}


	public void setGouvernorat(Gouvernorat gouvernorat) {
		this.gouvernorat = gouvernorat;
	}

	@JsonIgnore
	@OneToMany(mappedBy="ville", fetch=FetchType.EAGER)
	public Set<Localisation> localisation ;


	public Set<Localisation> getLocalisation() {
		return localisation;
	}


	public void setLocalisation(Set<Localisation> localisation) {
		this.localisation = localisation;
	}


	public int getID_ville() {
		return ID_ville;
	}


	public void setID_ville(int iD_ville) {
		ID_ville = iD_ville;
	}


	public String getNom_Ville() {
		return Nom_Ville;
	}


	public void setNom_Ville(String nom_Ville) {
		Nom_Ville = nom_Ville;
	}


 
	
	@Override
    public String toString() {
        return String.format("%s(ID_ville=%d, Nom_Ville='%s')", 
                this.getClass().getSimpleName(), this.getID_ville(),
                this.getNom_Ville());
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Ville) {
            final Ville other = (Ville) o;
            return 
            		Objects.equal(getID_ville(),other.getID_ville())
            		&&Objects.equal(getNom_Ville(), other.getNom_Ville());
                   
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_ville(),getNom_Ville());
    }

    
    

}
