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
public class WellMaterialsRequired extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Float casingMaterial;
	
	Float screenMaterial;
	
	Float groutForAnnulus;
	
	Float waterForAnnulus;
	
	Float sandPackMaterial;
	
	Float soilCuttingsForDisposal;
	
	Float groutToAbandonWells;
	
	Float waterForGroutToAbandonWells;
	
	Integer wellDetailsId;

	public Integer getWellDetailsId() {
		return wellDetailsId;
	}

	public void setWellDetailsId(Integer wellDetailsId) {
		this.wellDetailsId = wellDetailsId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getCasingMaterial() {
		return casingMaterial;
	}

	public void setCasingMaterial(Float casingMaterial) {
		this.casingMaterial = casingMaterial;
	}

	public Float getScreenMaterial() {
		return screenMaterial;
	}

	public void setScreenMaterial(Float screenMaterial) {
		this.screenMaterial = screenMaterial;
	}

	public Float getGroutForAnnulus() {
		return groutForAnnulus;
	}

	public void setGroutForAnnulus(Float groutForAnnulus) {
		this.groutForAnnulus = groutForAnnulus;
	}

	public Float getWaterForAnnulus() {
		return waterForAnnulus;
	}

	public void setWaterForAnnulus(Float waterForAnnulus) {
		this.waterForAnnulus = waterForAnnulus;
	}

	public Float getSandPackMaterial() {
		return sandPackMaterial;
	}

	public void setSandPackMaterial(Float sandPackMaterial) {
		this.sandPackMaterial = sandPackMaterial;
	}

	public Float getSoilCuttingsForDisposal() {
		return soilCuttingsForDisposal;
	}

	public void setSoilCuttingsForDisposal(Float soilCuttingsForDisposal) {
		this.soilCuttingsForDisposal = soilCuttingsForDisposal;
	}

	public Float getGroutToAbandonWells() {
		return groutToAbandonWells;
	}

	public void setGroutToAbandonWells(Float groutToAbandonWells) {
		this.groutToAbandonWells = groutToAbandonWells;
	}

	public Float getWaterForGroutToAbandonWells() {
		return waterForGroutToAbandonWells;
	}

	public void setWaterForGroutToAbandonWells(Float waterForGroutToAbandonWells) {
		this.waterForGroutToAbandonWells = waterForGroutToAbandonWells;
	}
	
	
}
