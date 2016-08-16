package com.globe.gest.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.common.base.Objects;

@Entity
@Table(name = "AUDITE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn( name = "dtype" )

@DiscriminatorValue(value = "audite")
public class Audite implements Serializable {

	private static final long serialVersionUID = 96285180113476324L;
	static Logger logger = LoggerFactory.getLogger(Strategy.class);

	@Column(name = "dtype", insertable = false, updatable = false)
	private String dtype;

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
	 @OneToMany(mappedBy="audite")
	    private Set<Visite> visite;

	@Id
	@GeneratedValue
	@Column(name = "ID_AUDITE")
	private int ID_AUDITE;

	public int getID_AUDITE() {
		return ID_AUDITE;
	}

	public void setID_AUDITE(int iD_AUDITE) {
		ID_AUDITE = iD_AUDITE;
	}

	@NotNull(message = "{error.audite.nom_audite.null}")
	@NotEmpty(message = "{error.audite.nom_audite.empty}")
	@Size(max = 50, message = "{error.audite.nom_audite.max}")
	@Column(name = "nom_audite", length = 64)
	private String nom_audite;

	@Column(name = "isValid", length = 64)
	private int isValid;

	/*
	 * @NotNull(message = "{error.audite.ID_OP.null}")
	 * 
	 * @NotEmpty(message = "{error.audite.ID_OP.empty}")
	 * 
	 * @Size(max = 50, message = "{error.audite.ID_OP.max}")
	 * 
	 * @Column(name = "ID_OP", length = 64) private int ID_OP;
	 * 
	 * 
	 * @NotNull(message = "{error.audite.ID_Gouv.null}")
	 * 
	 * @NotEmpty(message = "{error.audite.ID_Gouv.empty}")
	 * 
	 * @Size(max = 50, message = "{error.audite.ID_Gouv.max}")
	 * 
	 * @Column(name = "ID_Gouv", length = 64) private int ID_Gouv;
	 */
	
	@ManyToOne
	@JoinColumn(name="ID_OP")
	private Operator operator;

	 @ManyToOne
	 @JoinColumn(name="ID_Loc" )
	 public Localisation localisation;
	
	
	

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getNom_audite() {
		return nom_audite;
	}

	public void setNom_audite(String nom_Audite) {
		nom_audite = nom_Audite;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	// public int getID_OP() {
	// return ID_OP;
	// }
	//
	// public void setID_OP(int iD_OP) {
	// ID_OP = iD_OP;
	// }
	//
	// public int getID_Gouv() {
	// return ID_Gouv;
	// }
	//
	// public void setID_Gouv(int iD_Gouv) {
	// ID_Gouv = iD_Gouv;
	// }

	@Override
	public String toString() {
		return String.format("%s(ID_AUDITE=%d, Nom_Audite=%s, isValid=%d)", this.getClass().getSimpleName(),
				this.getID_AUDITE(), this.getNom_audite(), this.getIsValid());
		// this.getID_OP(),
		// this.getID_Gouv());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof Audite) {
			final Audite other = (Audite) o;
			return Objects.equal(getID_AUDITE(), other.getID_AUDITE())
					&& Objects.equal(getNom_audite(), other.getNom_audite())
					&& Objects.equal(getIsValid(), other.getIsValid());
			// && Objects.equal(getID_OP(), other.getID_OP())
			// && Objects.equal(getID_Gouv(), other.getID_Gouv());

		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getID_AUDITE(), getNom_audite(), getIsValid());
	}

}
