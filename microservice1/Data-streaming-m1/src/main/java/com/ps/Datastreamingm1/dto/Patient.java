package com.ps.Datastreamingm1.dto;

import java.util.List;

import javax.persistence.Convert;

import com.ps.Datastreamingm1.config.StringToListConverter;

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
public class Patient extends Person {

	Long patientId;
	Long emrRecordId;
	@Convert(converter = StringToListConverter.class)
	List<String> symptoms;
	String infectiousDiseaseCode;

}
