package com.job.naukri.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.naukri.demo.models.job;

@Repository
public interface jobRepository extends JpaRepository<job, Integer> {
	List <job> findByCompanyContaining(String cName);

	List <job> findBySkillsContaining(String skills);

	List <job> findByRoleContaining(String role);
}
