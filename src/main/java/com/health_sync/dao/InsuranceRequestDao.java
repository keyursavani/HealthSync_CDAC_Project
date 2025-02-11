package com.health_sync.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health_sync.pojos.InsuranceRequest;

public interface InsuranceRequestDao extends JpaRepository<InsuranceRequest, Long>{
	
}
