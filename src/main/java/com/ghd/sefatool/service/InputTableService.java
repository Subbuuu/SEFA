package com.ghd.sefatool.service;

import org.springframework.http.ResponseEntity;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.vo.InputLookupValuesJson;
import com.ghd.sefatool.vo.InputValuesJson;

public interface InputTableService {

	InputTableColumnsDTO getInputTableColumns(String name, String tableType);

	ResponseEntity<String> saveInputValues(InputValuesJson inputValuesJson);
	
	ResponseEntity<String> saveInputLookupValues(InputLookupValuesJson inputLookupValuesJson);

}
