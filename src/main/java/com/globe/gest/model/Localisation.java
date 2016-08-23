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
@Table(name="localisation")
public class Localisation implements Serializable {
	
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Localisation.class);
    
    @Id
    @GeneratedValue
    @Column(name="ID_LOC")
    private int ID_LOC;
    
    @NotNull(message = "{error.localisation.Nom_Loc.null}")
    @NotEmpty(message = "{error.localisation.Nom_Loc.empty}")
    @Size(max = 50, message = "{error.localisation.Nom_Loc.max}")
    @Column(name = "Nom_Loc", length = 64)
	  private String Nom_Loc;
    
    @ManyToOne
    @JoinColumn(name="ID_ville")
    public Ville ville;
    
    @JsonIgnore
    @OneToMany(mappedBy="localisation", fetch=FetchType.EAGER)
    private Set<Audite> audite;
    

	public Set<Audite> getAudite() {
		return audite;
	}

	public void setAudite(Set<Audite> audite) {
		this.audite = audite;
	}

	public int getID_LOC() {
		return ID_LOC;
	}

	public void setID_LOC(int iD_LOC) {
		ID_LOC = iD_LOC;
	}

	public String getNom_Loc() {
		return Nom_Loc;
	}

	public void setNom_Loc(String nom_Loc) {
		Nom_Loc = nom_Loc;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
	@Override
    public String toString() {
        return String.format("%s(ID_LOC=%d, Nom_Loc='%s')", 
                this.getClass().getSimpleName(), this.getID_LOC(),
                this.getNom_Loc());
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Localisation) {
            final Localisation other = (Localisation) o;
            return 
            		Objects.equal(getID_LOC(),other.getID_LOC())
            		&&Objects.equal(getNom_Loc(), other.getNom_Loc());
                   
                   
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getID_LOC(),getNom_Loc());
    }

}
