package com.ibm.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.ibm.role.model.Role;

@Entity
@Table(name = "users", catalog = "my_hours_report")
public class User implements java.io.Serializable{
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "user")
	private String user;

	@Column(name = "user_name")
	private String username;

	@Column(name = "user_password")
	private String userpassword;

	@Column(name = "enabled")
	private byte enabled;
	
	@Column(name = "mail")
	private String mail;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", catalog = "my_hours_report", joinColumns = {@JoinColumn(name = "id_user") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_roles") })
	private Set<Role> roles = new HashSet<Role>(0);
	
	public User(){
		
	}
	
	public User(String user, String username, String password, byte state, String mail){
		this.user = user;
		this.username = username;
		this.userpassword = password;
		this.enabled = state;
		this.mail = mail;
	}
	
	public User(String[] userData) {
		this(getParameter(userData[4]), getParameter(userData[2])+", "+getParameter(userData[1]), getParameter(userData[5]), (byte)1, getParameter(userData[3]).replaceFirst("%40", "@"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public boolean getIsEnabled() {
		if(this.getEnabled()==1)
			return true;
		else
			return false;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", username=" + username + ", userpassword=" + userpassword
				+ ", enabled=" + enabled + ", mail=" + mail + ", roles=" + roles + "]";
	}

	private static String getParameter(String string) {
		return string.substring(string.lastIndexOf("=")+1,string.length());
	}

}