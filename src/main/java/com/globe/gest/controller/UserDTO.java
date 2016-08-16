package com.globe.gest.controller;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1631797874369735181L;
    static Logger logger = LoggerFactory.getLogger(UserDTO.class);
    
    private int id;
    
    @NotNull(message = "{error.user.username.null}")
    @NotEmpty(message = "{error.user.username.empty}")
    @Size(max = 50, message = "{error.user.username.max}")
    private String username;

    @NotNull(message = "{error.user.password.null}")
    @NotEmpty(message = "{error.user.password.empty}")
    @Size(max = 50, message = "{error.user.password.max}")
    private String password;
    
    @NotNull(message = "mail null")
    @NotEmpty(message = "mail empty")
    @Size(max = 50, message = "mail max 50")
    private String mail;
    
    @NotNull(message = "phone null")
    @NotEmpty(message = "phone empty")
    @Size(max = 50, message = "phone max 50")
    private String phone;
    
    @NotNull(message = "fname null")
    @NotEmpty(message = "fname empty")
    @Size(max = 50, message = "fname max 50")
    private String fname;
    
    @NotNull(message = "lname null")
    @NotEmpty(message = "lname empty")
    @Size(max = 50, message = "lname max 50")
    private String lname;
    
    private boolean enabled;
    private int roleId;
	public int getID_OP() {
		return ID_OP;
	}
	public void setID_OP(int iD_OP) {
		ID_OP = iD_OP;
	}

	private int ID_OP;
    
    public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    @Override
    public String toString() {
        return String.format("%s(id=%d, username=%s, password=%s, mail=%s, phone=%s, fname=%s, lname=%s, enabled=%b, roleID=%d)", 
                this.getClass().getSimpleName(), 
                this.getId(), 
                this.getUsername(), 
                this.getPassword(), 
                this.getMail(), 
                this.getPhone(), 
                this.getFname(), 
                this.getLname(), 
                this.getEnabled(),
                this.getRoleId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof UserDTO) {
            final UserDTO other = (UserDTO) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getMail(), other.getMail())
                    && Objects.equal(getPhone(), other.getPhone())
                    && Objects.equal(getFname(), other.getFname())
                    && Objects.equal(getLname(), other.getLname())
                    && Objects.equal(getEnabled(), other.getEnabled())
                    && Objects.equal(getRoleId(), other.getRoleId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getMail(), getPhone(), getFname(), getLname(), getEnabled(), getRoleId());
    }


}
