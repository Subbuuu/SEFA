package com.ghd.sefatool.dto;

import java.util.List;

import com.ghd.sefatool.projection.InputTableColumnsProjection;

public class InputTableColumnsDTO {

	List<InputTableColumnsProjection> inputColumns;
	
	
	public InputTableColumnsDTO(List<InputTableColumnsProjection> inputColumns) {
		super();
		this.inputColumns = inputColumns;
	}


	public List<InputTableColumnsProjection> getInputColumns() {
		return inputColumns;
	}

	public void setInputColumns(List<InputTableColumnsProjection> inputColumns) {
		this.inputColumns = inputColumns;
	}


	public InputTableColumnsDTO() {
		super();
	}
}
