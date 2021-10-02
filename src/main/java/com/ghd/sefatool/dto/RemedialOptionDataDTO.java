package com.ghd.sefatool.dto;

import java.util.List;

public class RemedialOptionDataDTO {

    Integer siteId;
	
	Boolean isSummaryActive;
	
	List<RemedialOptionDTO> remedialOptions;

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Boolean getIsSummaryActive() {
		return isSummaryActive;
	}

	public void setIsSummaryActive(Boolean isSummaryActive) {
		this.isSummaryActive = isSummaryActive;
	}

	public List<RemedialOptionDTO> getRemedialOptions() {
		return remedialOptions;
	}

	public void setRemedialOptions(List<RemedialOptionDTO> remedialOptions) {
		this.remedialOptions = remedialOptions;
	}
	
}
