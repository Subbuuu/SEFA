package com.ghd.sefatool.service;


import com.ghd.sefatool.dto.RemedialOptionDTO;
import com.ghd.sefatool.dto.RemedialOptionDataDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.dto.SiteDataDTO;

public interface SiteInformationService {

	ResponseDTO<SiteDataDTO> getSiteData();

	ResponseDTO<RemedialOptionDataDTO> getRemedialOptionData(Integer siteId);

}
