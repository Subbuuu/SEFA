package com.ghd.sefatool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.service.InputTableService;
import com.ghd.sefatool.vo.InputValuesJson;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/inputTables", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class InputTableController {

	@Autowired
	InputTableService inputTableServiceImpl;
	
	@GetMapping("/tableColumns")
	public InputTableColumnsDTO getInputTableColumns(@RequestParam String name, @RequestParam String tableType) {
		InputTableColumnsDTO inputTableColumns = inputTableServiceImpl.getInputTableColumns(name, tableType);
		return inputTableColumns;
	}
	
	@PostMapping("/inputValues")
	public ResponseEntity<String> saveInputTableValues(@RequestBody InputValuesJson inputValuesJson) {
		ResponseEntity<String> status = inputTableServiceImpl.saveInputValues(inputValuesJson);
		return status;
	}
	
}
