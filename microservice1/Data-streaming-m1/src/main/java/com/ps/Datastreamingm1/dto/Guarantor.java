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
public class Guarantor extends Person {


	String cardNumber;
	int creditRating;
	int householdIncome;
}
