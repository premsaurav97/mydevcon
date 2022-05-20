package com.ps.Datastreamingm1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.Datastreamingm1.dao.PersonDao;
import com.ps.Datastreamingm1.dto.Encounter;

@Service
public class PersonService {

	@Autowired
	PersonDao personDao;

	public Integer createPerson(Encounter encounter) {
		return personDao.createPerson(encounter);
	}

}
