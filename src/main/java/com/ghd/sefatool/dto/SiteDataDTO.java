package com.ghd.sefatool.dto;

import java.util.List;

public class SiteDataDTO {

	Boolean isSummaryActive;
	
	List<SiteNameDTO> sites;
	
	List<InputTableDetailsDTO> inputTables;
		
	public Boolean getIsSummaryActive() {
		return isSummaryActive;
	}
	
	public void setIsSummaryActive(Boolean showSummary) {
		this.isSummaryActive = showSummary;
	}
	
	public List<SiteNameDTO> getSites() {
		return sites;
	}
	
	public void setSites(List<SiteNameDTO> sites) {
		this.sites = sites;
	}

	public List<InputTableDetailsDTO> getInputTables() {
		return inputTables;
	}

	public void setInputTables(List<InputTableDetailsDTO> inputTables) {
		this.inputTables = inputTables;
	}
	
}
