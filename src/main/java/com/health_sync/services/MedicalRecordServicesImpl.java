package com.health_sync.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.DoctorDao;
import com.health_sync.dao.MedicalRecordDao;
import com.health_sync.dao.PatientDao;
import com.health_sync.dto.AddMedicalRecordDto;
import com.health_sync.pojos.Doctor;
import com.health_sync.pojos.MedicalRecord;
import com.health_sync.pojos.Patient;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class MedicalRecordServicesImpl implements MedicalRecordServices {

	private ModelMapper modelMapper;
	private PatientDao patientDao;
	private DoctorDao doctorDao;
	private MedicalRecordDao medicalRecordDao;

	@Override
	public String addMedicalRecord(AddMedicalRecordDto dto) {
		try {
			Patient patient = patientDao.findById(dto.getPatient())
					.orElseThrow(() -> new HealthSynsException("Invalid patient id"));
			Doctor doctor = doctorDao.findById(dto.getDoctor())
					.orElseThrow(() -> new HealthSynsException("Invalid doctor id"));
			MedicalRecord record = modelMapper.map(dto, MedicalRecord.class);
			patient.addMedicalRecords(record);
			doctor.addMedicalRecords(record);
			record = medicalRecordDao.save(record);
			return "Medical record added successfully with patient id " + record.getPatient().getId()
					+ " and doctor id " + record.getDoctor().getId();
		} catch (Exception e) {
			throw new HealthSynsException("Invalid data");
		}
	}

}
