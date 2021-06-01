package com.ghd.sefatool.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.RefDataDTO;
import com.ghd.sefatool.dto.RefDataHierarchyDTO;
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
	public RefDataDTO getRefData(String classCode) {
		List<String> refData = refDataRepository.findValueByClassCode(classCode);
		return new RefDataDTO(refData);
	}

	@Override
	public RefDataHierarchyDTO getRefDataHierarchy(String parentCode) {
		List<String> refDataHierarchy = refDataRepository.findCodeByParentCode(parentCode);
		return new RefDataHierarchyDTO(refDataHierarchy);
	}

	@Override
	public ResponseEntity<String> insertRefDataValue(RefData refData) {
		try {
			refDataRepository.save(refData);	
		}
		catch(Exception e) {
			return new ResponseEntity<>("Value not saved", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Value saved successfully", HttpStatus.OK);
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
