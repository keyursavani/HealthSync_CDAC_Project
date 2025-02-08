package com.insurance.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insurance.custome_exception.HealthSynsException;
import com.insurance.dto.InsurancePlanDto;
import com.insurance.dto.InsuranceproviderDto;
import com.insurance.entities.InsurancePlan;
import com.insurance.repository.InsurancePlanRepository;
import com.insurance.service.client.UserServiceClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsurancePlanServiceImpl implements InsurancePlanService {

	 private InsurancePlanRepository insurancePlanRepository;
	 private UserServiceClient userServiceClient;
	 private ModelMapper modelMapper;
	 
	 
	@Override
	public String addInsuranceplan(InsurancePlanDto dto) {
		try {
			InsuranceproviderDto pdto = userServiceClient.getInsuranceproviderById(dto.getProviderId());
			InsurancePlan plan = modelMapper.map(dto, InsurancePlan.class);
			plan.setProviderDetails(pdto);
			plan = insurancePlanRepository.save(plan);
			return "Insurance Plan has been added successfully.";
		}catch(Exception e){
			throw new HealthSynsException("Invalid data : "+e.getMessage());
		}
		
	}

}
