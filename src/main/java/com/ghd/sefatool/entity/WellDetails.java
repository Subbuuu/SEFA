package com.ghd.sefatool.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class WellDetails extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String typeOfWell;
	
	String wellCasingMaterial;
	
	Float totalWellDepth;
	
	Float screenLength;
	
	Float wellCasingDiameter;
	
	Float stickupHeight;
	
	Float numberOfWells;
	
	Boolean isInUse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeOfWell() {
		return typeOfWell;
	}

	public void setTypeOfWell(String typeOfWell) {
		this.typeOfWell = typeOfWell;
	}

	public String getWellCasingMaterial() {
		return wellCasingMaterial;
	}

	public void setWellCasingMaterial(String wellCasingMaterial) {
		this.wellCasingMaterial = wellCasingMaterial;
	}

	public Float getTotalWellDepth() {
		return totalWellDepth;
	}

	public void setTotalWellDepth(Float totalWellDepth) {
		this.totalWellDepth = totalWellDepth;
	}

	public Float getScreenLength() {
		return screenLength;
	}

	public void setScreenLength(Float screenLength) {
		this.screenLength = screenLength;
	}

	public Float getWellCasingDiameter() {
		return wellCasingDiameter;
	}

	public void setWellCasingDiameter(Float wellCasingDiameter) {
		this.wellCasingDiameter = wellCasingDiameter;
	}

	public Float getStickupHeight() {
		return stickupHeight;
	}

	public void setStickupHeight(Float stickupHeight) {
		this.stickupHeight = stickupHeight;
	}

	public Float getNumberOfWells() {
		return numberOfWells;
	}

	public void setNumberOfWells(Float numberOfWells) {
		this.numberOfWells = numberOfWells;
	}

	public Boolean getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(Boolean isInUse) {
		this.isInUse = isInUse;
	}
	
}
