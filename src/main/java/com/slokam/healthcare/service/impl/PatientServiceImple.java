package com.slokam.healthcare.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.repo.IPatientRepo;
import com.slokam.healthcare.repo.PatientCriteriaREPO;
import com.slokam.healthcare.service.IPatientService;

@Service
public class PatientServiceImple implements IPatientService {
	@Autowired
	private IPatientRepo patientrepo;

	@Autowired
	private PatientCriteriaREPO patientcri;

	@Override
	public void PatientRegistration(Patient patient) {
		patient.setRegDate(new Date());
		patientrepo.save(patient);
	}

	@Override
	public List<Patient> getbynamenademail(String name, String email) {

		return patientcri.getbynamenademail(name, email);
	}

	@Override
	public List<Patient> patientserach(PatientSearchPojo search) {

		return patientcri.patientserach(search);
	}
	
	/*@Override
	public List<Patient> getbynamenadage(String name,Integer startingAge,Integer endingAge){
		
		return patientcri.getbynamenadage(name, startingAge, endingAge);
	}*/

	@Override
	public List<Patient> getalldetails() {

		return patientrepo.findAll();
	}

	@Override
	public Patient getbyid(Integer id) {

		Optional<Patient> opt = patientrepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public List<Patient> getbynameandage(String name, Integer startingAge, Integer endingAge) {
		
		return patientcri.getbynamenadage(name, startingAge, endingAge);
	}

}
