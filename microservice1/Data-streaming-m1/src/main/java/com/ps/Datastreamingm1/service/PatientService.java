package com.ps.Datastreamingm1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.Datastreamingm1.dao.PatientDao;
import com.ps.Datastreamingm1.dto.Encounter;

@Service
public class PatientService {

	@Autowired
	PatientDao patientDao;

	public Integer createPatient(Integer personId, Integer guarantorId, Encounter encounter) {

		return patientDao.createPatient(personId, guarantorId, encounter.getPatient());

	}

	public Map<String, Integer> checkIfPatientExists(Encounter encounter) {
		// TODO Auto-generated method stub
		return patientDao.checkIfPatientExists(encounter);
	}

}
