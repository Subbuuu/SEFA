package com.ghd.sefatool.projection;

public class InputTableColumnsProjection {

	public String inputColumnName;
	
	public String inputColumnType;
	
	public String inputColumnCode;

	public String getInputColumnName() {
		return inputColumnName;
	}

	public void setInputColumnName(String inputColumnName) {
		this.inputColumnName = inputColumnName;
	}

	public String getInputColumnType() {
		return inputColumnType;
	}

	public void setInputColumnType(String inputColumnType) {
		this.inputColumnType = inputColumnType;
	}

	public String getInputColumnCode() {
		return inputColumnCode;
	}

	public void setInputColumnCode(String inputColumnCode) {
		this.inputColumnCode = inputColumnCode;
	}

	public InputTableColumnsProjection(String inputColumnName, String inputColumnType, String inputColumnCode) {
		super();
		this.inputColumnName = inputColumnName;
		this.inputColumnType = inputColumnType;
		this.inputColumnCode = inputColumnCode;
	}
	
	
}
