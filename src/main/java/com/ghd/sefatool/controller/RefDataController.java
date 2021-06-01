package com.ghd.sefatool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghd.sefatool.dto.RefDataDTO;
import com.ghd.sefatool.dto.RefDataHierarchyDTO;
import com.ghd.sefatool.entity.RefData;
import com.ghd.sefatool.entity.RefDataHierarchy;
import com.ghd.sefatool.repository.RefDataHierarchyRepository;
import com.ghd.sefatool.repository.RefDataRepository;
import com.ghd.sefatool.service.RefDataService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping(value = "/api/refdatas", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RefDataController {

	@Autowired
	RefDataService refDataServiceImpl;
	
	@Autowired
	RefDataRepository refDataRepository;
	
	@Autowired
	RefDataHierarchyRepository refDataHierarchyRepository;
	
	@GetMapping("/v1")
	public RefDataDTO getRefDataValue(@RequestParam(required = false)  String classCode) {
		RefDataDTO refDataDTOList = refDataServiceImpl.getRefData(classCode.toUpperCase());
		return refDataDTOList;
	}
	
	@PostMapping("/inputv1")
	public ResponseEntity<String> insertRefDataValue(@RequestBody RefData refData) {
		ResponseEntity<String> status = refDataServiceImpl.insertRefDataValue(refData);
		return status;
	}
	
	@GetMapping("/v2")
	public RefDataHierarchyDTO getRefDataHierarchyValue(@RequestParam(required = false) String parentCode) {
		RefDataHierarchyDTO refDataHierarchyDTOList = refDataServiceImpl.getRefDataHierarchy(parentCode.toUpperCase());
		return refDataHierarchyDTOList;	
	}

	@PostMapping("/inputv2")
	public ResponseEntity<String> insertRefDataHierarchyValue (@RequestBody RefDataHierarchy refDataHierarchy) {
		ResponseEntity<String> status = refDataServiceImpl.insertRefDataHierarchyValue(refDataHierarchy);
		return status;
	}
}
