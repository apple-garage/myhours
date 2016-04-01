package com.ibm.role.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.ibm.user.model.User;


@Entity
@Table(name = "roles")
public class Role implements java.io.Serializable{
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "roles")
	private String roles;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> userRoles = new HashSet<User>(0);
	
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Set<User> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roles=" + roles + "]";
	}
	   
   
}
