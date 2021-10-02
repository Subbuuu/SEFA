package com.ghd.sefatool.dto;

public class SiteNameDTO {

	Integer siteId;
	
	String siteName;
	
	public Integer getSiteId() {
		return siteId;
	}
	
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
	public String getSiteName() {
		return siteName;
	}
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public SiteNameDTO(Integer siteId, String siteName) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
	}
	
}
