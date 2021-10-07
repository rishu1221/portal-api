package com.job.naukri.demo.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity
public class job {

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

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
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

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private String role;
    
    private String company;
    
    private Long salary;
    
    private String description;
    
    private String level;
    
    private Date expiry;
    
    @ElementCollection
    private List<String> skills;
    
    public job(int id, String role, String company, Long salary, String description, String level, Date expiry,
			List<String> skills, List<user> candidates) {
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
	}

	@OneToMany(targetEntity=user.class,cascade=CascadeType.ALL)
    private List<user> candidates;
    
	public List<user> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<user> candidates) {
		this.candidates = candidates;
	}
	
}
