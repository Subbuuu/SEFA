package com.ghd.sefatool.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.ComponentDTO;
import com.ghd.sefatool.dto.ComponentPhaseDTO;
import com.ghd.sefatool.dto.InputTableDetailsDTO;
import com.ghd.sefatool.dto.RemedialOptionDTO;
import com.ghd.sefatool.dto.RemedialOptionDataDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.dto.SiteDataDTO;
import com.ghd.sefatool.dto.SiteNameDTO;
import com.ghd.sefatool.entity.ComponentInformation;
import com.ghd.sefatool.entity.ComponentPhaseInformation;
import com.ghd.sefatool.entity.RemedialOptionInformation;
import com.ghd.sefatool.projection.InputTableColumnsProjection;
import com.ghd.sefatool.repository.ComponentInformationRepository;
import com.ghd.sefatool.repository.ComponentPhaseInformationRepository;
import com.ghd.sefatool.repository.InputTableMetaRepository;
import com.ghd.sefatool.repository.InputTableNameRepository;
import com.ghd.sefatool.repository.RemedialOptionInformationRepository;
import com.ghd.sefatool.repository.SiteInformationRepository;
import com.ghd.sefatool.repository.UserInputTableValueRepository;
import com.ghd.sefatool.service.SiteInformationService;

@Service
public class SiteInformationServiceImpl implements SiteInformationService {

	@Autowired
	UserInputTableValueRepository userInputTableValueRepository;
	
	@Autowired
	SiteInformationRepository siteInformationRepository;
	
	@Autowired
	InputTableNameRepository inputTableNameRepository;
	
	@Autowired
	InputTableMetaRepository inputTableMetaRepository;
	
	@Autowired
	RemedialOptionInformationRepository remedialOptionInformationRepository;
	
	@Autowired
	ComponentInformationRepository componentInformationRepository;
	
	@Autowired
	ComponentPhaseInformationRepository componentPhaseInformationRepository;
	
	@Override
	public ResponseDTO<SiteDataDTO> getSiteData() {
		
		ResponseDTO<SiteDataDTO> response = new ResponseDTO<SiteDataDTO>();
		SiteDataDTO siteData = new SiteDataDTO();
		List<InputTableDetailsDTO> inputTables = new ArrayList<InputTableDetailsDTO>(); 
		List<SiteNameDTO> sites = new ArrayList<SiteNameDTO>();
		
		try {
			Long countUserData = userInputTableValueRepository.count();
			if(countUserData == 0) {
				siteData.setIsSummaryActive(false);
			}
			else {
				siteData.setIsSummaryActive(true);
			}
			sites = siteInformationRepository.getSiteData();
			siteData.setSites(sites);
			
			List<Integer> columnId = inputTableNameRepository.getIdByTableType("user_input");
			for(int i : columnId ) {
				InputTableDetailsDTO inputTableValues = new InputTableDetailsDTO();
				
				inputTableValues.setInputTableId(i);
				inputTableValues.setInputTableName(inputTableNameRepository.getNameById(i));
				
				List<InputTableColumnsProjection> inputTableColumns = inputTableMetaRepository.findAllByInputTableNameId(i);
				inputTableValues.setInputColumns(inputTableColumns);
				
				inputTables.add(inputTableValues);
			}
			
			siteData.setInputTables(inputTables);
			
			response.setMessage("Success");
			response.setResultSet(siteData);
			return response;
		}
		
		catch(Exception e) {
				response.setMessage("getSiteData Failed - " + e.getMessage());
				return response;
		}
	}

	@Override
	public ResponseDTO<RemedialOptionDataDTO> getRemedialOptionData(Integer siteId) {
		
		ResponseDTO<RemedialOptionDataDTO> response = new ResponseDTO<RemedialOptionDataDTO>();
		RemedialOptionDataDTO remedialOptionData = new RemedialOptionDataDTO();
		List<RemedialOptionDTO> remedialOption = new ArrayList<RemedialOptionDTO>();
		
		try {
			
			remedialOptionData.setSiteId(siteId);
			Long countUserData = userInputTableValueRepository.count();
			if(countUserData == 0) {
				remedialOptionData.setIsSummaryActive(false);
			}
			else {
				remedialOptionData.setIsSummaryActive(true);
			}
			
			List<RemedialOptionInformation> remedialOptions = remedialOptionInformationRepository.getRemedialOptions(siteId);
			
			for(RemedialOptionInformation i : remedialOptions) {
				
				List<ComponentDTO> componentData = new ArrayList<ComponentDTO>();
				
				RemedialOptionDTO remedial = new RemedialOptionDTO();
				remedial.setRemedialOptionId(i.getRemedialOptionId());
				remedial.setRemedialOption(i.getRemedialOptionName());
				
				List<ComponentInformation> components = componentInformationRepository.getComponents(i.getRemedialOptionId());
				
				for(ComponentInformation j : components) {
					
					List<ComponentPhaseDTO> componentPhaseData = new ArrayList<ComponentPhaseDTO>();
					
					ComponentDTO component = new ComponentDTO();
					component.setComponentId(j.getComponentId());
					component.setComponent(j.getComponentName());
					
					List<ComponentPhaseInformation> componentPhases = componentPhaseInformationRepository.getComponentPhases(j.getComponentId());
					
					for(ComponentPhaseInformation k : componentPhases) {
						
						ComponentPhaseDTO phase = new ComponentPhaseDTO();
						phase.setPhaseId(k.getComponentPhaseId());
						phase.setPhase(k.getComponentPhaseName());
						
						componentPhaseData.add(phase);
					}
					
					component.setPhases(componentPhaseData);
					componentData.add(component);
				}
				
				remedial.setComponents(componentData);
				remedialOption.add(remedial);
			}
			
			remedialOptionData.setRemedialOptions(remedialOption);
			
			response.setMessage("Success");
			response.setResultSet(remedialOptionData);
			return response;
		}
		
		catch(Exception e) {
			response.setMessage("getRemedialOptionData Failed - " + e.getMessage());
			return response;
		}
	}
	
}
