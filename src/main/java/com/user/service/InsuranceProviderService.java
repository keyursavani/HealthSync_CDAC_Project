package com.user.service;

import com.user.dto.InsuranceproviderDto;
import com.user.dto.RegisterInsurancesProviderDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;

public interface InsuranceProviderService {
	
	public String registerInsuranceprovider(RegisterInsurancesProviderDto dto);

	public SignInResponseJwtDto insuranceproviderLogin(SignInDto dto);

	public InsuranceproviderDto getInsuranceproviderById(Long pid);
}
