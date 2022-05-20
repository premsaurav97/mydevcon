package com.ps.Datastreamingm1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.Datastreamingm1.dao.GuarantorDao;
import com.ps.Datastreamingm1.dto.Encounter;

@Service
public class GuarantorService {

	@Autowired
	GuarantorDao guarantorDao;

	public Map<String, Integer> checkIfGuarantorExists(Encounter encounter) {

		return guarantorDao.checkIfGuarantorExists(encounter);
	}

	public Integer createGuarantor(Integer personId, Encounter encounter) {

		return guarantorDao.createGuarantor(personId, encounter.getGuarantor());
	}

}
