package com.slokam.healthcare.service;

import java.util.List;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;

public interface IPatientService {

	
	public void PatientRegistration(Patient patient);
	public List<Patient>  getbynamenademail(String name, String email); 
	
	public List<Patient> getbynameandage(String name ,Integer startingAge,Integer endingAge);
      public List<Patient> patientserach(PatientSearchPojo search);
     public List<Patient> getalldetails();
     public  Patient getbyid(Integer ID);
     
   
}
