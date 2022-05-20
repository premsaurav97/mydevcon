package com.ps.Datastreamingm1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ps.Datastreamingm1.dao.EncounterDao;
import com.ps.Datastreamingm1.dto.Encounter;

@Service
public class EncounterService {

	@Autowired
	PersonService personService;

	@Autowired
	GuarantorService guarantorService;

	@Autowired
	PatientService patientService;

	@Autowired
	EncounterDao encounterDao;

	@Transactional
	public ResponseEntity<Map<String, String>> create(List<Encounter> encounters) {

		for (Encounter encounter : encounters) {

			List<String> recordStatus = new ArrayList<>();

			Integer personId;
			Integer guarantorId;
			Integer patientId;

			Map<String, Integer> guarantorExists = guarantorService.checkIfGuarantorExists(encounter);
			Map<String, Integer> patientExists = patientService.checkIfPatientExists(encounter);

			if (patientExists.isEmpty() || guarantorExists.isEmpty()) {

				if (guarantorExists.isEmpty()) {

					// for guarantor creation
					personId = personService.createPerson(encounter);
					guarantorId = guarantorService.createGuarantor(personId, encounter);
				} else {
					personId = guarantorExists.get("personId");
					guarantorId = guarantorService.createGuarantor(personId, encounter);
				}

				// for patient creation
				encounter.setPersonType(true); // change persontype to patient
				if (patientExists.isEmpty()) {

					personId = personService.createPerson(encounter);
					patientId = patientService.createPatient(personId, guarantorId, encounter);

				} else {

					personId = patientExists.get("personId");
					patientId = patientService.createPatient(personId, guarantorId, encounter);
				}

				// encounter creation
				String message = encounterDao.createEncounter(patientId, guarantorId, encounter);

				System.out.println(message);
			} else {
				recordStatus.add("Record exists");
			}
			System.out.println(recordStatus.toString());
		}
		return new ResponseEntity<>( HttpStatus.OK);
	}

	public ResponseEntity<Map<String, String>> updateEncounters(Encounter encounter, Integer encounterId) {

		Map<String, String> message = new HashMap<>();

		if (encounterDao.encounterExists(encounterId)) {

			encounterDao.updateEncounterDao(encounter, encounterId);
			message.put("message", "Encounter-Id " + encounterId + " updated.");
			return new ResponseEntity<>(message, HttpStatus.OK);

		}
		System.out.println("Encounter doesn't exists");
		message.put("message", "Encounter-Id " + encounterId + " not found");

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
