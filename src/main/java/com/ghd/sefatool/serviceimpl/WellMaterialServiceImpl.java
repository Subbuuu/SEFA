package com.ghd.sefatool.serviceimpl;

import com.ghd.sefatool.dto.WellMaterialsDTO;
import com.ghd.sefatool.entity.WellDetails;
import com.ghd.sefatool.projection.WellMaterialProjection;
import com.ghd.sefatool.repository.WellDetailsRepository;
import com.ghd.sefatool.repository.WellMaterialsRequiredRepository;
import com.ghd.sefatool.service.WellMaterialService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WellMaterialServiceImpl implements WellMaterialService {

	@Autowired
	WellMaterialsRequiredRepository wellMaterialsRequiredRepository;
	
	@Autowired
	WellDetailsRepository wellDetailsRepository;
	
	@Override
	public WellMaterialsDTO getWellMaterialsRequired() {
		List<Integer> wellDetailsId = wellDetailsRepository.findIdByActice(); 
		List<WellMaterialProjection> materialsRequired = wellMaterialsRequiredRepository.findAllByWellDetailsIdIn(wellDetailsId);
		return new WellMaterialsDTO(materialsRequired);
	}

	@Override
	public ResponseEntity<String> saveWellDetails(WellDetails wellDetails) {
		try {
			wellDetailsRepository.save(wellDetails);	
		}
		catch(Exception e) {
			return new ResponseEntity<>("Data not saved", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	}

}
