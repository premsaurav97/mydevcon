package com.ps.Datastreamingm1.dto;

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
public class Person {

	String firstName;
	String lastName;
	String phoneNo;
	String address;
	int age;
	String gender;
	String ssn;
}
