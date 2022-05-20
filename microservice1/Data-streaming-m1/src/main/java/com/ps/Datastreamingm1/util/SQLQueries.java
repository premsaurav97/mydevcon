package com.ps.Datastreamingm1.util;

public class SQLQueries {

	public static final String INSERT_PERSON_QUERY = "INSERT into Person"
			+ " (firstName, LastName, phoneNo, address, age, gender, ssn) output inserted.id "
			+ " values(:firstName, :lastName, :phnNo, :address, :age, :gender, :ssn)";

	public static final String INSERT_GUARANTOR_QUERY = "Insert into Guarantor"
			+ " (personId, cardNo, CreditRating, HouseholdIncome) output inserted.id values (:personId, :cardNumber, :creditRating, :householdIncome)";

	public static final String INSERT_PATIENT_QUERY = "Insert into patient(personId, guarantorId, emrRecordId, symptoms, infectionDiseaseCode) "
			+ "output inserted.id values(:personId, :guarantorId, :emrRecordId, :symptoms, :infectionDiseaseCode)";

	public static final String INSERT_ENCOUNTER_QUERY = "Insert into encounter(patientId, guarantorId, startTime, releaseTime, charges)"
			+ " values (:patientId, :guarantorId, :startTime, :releaseTime, :charges)";

	public static final String FETCH_GUARANTOR_SSN_EXIST = "SELECT person.id as personId, guarantor.id as guarantorId FROM person "
			+ "INNER JOIN Guarantor ON person.id = Guarantor.personId where ssn=:guarantorSSN";

	public static final String FETCH_PATIENT_SSN_EXIST = "SELECT person.id as personId, patient.id as patientId from person "
			+ "INNER JOIN Patient ON person.id = Patient.personId AND ssn=:patientSSN";

	public static final String UPDATE_ENCOUNTER_QUERY = "UPDATE Encounter set releaseTime=:releaseTime , charges=:charges where id=:encounterId";
	
	public static final String FETCH_ENCOUNTER_EXIST = "select 1 from Encounter where id=:encounterId";
}
