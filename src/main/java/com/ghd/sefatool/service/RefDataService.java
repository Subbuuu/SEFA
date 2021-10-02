package com.ghd.sefatool.service;

import org.springframework.http.ResponseEntity;

import com.ghd.sefatool.dto.RefDataDTO;
import com.ghd.sefatool.dto.RefDataHierarchyDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.entity.RefData;
import com.ghd.sefatool.entity.RefDataHierarchy;

public interface RefDataService {

	public ResponseDTO<RefDataDTO> getRefData(String classCode);

	public RefDataHierarchyDTO getRefDataHierarchy(String parentCode);

	public ResponseDTO<ResponseEntity<String>> insertRefDataValue(RefData refData);

	public ResponseEntity<String> insertRefDataHierarchyValue(RefDataHierarchy refDataHierarchy);
	
}
