package com.slokam.healthcare.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface IVisitingService {

	public List<Object[]> getbydrtId(Integer id);

}
