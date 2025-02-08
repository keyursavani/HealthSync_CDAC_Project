package com.insurance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.insurance.entities.InsurancePlan;

public interface InsurancePlanRepository extends MongoRepository<InsurancePlan,String>{

}
