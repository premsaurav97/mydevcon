package com.ps.Datastreamingm1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.Datastreamingm1.dto.Encounter;
import com.ps.Datastreamingm1.service.EncounterService;

@RestController
@RequestMapping("/encounters")
public class EncounterController {

	@Autowired
	EncounterService encounterService;

	@PostMapping("/newentries")
	@ResponseBody
	public ResponseEntity<Map<String, String>> createEntry(@RequestBody List<Encounter> encounter) {

		return encounterService.create(encounter);

	}
	
	@PatchMapping("/{encounterId}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateEncounte(@RequestBody Encounter encounter, @PathVariable Integer encounterId) {

		return encounterService.updateEncounters(encounter,encounterId);


	}

}
