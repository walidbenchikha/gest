package com.globe.gest.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Objects;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {
	/*
	 * CREATE TABLE `USERS` ( `ID` int(6) NOT NULL AUTO_INCREMENT, `USERNAME`
	 * VARCHAR(50) NOT NULL UNIQUE, `PASSWORD` VARCHAR(50) NOT NULL, `MAIL`
	 * VARCHAR(50) NOT NULL, `PHONE` VARCHAR(50) NOT NULL, `FNAME` VARCHAR(50)
	 * NOT NULL, `LNAME` VARCHAR(50) NOT NULL, `ENABLED` BOOLEAN NOT NULL,
	 * PRIMARY KEY (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */

	private static final long serialVersionUID = 6311364761937265306L;
	static Logger logger = LoggerFactory.getLogger(User.class);

	@NotNull(message = "{error.user.username.null}")
	@NotEmpty(message = "{error.user.username.empty}")
	@Size(max = 50, message = "{error.user.username.max}")
	@Column(name = "username", length = 50)
	private String username;

	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Size(max = 50, message = "{error.user.password.max}")
	@Column(name = "password", length = 50)
	private String password;
	
	
	 @OneToMany(mappedBy="user")
	    private Set<Visite> visiteUser;
	 
	  @OneToMany(mappedBy="auditor")
	    private Set<Visite> visiteAuditor;


	public Set<Visite> getVisiteUser() {
		return visiteUser;
	}

	public void setVisiteUser(Set<Visite> visiteUser) {
		this.visiteUser = visiteUser;
	}

	public Set<Visite> getVisiteAuditor() {
		return visiteAuditor;
	}

	public void setVisiteAuditor(Set<Visite> visiteAuditor) {
		this.visiteAuditor = visiteAuditor;
	}

	@NotNull(message = "mail null")
	@NotEmpty(message = "mail empty")
	@Size(max = 50, message = "mail max 50")
	@Column(name = "mail", length = 50)
	private String mail;

	@NotNull(message = "phone null")
	@NotEmpty(message = "phone empty")
	@Size(max = 50, message = "phone max 50")
	@Column(name = "phone", length = 50)
	private String phone;

	@NotNull(message = "fname null")
	@NotEmpty(message = "fname empty")
	@Size(max = 50, message = "fname max 50")
	@Column(name = "fname", length = 50)
	private String fname;

	@NotNull(message = "lname null")
	@NotEmpty(message = "lname empty")
	@Size(max = 50, message = "lname max 50")
	@Column(name = "lname", length = 50)
	private String lname;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Role role;

	 @ManyToOne
	 @JoinColumn(name="ID_OP")
	 private Operator operator;
	
	 public Operator getOperator() {
	 return operator;
	 }
	
	 public void setOperator(Operator operator) {
	 this.operator = operator;
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

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)", this.getClass().getSimpleName(),
				this.getId(), this.getUsername(), this.getPassword(), this.getMail(), this.getPhone(), this.getFname(),
				this.getLname(), this.getEnabled());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof User) {
			final User other = (User) o;
			return Objects.equal(getId(), other.getId()) && Objects.equal(getUsername(), other.getUsername())
					&& Objects.equal(getPassword(), other.getPassword()) && Objects.equal(getMail(), other.getMail())
					&& Objects.equal(getPhone(), other.getPhone()) && Objects.equal(getFname(), other.getFname())
					&& Objects.equal(getLname(), other.getLname()) && Objects.equal(getEnabled(), other.getEnabled());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getUsername(), getPassword(), getMail(), getPhone(), getFname(), getLname(),
				getEnabled());
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();
		perms.addAll(role.getPermissions());
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(getRole());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

}
