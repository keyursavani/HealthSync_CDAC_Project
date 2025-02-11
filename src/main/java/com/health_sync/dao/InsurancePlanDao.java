package com.health_sync.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health_sync.pojos.InsurancePlan;

public interface InsurancePlanDao extends JpaRepository<InsurancePlan, Long>{

}
