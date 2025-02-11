package com.health_sync.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.InsurancePlanDao;
import com.health_sync.dao.InsuranceProviderDao;
import com.health_sync.dao.InsuranceRequestDao;
import com.health_sync.dao.PatientDao;
import com.health_sync.dto.InsuranceRequestDto;
import com.health_sync.dto.RequestStatusChangeDto;
import com.health_sync.pojos.InsurancePlan;
import com.health_sync.pojos.InsuranceProvider;
import com.health_sync.pojos.InsuranceRequest;
import com.health_sync.pojos.InsuranceStatus;
import com.health_sync.pojos.Patient;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InsuranceRequestServicesImpl  implements InsuranceRequestServices {

	private ModelMapper modelMapper;
	private InsuranceRequestDao insuranceRequestDao;
	private PatientDao patientDao;
	private InsurancePlanDao insurancePlanDao;
	private InsuranceProviderDao insuranceProviderDao;
	
	@Override
	public String addNewRequest(InsuranceRequestDto dto) {
		Patient patient = patientDao.findById(dto.getPatient()).orElseThrow(()-> new HealthSynsException("Invalid patient id"));
		InsuranceProvider provider  = insuranceProviderDao.findById(dto.getProvider()).orElseThrow(()-> new HealthSynsException("Invalid provider id"));
		InsurancePlan plan = insurancePlanDao.findById(dto.getPlan()).orElseThrow(() -> new HealthSynsException("Invalid plan id"));
		InsuranceRequest request = modelMapper.map(dto, InsuranceRequest.class);
		request.setStatus(InsuranceStatus.PROCESSING);
		patient.addInsuranceRequest(request);
		provider.addInsuranceRequest(request);
		plan.addInsuranceRequests(request);
		request = insuranceRequestDao.save(request);
		return "Add insurance request successfully with id "+request.getId();
	}

	@Override
	public String changeRequestStatus(RequestStatusChangeDto dto, Long requestId) {
		InsuranceRequest request = insuranceRequestDao.findById(requestId)
				.orElseThrow(()-> new HealthSynsException("Invalid provider id"));
		if(request.getProvider().getId() != dto.getProviderId())
			throw new HealthSynsException("Invalid provider id");
		request.setStatus(dto.getStatus());
		request  = insuranceRequestDao.save(request);
		return "Successfully updated status with id "+request.getId();
	}


}
