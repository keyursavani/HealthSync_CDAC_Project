package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.InsuranceproviderDto;
import com.user.dto.RegisterInsurancesProviderDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.InsuranceProvider;
import com.user.entities.UserRole;
import com.user.repository.DoctorRepository;
import com.user.repository.InsuranceProviderRepository;
import com.user.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InsuranceProviderServiceImpl implements InsuranceProviderService {

	private InsuranceProviderRepository insuranceProviderRepository;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder encoder;

	@Override
	public String registerInsuranceprovider(RegisterInsurancesProviderDto dto) {
		try {
			if (insuranceProviderRepository.existsByEmail(dto.getEmail())
					|| doctorRepository.existsByEmail(dto.getEmail())
					|| patientRepository.existsByEmail(dto.getEmail()))
				throw new HealthSynsException("This email is already registered. Please use a different email.");
			dto.setPassword(encoder.encode(dto.getPassword()));
			InsuranceProvider provider = insuranceProviderRepository
					.save(modelMapper.map(dto, InsuranceProvider.class));
			return "You have successfully registered as a insuranceprovider. Your unique ID is: " + provider.getId();
		} catch (Exception e) {
			throw new HealthSynsException("Registration failed : " + e.getMessage());
		}
	}

	@Override
	public SignInResponseJwtDto insuranceproviderLogin(SignInDto dto) {
		InsuranceProvider provider = insuranceProviderRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new HealthSynsException("Invalid email or password. Please try again"));
		if (!encoder.matches(dto.getPassword(), provider.getPassword()))
			new HealthSynsException("Invalid email or password. Please try again");
		SignInResponseJwtDto sdto = modelMapper.map(provider, SignInResponseJwtDto.class);
		sdto.setFirstName(provider.getCompanyName());
		sdto.setRole(UserRole.INSURANE_PROVIDER);
		return sdto;
	}

	@Override
	public InsuranceproviderDto getInsuranceproviderById(Long pid) {
		InsuranceProvider provider = insuranceProviderRepository.findById(pid)
				.orElseThrow(() -> new HealthSynsException("Invalid insuranceprovider ID. Please enter a valid ID."));
		InsuranceproviderDto insuranceproviderDto = modelMapper.map(provider, InsuranceproviderDto.class);
		return insuranceproviderDto;
	}
}
