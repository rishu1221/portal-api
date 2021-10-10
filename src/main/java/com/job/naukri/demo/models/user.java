package com.job.naukri.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
		return "user [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", resume=" + resume
				+ ", applied=" + applied + "]";
	}

	private String username;
	
	private String password;
	
	private String role;
	private String resume;
	
	public String getResume() {
		return resume;
	}

	public void setResume(String f) {
		this.resume = f;
	}
	@ManyToMany(targetEntity=job.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<job> applied;
	
	@ManyToMany(targetEntity=job.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<job> blocked;
	
	

	public user() {
		super();
	}

	public user(String username, String password, String role, List<job> applied,String f,List<job> blocked) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.applied = applied;
		this.resume=f;
		this.blocked=blocked;
	}

	public List<job> getApplied() {
		return applied;
	}

	public void setApplied(List<job> applied) {
		this.applied = applied;
	}

	public List<job> getBlocked() {
		return blocked;
	}

	public void setBlocked(List<job> blocked) {
		this.blocked = blocked;
	}
	
}
