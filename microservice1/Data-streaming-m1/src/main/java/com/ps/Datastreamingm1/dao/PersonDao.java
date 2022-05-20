package com.ps.Datastreamingm1.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ps.Datastreamingm1.dto.Encounter;
import com.ps.Datastreamingm1.dto.Person;
import com.ps.Datastreamingm1.util.SQLQueries;

@Component
public class PersonDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * Creates person record. 
	 * 
	 * @param encounter
	 * @return personId (pk)
	 */
	public Integer createPerson(Encounter encounter) {
		
		Person person = encounter.getGuarantor();
		if (encounter.isPersonType()) {
			person = encounter.getPatient();
		}

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstName", person.getFirstName());
		paramMap.put("lastName", person.getLastName());
		paramMap.put("phnNo", person.getPhoneNo());
		paramMap.put("address", person.getAddress());
		paramMap.put("age", person.getAge());
		paramMap.put("gender", person.getGender());
		paramMap.put("ssn", person.getSsn());

		return namedParameterJdbcTemplate.queryForObject(SQLQueries.INSERT_PERSON_QUERY, paramMap, Integer.class);

	}

}
