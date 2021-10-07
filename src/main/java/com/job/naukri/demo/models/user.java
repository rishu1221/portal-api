package com.job.naukri.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;
@Entity
public class user {

	
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", f=" + f
				+ ", applied=" + applied + "]";
	}

	private String username;
	
	private String password;
	
	private String role;
	@ManyToOne(cascade=CascadeType.ALL)
	private file f;
	
	public file getF() {
		return f;
	}

	public void setF(file f) {
		this.f = f;
	}

	@OneToMany(targetEntity=job.class,cascade=CascadeType.ALL)
	private List<job> applied;
	

	public user() {
		super();
	}

	public user(String username, String password, String role, List<job> applied,file f) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.applied = applied;
		this.f=f;
	}

	public List<job> getApplied() {
		return applied;
	}

	public void setApplied(List<job> applied) {
		this.applied = applied;
	}
	
}
