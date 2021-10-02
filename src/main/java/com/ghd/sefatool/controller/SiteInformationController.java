package com.ghd.sefatool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghd.sefatool.dto.RemedialOptionDTO;
import com.ghd.sefatool.dto.RemedialOptionDataDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.dto.SiteDataDTO;
import com.ghd.sefatool.service.SiteInformationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/siteInformation", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SiteInformationController {

	@Autowired
	SiteInformationService siteInformationService;
	
	@GetMapping("/siteData")
	public ResponseDTO<SiteDataDTO> getSiteData() {
		return siteInformationService.getSiteData();
		}

	@GetMapping("/remedialOptionData")
	public ResponseDTO<RemedialOptionDataDTO> getRemedialOptionData(@RequestParam Integer siteId) {
		return siteInformationService.getRemedialOptionData(siteId);
		
	}
}