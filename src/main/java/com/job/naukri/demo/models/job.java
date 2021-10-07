package com.job.naukri.demo.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class job {

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public job() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private String role;
    
    private String company;
    
    private String salary;
    
    private String description;
    
    private String level;
    
    private Date expiry;
    
   private String skills;
   
   private String location;
    
    public job(int id, String role, String company, String salary, String description, String level, Date expiry,
			String skills, List<user> candidates,String location) {
		super();
		this.id = id;
		this.role = role;
		this.company = company;
		this.salary = salary;
		this.description = description;
		this.level = level;
		this.expiry = expiry;
		this.skills = skills;
		this.candidates = candidates;
		this.location=location;
	}
	@ManyToMany(targetEntity=user.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
    private List<user> candidates;
    
	public List<user> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<user> candidates) {
		this.candidates = candidates;
	}
	
}
