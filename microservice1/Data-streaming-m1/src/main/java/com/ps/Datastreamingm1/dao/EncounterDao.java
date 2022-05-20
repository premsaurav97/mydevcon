package com.ps.Datastreamingm1.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ps.Datastreamingm1.dto.Encounter;
import com.ps.Datastreamingm1.util.SQLQueries;

@Component
public class EncounterDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public String createEncounter(Integer patientId, Integer guarantorId, Encounter encounter) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("patientId", patientId);
		paramMap.put("guarantorId", guarantorId);
		paramMap.put("startTime", encounter.getStartTime());
		paramMap.put("releaseTime", encounter.getReleaseTime());
		paramMap.put("charges", encounter.getCharges());
		namedParameterJdbcTemplate.update(SQLQueries.INSERT_ENCOUNTER_QUERY, paramMap);
		return "Records inserted successfully";
	}

	

	

	public String updateEncounterDao(Encounter encounter, Integer encounterId) {

		Map<String, Object> paramMap = new HashMap<>();

		System.out.println(encounter.getReleaseTime());
		paramMap.put("releaseTime", encounter.getReleaseTime());
		paramMap.put("charges", encounter.getCharges());
		paramMap.put("encounterId", encounterId);
		System.out.println();
		namedParameterJdbcTemplate.update(SQLQueries.UPDATE_ENCOUNTER_QUERY, paramMap);
		return "Records inserted successfully";
	}

	public boolean encounterExists(Integer encounterId) {

		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("encounterId", encounterId);
		Integer result=namedParameterJdbcTemplate.query(SQLQueries.FETCH_ENCOUNTER_EXIST, paramMap,
				(ResultSetExtractor<Integer>) rs -> {
					Integer result1=0;
					if (rs.next()) {
						result1 = rs.getInt(1);
					}
					return result1;
				});
		System.out.println(result);
		return result == 1 ? true : false;
	}

}
