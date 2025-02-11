package com.health_sync.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.InsuranceProviderDao;
import com.health_sync.dto.InsuranceProviderPlanReqDto;
import com.health_sync.dto.InsuranceProviderPlansDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterInsurancesProviderDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.pojos.InsuranceProvider;
import com.health_sync.pojos.UseRole;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InsuranceProviderServicesImpl implements InsuranceProviderServices {

	private InsuranceProviderDao insuranceProviderDao;
	private ModelMapper modelMapper;
	private PasswordEncoder encoder;
	private PatientServices patientServices;

	@Override
	public String registerInsuranceProvider(RegisterInsurancesProviderDto dto) {
		try {
			dto.setPassword(encoder.encode(dto.getPassword()));
			InsuranceProvider insuranceProvider = insuranceProviderDao
					.save(modelMapper.map(dto, InsuranceProvider.class));
			return "Successfully register as a insurance provider with id " + insuranceProvider.getId();
		} catch (Exception e) {
			throw new HealthSynsException("Email is already exists");
		}
	}
	
	@Override
	public SignInResponseJwtDto insuranceProviderLogin(SignInDto dto) {
		 InsuranceProvider insuranceProvider = insuranceProviderDao.findByEmail(dto.getEmail())
				 .orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		 if(!encoder.matches(dto.getPassword(), insuranceProvider.getPassword()))
			 new HealthSynsException("Invalid email and password");
		 SignInResponseJwtDto sdto = modelMapper.map(insuranceProvider, SignInResponseJwtDto.class);
		 sdto.setRole(UseRole.INSURANE_PROVIDER);
		 sdto.setFirstName(insuranceProvider.getCompanyName());
		return sdto;
	}

	@Override
	public InsuranceProviderPlansDto getMyInsurancePlan(Long id) {
		if(!insuranceProviderDao.existsById(id))
			throw new HealthSynsException("Invalid provider id");
		InsuranceProvider records = insuranceProviderDao.getInsuranPlans(id);
		return modelMapper.map(records, InsuranceProviderPlansDto.class);
	}

	@Override
	public InsuranceProviderPlanReqDto getInsuranceRequests(Long providerId) {
		if(!insuranceProviderDao.existsById(providerId))
			throw new HealthSynsException("Invalid provider id");
	 InsuranceProvider requests = insuranceProviderDao.getInsuranceRequests(providerId);
		return modelMapper.map(requests, InsuranceProviderPlanReqDto.class);
	}

	@Override
	public PatientRecordDto getPatientMedicalRecord(Long patientId) {
		return modelMapper.map(patientServices.getMedicalRecord(patientId), PatientRecordDto.class);
	}
}
