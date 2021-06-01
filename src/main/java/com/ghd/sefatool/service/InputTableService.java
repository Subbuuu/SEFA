package com.ghd.sefatool.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.vo.InputValuesJson;

public interface InputTableService {

	InputTableColumnsDTO getInputTableColumns(String name, String tableType);

	ResponseEntity<String> saveInputValues(InputValuesJson inputValuesJson);

}
