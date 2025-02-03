package com.user.service;



import com.user.dto.DoctorDto;
import com.user.dto.DoctorRecordDto;
import com.user.dto.RegisterDoctorDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;

public interface DoctorService {
  public String registerDoctor(RegisterDoctorDto dto);
  public SignInResponseJwtDto doctorLogin(SignInDto dto);
  public DoctorRecordDto getMedicalRecord(Long id);
  public DoctorDto getDoctorById(Long doctorId);
}
