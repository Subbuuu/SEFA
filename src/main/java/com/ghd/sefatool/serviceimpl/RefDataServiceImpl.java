package com.ghd.sefatool.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.RefDataDTO;
import com.ghd.sefatool.dto.RefDataHierarchyDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.entity.RefData;
import com.ghd.sefatool.entity.RefDataHierarchy;
import com.ghd.sefatool.repository.RefDataHierarchyRepository;
import com.ghd.sefatool.repository.RefDataRepository;
import com.ghd.sefatool.service.RefDataService;

@Service
public class RefDataServiceImpl implements RefDataService {

	@Autowired
	RefDataRepository refDataRepository; 
	
	@Autowired
	RefDataHierarchyRepository refDataHierarchyRepository;
			
	@Override
	public ResponseDTO<RefDataDTO> getRefData(String classCode) {
		
		ResponseDTO<RefDataDTO> response = new ResponseDTO<RefDataDTO>();
		
		try {
			List<String> refData = refDataRepository.findValueByClassCode(classCode);
			RefDataDTO refDataValues = new RefDataDTO(refData);
			response.setMessage("Success");
			response.setResultSet(refDataValues);
			return response;
		}
		
		catch(Exception e) {
			response.setMessage("-- getRefData Failed - " + e.getMessage() + " --");
			return response;
		}
		
	}

	@Override
	public RefDataHierarchyDTO getRefDataHierarchy(String parentCode) {
		List<String> refDataHierarchy = refDataRepository.findCodeByParentCode(parentCode);
		return new RefDataHierarchyDTO(refDataHierarchy);
	}

	@Override
	public ResponseDTO<ResponseEntity<String>> insertRefDataValue(RefData refData) {
		
		ResponseDTO<ResponseEntity<String>> response = new ResponseDTO<ResponseEntity<String>>();
		
		try {
			refDataRepository.save(refData);
			response.setMessage("Success");
			response.setResultSet(new ResponseEntity<>("Value saved successfully", HttpStatus.OK));
			return response;
		}
		
		catch(Exception e) {
			response.setMessage("-- insertRefDataValue Failed - " + e.getMessage() + " --");
			return response;
		}

	}

	@Override
	public ResponseEntity<String> insertRefDataHierarchyValue(RefDataHierarchy refDataHierarchy) {
		try {
			refDataHierarchyRepository.save(refDataHierarchy);	
		}
		catch(Exception e) {
			return new ResponseEntity<>("Value not saved", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Value saved successfully", HttpStatus.OK);
	}
	

}
