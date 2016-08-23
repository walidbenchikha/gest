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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;


@Entity
@Table(name="Track")
public class Track implements Serializable {
	
	private static final long serialVersionUID = 96285180113476324L;
    static Logger logger = LoggerFactory.getLogger(Operator.class);
    
    @Id
    @GeneratedValue
    @Column(name="ID_Track")
    private int ID_Track;
    
   
    @Column(name = "longitude", length = 64)
	  private String longitude;
    
    @Column(name = "latitude", length = 64)
	  private String latitude;
    
    @Column(name = "date_track", length = 64)
	  private Long date_track;
  
    
	@ManyToOne
    @JoinColumn(name="id_auditor")
    private User auditor;


	public int getID_Track() {
		return ID_Track;
	}



	public void setID_Track(int iD_Track) {
		ID_Track = iD_Track;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public Long getDate_track() {
		return date_track;
	}



	@Override
	public String toString() {
		return "Track [ID_Track=" + ID_Track + ", longitude=" + longitude + ", latitude=" + latitude + ", date_track="
				+ date_track + ", auditor=" + auditor + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_Track;
		result = prime * result + ((auditor == null) ? 0 : auditor.hashCode());
		result = prime * result + ((date_track == null) ? 0 : date_track.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		Track other = (Track) obj;
		if (ID_Track != other.ID_Track)
			return false;
		if (auditor == null) {
			if (other.auditor != null)
				return false;
		} else if (!auditor.equals(other.auditor))
			return false;
		if (date_track == null) {
			if (other.date_track != null)
				return false;
		} else if (!date_track.equals(other.date_track))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}



	public User getAuditor() {
		return auditor;
	}



	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}



	public void setDate_track(Long date_track) {
		this.date_track = date_track;
	}



}