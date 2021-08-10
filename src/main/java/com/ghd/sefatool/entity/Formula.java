package com.ghd.sefatool.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Formula implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
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
	
	@Column(name = "input_table_column_id")
	Integer inputTableColumnId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Integer getNumberOfVariablesUsed() {
		return numberOfVariablesUsed;
	}

	public void setNumberOfVariablesUsed(Integer numberOfVariablesUsed) {
		this.numberOfVariablesUsed = numberOfVariablesUsed;
	}

	public String getVariablesUsed() {
		return variablesUsed;
	}

	public void setVariablesUsed(String variablesUsed) {
		this.variablesUsed = variablesUsed;
	}

	public String getTotalVariables() {
		return totalVariables;
	}

	public void setTotalVariables(String totalVariables) {
		this.totalVariables = totalVariables;
	}

	public Integer getInputTableColumnId() {
		return inputTableColumnId;
	}

	public void setInputTableColumnId(Integer inputTableColumnId) {
		this.inputTableColumnId = inputTableColumnId;
	}
	
	
}
