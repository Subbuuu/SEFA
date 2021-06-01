package com.ghd.sefatool.service;

import org.springframework.http.ResponseEntity;

import com.ghd.sefatool.dto.WellMaterialsDTO;
import com.ghd.sefatool.entity.WellDetails;

public interface WellMaterialService {

	public WellMaterialsDTO getWellMaterialsRequired();

	public ResponseEntity<String> saveWellDetails(WellDetails wellDetails);

}
