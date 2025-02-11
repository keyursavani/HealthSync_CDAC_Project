package com.health_sync.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.DoctorDao;
import com.health_sync.dto.DoctorRecordDto;
import com.health_sync.dto.RegisterDoctorDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.pojos.Doctor;
import com.health_sync.pojos.UseRole;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

	private DoctorDao doctorDao;
	private ModelMapper modelMapper;
	private PasswordEncoder encoder;
	
	
	@Override
	public String registerDoctor(RegisterDoctorDto dto) {
		try {
		dto.setPassword(encoder.encode(dto.getPassword()));
		Doctor doctor = doctorDao.save(modelMapper.map(dto,Doctor.class));
		return "Successfully register as a doctor with id "+doctor.getId();
		}catch(Exception e) {
			throw new HealthSynsException("Email or Doctor id is already exists");
		}
	}


	@Override
	public SignInResponseJwtDto doctorLogin(SignInDto dto) {
		 Doctor doctor = doctorDao.findByEmail(dto.getEmail())
				 .orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		 if(!encoder.matches(dto.getPassword(), doctor.getPassword()))
			 new HealthSynsException("Invalid email and password");
		 SignInResponseJwtDto sdto = modelMapper.map(doctor, SignInResponseJwtDto.class);
		 sdto.setRole(UseRole.DOCTOR);
		return sdto;
	}

	@Override
	public DoctorRecordDto getMedicalRecord(Long id) {
		if(!doctorDao.existsById(id))
			throw new HealthSynsException("Invalid doctor id");
		Doctor records = doctorDao.getMedicalRecords(id);
		return modelMapper.map(records, DoctorRecordDto.class);
	}
   
}
