package com.ghd.sefatool.projection;

public class WellMaterialProjection {

	public Float casingMaterial;
	
	public Float screenMaterial;
	
	public Float groutForAnnulus;
	
	public Float waterForAnnulus;
	
	public Float sandPackMaterial;
	
	public Float soilCuttingsForDisposal;
	
	public Float groutToAbandonWells;
	
	public Float waterForGroutToAbandonWells;
	
	public Integer wellDetailsId;

	public WellMaterialProjection(Float casingMaterial, Float screenMaterial, Float groutForAnnulus,
			Float waterForAnnulus, Float sandPackMaterial, Float soilCuttingsForDisposal, Float groutToAbandonWells,
			Float waterForGroutToAbandonWells, Integer wellDetailsId) {
		super();
		this.casingMaterial = casingMaterial;
		this.screenMaterial = screenMaterial;
		this.groutForAnnulus = groutForAnnulus;
		this.waterForAnnulus = waterForAnnulus;
		this.sandPackMaterial = sandPackMaterial;
		this.soilCuttingsForDisposal = soilCuttingsForDisposal;
		this.groutToAbandonWells = groutToAbandonWells;
		this.waterForGroutToAbandonWells = waterForGroutToAbandonWells;
		this.wellDetailsId = wellDetailsId;
	}
	
}
