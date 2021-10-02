package com.ghd.sefatool.dto;

import java.util.List;

import com.ghd.sefatool.projection.InputTableColumnsProjection;

public class InputTableDetailsDTO {

	Integer inputTableId;
	
	String inputTableName;
	
	List<InputTableColumnsProjection> inputColumns;

	public Integer getInputTableId() {
		return inputTableId;
	}

	public void setInputTableId(Integer inputTableId) {
		this.inputTableId = inputTableId;
	}

	public String getInputTableName() {
		return inputTableName;
	}

	public void setInputTableName(String inputTableName) {
		this.inputTableName = inputTableName;
	}

	public List<InputTableColumnsProjection> getInputColumns() {
		return inputColumns;
	}

	public void setInputColumns(List<InputTableColumnsProjection> inputColumns) {
		this.inputColumns = inputColumns;
	}
	
}
