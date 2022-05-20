package com.ps.Datastreamingm1.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.ps.Datastreamingm1.dto.Encounter;
import com.ps.Datastreamingm1.dto.Guarantor;
import com.ps.Datastreamingm1.util.SQLQueries;

@Component
public class GuarantorDao {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * Creates guarantor record. 
	 * 
	 * @param personId
	 * @param guarantor
	 * @return guarantorId (pk)
	 */
	public Integer createGuarantor(Integer personId, Guarantor guarantor)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("personId", personId);
		paramMap.put("cardNumber", guarantor.getCardNumber());
		paramMap.put("creditRating", guarantor.getCreditRating());
		paramMap.put("householdIncome", guarantor.getHouseholdIncome());
		return namedParameterJdbcTemplate.queryForObject(SQLQueries.INSERT_GUARANTOR_QUERY,
				paramMap, Integer.class);
	}
	
	public Map<String, Integer> checkIfGuarantorExists(Encounter encounter) {

		Map<String, Integer> list = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("guarantorSSN", encounter.getGuarantor().getSsn());
		return namedParameterJdbcTemplate.query(SQLQueries.FETCH_GUARANTOR_SSN_EXIST, paramMap,
				(ResultSetExtractor<Map<String, Integer>>) rs -> {
					if (rs.next()) {
						System.out.println((Integer) rs.getInt(1));
						System.out.println(rs.getInt(2));
						list.put("personId", rs.getInt(1));
						list.put("guarantorId", rs.getInt(2));
					}
					return list;
				});

	}

}
