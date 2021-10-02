package com.ghd.sefatool.dto;

import java.util.List;

public class RemedialOptionDTO {

	Integer remedialOptionId;
	
	String remedialOption;
	
	List<ComponentDTO> components;
	
	public Integer getRemedialOptionId() {
		return remedialOptionId;
	}
	
	public void setRemedialOptionId(Integer remedialOptionId) {
		this.remedialOptionId = remedialOptionId;
	}
	
	public String getRemedialOption() {
		return remedialOption;
	}
	
	public void setRemedialOption(String remedialOption) {
		this.remedialOption = remedialOption;
	}
	
	public List<ComponentDTO> getComponents() {
		return components;
	}
	
	public void setComponents(List<ComponentDTO> components) {
		this.components = components;
	}
	
}
