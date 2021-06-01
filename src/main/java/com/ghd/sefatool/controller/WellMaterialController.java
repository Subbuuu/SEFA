package com.ghd.sefatool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghd.sefatool.dto.WellMaterialsDTO;
import com.ghd.sefatool.entity.WellDetails;
import com.ghd.sefatool.service.WellMaterialService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/wellmaterials", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class WellMaterialController {

	@Autowired
	WellMaterialService wellMaterialServiceImpl ;
	
	@GetMapping("/materialsRequired")
	public WellMaterialsDTO getWellMaterialsRequired() {
		return wellMaterialServiceImpl.getWellMaterialsRequired();
	}
	
	@PostMapping("/wellDetails")
	public ResponseEntity<String> saveWellDetails(@RequestBody WellDetails wellDetails) {
		ResponseEntity<String> status = wellMaterialServiceImpl.saveWellDetails(wellDetails);
		return status;
	}
}
