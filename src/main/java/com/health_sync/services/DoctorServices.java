package com.health_sync.services;

import java.util.List;

import com.health_sync.dto.DoctorRecordDto;
import com.health_sync.dto.RegisterDoctorDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;

public interface DoctorServices {
  public String registerDoctor(RegisterDoctorDto dto);
  public SignInResponseJwtDto doctorLogin(SignInDto dto);
  public DoctorRecordDto getMedicalRecord(Long id);
}
