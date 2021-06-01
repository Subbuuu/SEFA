package com.ghd.sefatool.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.entity.UserInputTableValue;
import com.ghd.sefatool.projection.InputTableColumnsProjection;
import com.ghd.sefatool.repository.InputTableMetaRepository;
import com.ghd.sefatool.repository.InputTableNameRepository;
import com.ghd.sefatool.repository.UserInputTableValueRepository;
import com.ghd.sefatool.service.InputTableService;
import com.ghd.sefatool.vo.InputValuesJson;

@Service
public class InputTableServiceImpl implements InputTableService {

	@Autowired
	InputTableNameRepository inputTableNameRepository;
	
	@Autowired
	InputTableMetaRepository inputTableMetaRepository;
	
	@Autowired
	UserInputTableValueRepository userInputTableValueRepository;
	
	@Override
	public InputTableColumnsDTO getInputTableColumns(String name, String tableType) {
		Integer id = inputTableNameRepository.getId(name, tableType);
		List<InputTableColumnsProjection> inputTableColumns = inputTableMetaRepository.findAllByInputTableNameId(id);
		return new InputTableColumnsDTO(inputTableColumns);
	}

	@Override
	public ResponseEntity<String> saveInputValues(InputValuesJson inputValuesJson) {
		Map<String,String> values = inputValuesJson.getColumnValues();
		Integer inputTableNameId = findUserInputTableId(inputValuesJson.getInputTableName());
		String rowIdentifier = UUID.randomUUID().toString();
		String remedialOptionName = inputValuesJson.getRemedialOptionName();
		String componentName = inputValuesJson.getComponentName();
		String componentPhase = inputValuesJson.getComponentPhase();
		try {
			for (Map.Entry<String,String> entry : values.entrySet()) {
				UserInputTableValue userInputTableValue = new UserInputTableValue();
				String inputColumnName = entry.getKey();
				Integer userInputColumnId = findUserInputColumnId(inputColumnName, inputTableNameId);
				String userInputColumnValue = entry.getValue();
				userInputTableValue.setInputTableNameId(inputTableNameId);
				userInputTableValue.setUserInputColumnId(userInputColumnId);
				userInputTableValue.setUserInputColumnValue(userInputColumnValue);
				userInputTableValue.setRowIdentifier(rowIdentifier);
				userInputTableValue.setRemedialOptionName(remedialOptionName);
				userInputTableValue.setComponentName(componentName);
				userInputTableValue.setComponentPhase(componentPhase);
				userInputTableValueRepository.save(userInputTableValue);
			}	
		}
		catch(Exception e) {
			return new ResponseEntity<>("Values not saved", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Values saved successfully", HttpStatus.OK);
	}
	
	public Integer findUserInputTableId(String inputTableName) {
		Integer id = inputTableNameRepository.getId(inputTableName,"user_input");
		return id;
	}
	
	public Integer findUserInputColumnId(String inputColumnName, Integer inputTableNameId) {
		Integer id = inputTableMetaRepository.getColumnId(inputColumnName, inputTableNameId);
		return id;
	}

}
