package com.ps.Datastreamingm1.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Encounter {

	Patient patient;

	Guarantor guarantor;

	String startTime;

	String releaseTime;

	int charges;
	
	boolean personType;

}
