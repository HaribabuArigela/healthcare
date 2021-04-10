package com.slokam.healthcare.repo;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.entity.Patient;
import com.slokam.healthcare.pojo.PatientSearchPojo;
import com.slokam.healthcare.utils.StringUtils;

@Repository
public class PatientCriteriaREPO {
	@Autowired
	private EntityManager em;

	public List<Patient> patientserach(PatientSearchPojo search) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
		Root<Patient> root = cq.from(Patient.class);
		List<Predicate> predicatelist = new ArrayList<>();
		if (search != null) {
			if (StringUtils.blankcheck(search.getName())) {
				predicatelist.add(cb.like(root.get("name"), search.getName() + "%"));
			}
			if (search.getPageno() != null) {
				predicatelist.add(cb.equal(root.get("phone"), search.getPhone()));
			}
			if (search.getFromdate() != null)
				predicatelist.add(cb.greaterThanOrEqualTo(root.get("regDate"), search.getFromdate()));
			if (search.getTodate() != null)
				predicatelist.add(cb.lessThanOrEqualTo(root.get("regDate"), search.getFromdate()));
			if (search.getStartingAge() != null)
				predicatelist.add(cb.ge(root.get("age"), search.getStartingAge()));
			if (search.getEndingAge() != null) {
				predicatelist.add(cb.le(root.get("age"), search.getEndingAge()));
			}
		}

		// cq.where(predicatelist.toArray(new Predicate[predicatelist.size()]));

		cq.where(predicatelist.toArray(new Predicate[predicatelist.size()]));
		TypedQuery<Patient> patientquery = em.createQuery(cq);
		return patientquery.getResultList();
	}

	public List<Patient> getbynamenademail(String name, String email) {

		CriteriaBuilder cd = em.getCriteriaBuilder();
		CriteriaQuery<Patient> critquer =cd.createQuery(Patient .class);
		
		Root<Patient> root= critquer.from(Patient.class);
		
		List<Predicate> namemail = new ArrayList<>();
		
		if( name != null && name.trim().length()>0) {
			namemail.add(cd.like(root.get("name"), "%"+name+"%"));
		}
		if(email != null) {
			namemail.add(cd.like(root.get("email"), "%"+email+"%"));
		}
		
		
		critquer.where(namemail.toArray(new Predicate[namemail.size()]));
              TypedQuery<Patient> pq= em.createQuery(critquer);
		return pq.getResultList();

	}
	public List<Patient> getbynamenadage(String name,Integer startingAge,Integer endingAge){
		PatientSearchPojo pp = new PatientSearchPojo();
		CriteriaBuilder cb =em.getCriteriaBuilder();
		CriteriaQuery<Patient> cq =cb.createQuery(Patient.class);
		
		 Root<Patient> root= cq.from(Patient.class);
		 List<Predicate> list = new ArrayList<>();
		 
		 if(name != null &&name.trim().length()>0) {
			 list.add(cb.like(root.get("name"), "%"+name+"%"));
		 }
		 if(startingAge !=null) {
			 list.add(cb.ge(root.get("age"), pp.getStartingAge()));
		 }
		 if(endingAge != null) {
			 list.add(cb.le(root.get("age"), pp.getEndingAge()));
		 }
		 cq.where(list.toArray(new Predicate[list.size()]));
		TypedQuery<Patient> tp= em.createQuery(cq);
		return tp.getResultList();
	}

}
