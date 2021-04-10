package com.slokam.healthcare.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slokam.healthcare.repo.IVisitngRepo;
import com.slokam.healthcare.service.IVisitingService;

@Service
public class VisitingServiceImpl implements IVisitingService{

	@Autowired
	private IVisitngRepo visitrepo;
	
	@Override
	public List<Object[]> getbydrtId(Integer id) {
		
		return visitrepo.getbyidofvisitings(id);
	}
	
	

}
