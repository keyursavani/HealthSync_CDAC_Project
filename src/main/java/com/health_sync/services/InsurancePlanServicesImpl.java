package com.health_sync.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.InsurancePlanDao;
import com.health_sync.dao.InsuranceProviderDao;
import com.health_sync.dto.AddInsurancePlanDto;
import com.health_sync.dto.InsurancePlansPatientDto;
import com.health_sync.pojos.InsurancePlan;
import com.health_sync.pojos.InsuranceProvider;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InsurancePlanServicesImpl implements InsurancePlanServices {

	private InsurancePlanDao insurancePlanDao;
	private InsuranceProviderDao insuranceProviderDao;
	private ModelMapper modelMapper;

	@Override
	public String addNewInsurancePlan(AddInsurancePlanDto dto) {

		InsuranceProvider insuranceProvider = insuranceProviderDao.findById(dto.getProvider())
				.orElseThrow(() -> new HealthSynsException("Invalid provider id"));

		InsurancePlan insurancePlan = modelMapper.map(dto, InsurancePlan.class);
		insuranceProvider.addInsurancePlan(insurancePlan);
		InsurancePlan insurancePlan2 = insurancePlanDao.save(insurancePlan);
		return "New Insurance plan added successfully with id " + insurancePlan2.getId() + " and Provider id "
				+ insurancePlan2.getProvider().getId();

	}

	@Override
	public List<InsurancePlansPatientDto> showInsurancePlans() {
		List<InsurancePlan> plans = insurancePlanDao.findAll();
		return plans.stream().map(plan -> modelMapper.map(plan, InsurancePlansPatientDto.class)).collect(Collectors.toList());
	}

}
