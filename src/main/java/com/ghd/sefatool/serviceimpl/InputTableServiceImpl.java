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
import com.ghd.sefatool.repository.InputTableNameLookupRepository;
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
	InputTableNameLookupRepository inputTableNameLookupRepository; 
	
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
			loadCalculatedValues(inputTableNameId, inputValuesJson, rowIdentifier);
		}
		catch(Exception e) {
			return new ResponseEntity<>("Values not saved - " + e.getMessage(), HttpStatus.BAD_REQUEST);
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
	
	public void loadCalculatedValues(Integer inputTableNameId, InputValuesJson inputValuesJson, String rowIdentifier) {
		Map<String,String> values = inputValuesJson.getColumnValues();
		String remedialOptionName = inputValuesJson.getRemedialOptionName();
		String componentName = inputValuesJson.getComponentName();
		String componentPhase = inputValuesJson.getComponentPhase();
		switch(inputTableNameId) {
		case 1: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String userInputColumnValue =  String.valueOf(Float.parseFloat(values.get("Number of Roundtrips to Site"))*
					Float.parseFloat(values.get("Roundtrip Distance to Site")));
			userInputTableValue.setUserInputColumnId(6);
			userInputTableValue.setUserInputColumnValue(userInputColumnValue);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			userInputTableValue.setUserInputColumnId(7);
			String lookupRow = values.get("Mode of Transportation");
			String lookupColumn = values.get("Transport Fuel Type");
			String lookupValue = inputTableNameLookupRepository.findLookupValue(2, lookupRow, lookupColumn);
			userInputTableValue.setUserInputColumnValue(lookupValue);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			userInputTableValue.setUserInputColumnId(8);
			String userInputColumnValueFuel = String.format("%.1f",Float.parseFloat(userInputColumnValue)/Float.parseFloat(lookupValue));
			userInputTableValue.setUserInputColumnValue(userInputColumnValueFuel);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 17: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float powerRating = Float.parseFloat(values.get("Power Rating"));
			Float efficiency = Float.parseFloat(values.get("Efficiency"))/100;
			Float hoursUsed = Float.parseFloat(values.get("Hours Used"));	
			String userInputColumnValueEnergy = String.valueOf(powerRating/efficiency*hoursUsed);
			String userInputColumnValueNaturalGasUsed = String.valueOf((powerRating/efficiency*hoursUsed)/103000);
			userInputTableValue.setUserInputColumnId(110);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueEnergy);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			userInputTableValue.setUserInputColumnId(111);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueNaturalGasUsed);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 18: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float landfillGas = Float.parseFloat(values.get("Landfill Gas"));
			Float percentageMethaneValue = Float.parseFloat(values.get("% Methane by volume"))/100;
			String userInputColumnValue = String.valueOf(landfillGas*percentageMethaneValue);
			userInputTableValue.setUserInputColumnId(117);
			userInputTableValue.setUserInputColumnValue(userInputColumnValue);
			userInputTableValueRepository.save(userInputTableValue);
		}
		}
	}

}
