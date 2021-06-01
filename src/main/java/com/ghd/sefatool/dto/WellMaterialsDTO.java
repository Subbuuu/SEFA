package com.ghd.sefatool.dto;

import java.util.List;

import com.ghd.sefatool.projection.WellMaterialProjection;

public class WellMaterialsDTO {

	List<WellMaterialProjection> materialsRequired;
	
	public WellMaterialsDTO (List<WellMaterialProjection> materialsRequired) {
		super();
		this.materialsRequired = materialsRequired;
	}

	public WellMaterialsDTO() {
		super();
	}

	public List<WellMaterialProjection> getMaterialsRequired() {
		return materialsRequired;
	}

	public void setMaterialsRequired(List<WellMaterialProjection> materialsRequired) {
		this.materialsRequired = materialsRequired;
	}
	
}
