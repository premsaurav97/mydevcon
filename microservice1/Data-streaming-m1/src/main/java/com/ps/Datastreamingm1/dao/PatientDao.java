package com.ps.Datastreamingm1.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ps.Datastreamingm1.dto.Encounter;
import com.ps.Datastreamingm1.dto.Patient;
import com.ps.Datastreamingm1.util.SQLQueries;

@Component
public class PatientDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Creates patient record.
	 * 
	 * @param personId
	 * @param guarantorId
	 * @param patient
	 * @return patientId (pk)
	 */
	public Integer createPatient(Integer personId, Integer guarantorId, Patient patient) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("personId", personId);
		paramMap.put("guarantorId", guarantorId);
		paramMap.put("emrRecordId", patient.getEmrRecordId());
		paramMap.put("symptoms", patient.getSymptoms().toString());
		paramMap.put("infectionDiseaseCode", patient.getInfectiousDiseaseCode());
		return namedParameterJdbcTemplate.queryForObject(SQLQueries.INSERT_PATIENT_QUERY, paramMap, Integer.class);
	}

	public Map<String, Integer> checkIfPatientExists(Encounter encounter) {
		Map<String, Integer> list = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("patientSSN", encounter.getPatient().getSsn());
		return namedParameterJdbcTemplate.query(SQLQueries.FETCH_PATIENT_SSN_EXIST, paramMap,
				(ResultSetExtractor<Map<String, Integer>>) rs -> {

					if (rs.next()) {
						list.put("personId", rs.getInt(1));
						list.put("patientId", rs.getInt(2));
					}
					return list;
				});
	}

}
