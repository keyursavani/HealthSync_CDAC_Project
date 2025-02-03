package com.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.custome_exception.HealthSynsException;
import com.user.dto.DoctorDto;
import com.user.dto.DoctorRecordDto;
import com.user.dto.RegisterDoctorDto;
import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.entities.Doctor;
import com.user.entities.UseRole;
import com.user.repository.DoctorRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private DoctorRepository doctorRepository;
	private ModelMapper modelMapper;
	private PasswordEncoder encoder;
	
	
	@Override
	public String registerDoctor(RegisterDoctorDto dto) {
		try {
		dto.setPassword(encoder.encode(dto.getPassword()));
		Doctor doctor = doctorRepository.save(modelMapper.map(dto,Doctor.class));
		return "Successfully register as a doctor with id "+doctor.getId();
		}catch(Exception e) {
			throw new HealthSynsException("Email or Doctor id is already exists");
		}
	}


	@Override
	public SignInResponseJwtDto doctorLogin(SignInDto dto) {
		 Doctor doctor = doctorRepository.findByEmail(dto.getEmail())
				 .orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		 if(!encoder.matches(dto.getPassword(), doctor.getPassword()))
			 new HealthSynsException("Invalid email and password");
		 SignInResponseJwtDto sdto = modelMapper.map(doctor, SignInResponseJwtDto.class);
		 sdto.setRole(UseRole.DOCTOR);
		return sdto;
	}

	@Override
	public DoctorRecordDto getMedicalRecord(Long id) {
		if(!doctorRepository.existsById(id))
			throw new HealthSynsException("Invalid doctor id");
		Doctor records = doctorRepository.getMedicalRecords(id);
		return modelMapper.map(records, DoctorRecordDto.class);
	}


	@Override
	public DoctorDto getDoctorById(Long doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new HealthSynsException("Invalid doctor id"));
		DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
		return doctorDto;
	}
   
}
