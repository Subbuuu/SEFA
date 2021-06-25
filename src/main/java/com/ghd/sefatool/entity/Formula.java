package com.ghd.sefatool.entity;

import javax.persistence.Column;

import lombok.Data;


@Data
public class Formula {
	Integer id;
	
	@Column(name = "formula_name")
	String formulaName;
	
	String formula;
	
	@Column(name = "number_of_variables_used")
	Integer numberOfVariablesUsed;
	
	@Column(name = "variables_used")
	String variablesUsed;
	
	@Column(name = "total_variables")
	String totalVariables;
}
