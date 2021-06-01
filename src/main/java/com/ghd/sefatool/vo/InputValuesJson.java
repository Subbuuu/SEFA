package com.ghd.sefatool.vo;

import java.util.Map;

public class InputValuesJson {
	
	String remedialOptionName;
	
	String componentName;
	
	String componentPhase;
	
	String inputTableName;
	
	Map<String,String> columnValues;

	public String getRemedialOptionName() {
		return remedialOptionName;
	}

	public void setRemedialOptionName(String remedialOptionName) {
		this.remedialOptionName = remedialOptionName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentPhase() {
		return componentPhase;
	}

	public void setComponentPhase(String componentPhase) {
		this.componentPhase = componentPhase;
	}

	public String getInputTableName() {
		return inputTableName;
	}

	public void setInputTableName(String inputTableName) {
		this.inputTableName = inputTableName;
	}

	public Map<String, String> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(Map<String, String> columnValues) {
		this.columnValues = columnValues;
	}

	public InputValuesJson(String remedialOptionName, String componentName, String componentPhase,
			String inputTableName, Map<String, String> columnValues) {
		super();
		this.remedialOptionName = remedialOptionName;
		this.componentName = componentName;
		this.componentPhase = componentPhase;
		this.inputTableName = inputTableName;
		this.columnValues = columnValues;
	}
	
}
