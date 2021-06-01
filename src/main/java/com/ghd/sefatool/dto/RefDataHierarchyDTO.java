package com.ghd.sefatool.dto;

import java.util.List;

import lombok.Data;

@Data
public class RefDataHierarchyDTO {
	
	List<String> refDataHierarchy;

	public RefDataHierarchyDTO(List<String> refDataHierarchy) {
		super();
		this.refDataHierarchy = refDataHierarchy;
	}

	public List<String> getRefDataHierarchy() {
		return refDataHierarchy;
	}

	public void setRefDataHierarchy(List<String> refDataHierarchy) {
		this.refDataHierarchy = refDataHierarchy;
	}

	public RefDataHierarchyDTO() {
		super();
	}
	
}
