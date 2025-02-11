package com.health_sync.services;

import java.util.List;

import com.health_sync.dto.AddInsurancePlanDto;
import com.health_sync.dto.InsurancePlansPatientDto;

public interface InsurancePlanServices {
	
	public String addNewInsurancePlan(AddInsurancePlanDto dto);
	public List<InsurancePlansPatientDto> showInsurancePlans();

}
