package com.ghd.sefatool.dto;

import java.util.List;

import lombok.Data;

@Data
public class RefDataDTO {

	public List<String> getRefData() {
		return refData;
	}

	public void setRefData(List<String> refData) {
		this.refData = refData;
	}

	List<String> refData;	
	
	public RefDataDTO(List<String> refData) {
		super();
		this.refData = refData;
	}

	public RefDataDTO() {
		super();
	}
	
}
