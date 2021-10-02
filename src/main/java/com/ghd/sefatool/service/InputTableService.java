package com.ghd.sefatool.service;

import org.springframework.http.ResponseEntity;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.dto.ResponseDTO;
import com.ghd.sefatool.vo.InputLookupValuesJson;
import com.ghd.sefatool.vo.InputValuesJson;

public interface InputTableService {

	ResponseDTO<InputTableColumnsDTO> getInputTableColumns(String name, String tableType);

	ResponseDTO<ResponseEntity<String>> saveInputValues(InputValuesJson inputValuesJson);
	
	ResponseDTO<ResponseEntity<String>> saveInputLookupValues(InputLookupValuesJson inputLookupValuesJson);

}
