package com.slokam.healthcare.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.service.IPatientService;

@RestController
@RequestMapping("pateint")
public class PatientContoller {
	
	@Autowired
	private IPatientService patientservice;
	@Value("${app.file.upload.location}")
	private String uploadLocation;
	
	@PostMapping("/Saveimage")
	public ResponseEntity<String> getbyphotoDet(MultipartFile patimage){
		
		String name= System.currentTimeMillis()+"-"+patimage.getOriginalFilename();
		
		try {
			patimage.transferTo(new File(uploadLocation+name));
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<String>(name,HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient){
		patientservice.PatientRegistration(patient);
		return new ResponseEntity<Patient>(patient,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbynamenademail/{name}/{email}")
	public ResponseEntity<List<Patient>> getbynamenademail(@PathVariable String name,@PathVariable String email){
	List<Patient> pati=	patientservice.getbynamenademail(name, email);
	return new ResponseEntity<List<Patient>>(pati,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyname/{name}/{startingAge}/{endingAge}")
	public ResponseEntity<List<Patient>> getbynamenadage(@PathVariable String name,@PathVariable Integer startingAge,@PathVariable Integer endingAge){
		
	List<Patient> slp =patientservice.getbynameandage(name, startingAge, endingAge);
		return new ResponseEntity<List<Patient>>(slp,HttpStatus.OK);
	}
	@PostMapping("/serch")
	public ResponseEntity<List<Patient>> patientSearch(@RequestBody PatientSearchPojo serch ){
	List<Patient> lp=	patientservice.patientserach(serch);
		return ResponseEntity.ok(lp);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getalldetails(){
		List<Patient> lsp=  patientservice.getalldetails();
		return ResponseEntity.ok(lsp);
	}
	
	@GetMapping("/byid/{id}")
	public ResponseEntity<Patient> getbyid(@PathVariable Integer id){
	Patient pp =	patientservice.getbyid(id);
	System.out.println(pp);
	return ResponseEntity.ok(pp);
	}
	@GetMapping("/getevenpat")
	public ResponseEntity<List<Patient>> getevenpatientdetails(){
//		List<Patient> lsp=  patientservice.getalldetails();
//		return ResponseEntity.ok(lsp);
		//List<Patient> lsp=  patientservice.getalldetails();
		//return ResponseEntity.ok(lsp.stream().filter(patient->patient.getId()%2 == 0).collect(Collectors.toList()));
		return ResponseEntity.ok(patientservice.getalldetails().stream().filter(patient->patient.getId()%2 == 0).collect(Collectors.toList()));
	}
	
	@GetMapping("/getlowage/{age}")
	public ResponseEntity<List<Patient>> getlowagegiven(@PathVariable Integer age){
		return ResponseEntity.ok(patientservice.getalldetails().stream().filter(pat->pat.getAge()!=null&& pat.getAge()>age).collect(Collectors.toList()));
		
	}
	@GetMapping("/getonlynames")
	public ResponseEntity<List<String>> getonlynames(){
		return ResponseEntity.ok(patientservice.getalldetails().stream().filter(nam->nam.getName()!=null).map(name->name.getName()).collect(Collectors.toList()));
	}
	public  boolean nullcheck(Patient p ,Integer i) {
		boolean flag=false;
		if(Objects.nonNull(p) && p.getAge() !=null && p.getAge()>i) {
		flag=true;
		}
		return flag;
	}

}
