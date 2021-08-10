package com.ghd.sefatool.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.entity.Formula;
import com.ghd.sefatool.entity.InputTableMeta;
import com.ghd.sefatool.entity.InputTableNameLookup;
import com.ghd.sefatool.entity.LookupFormula;
import com.ghd.sefatool.entity.RefData;
import com.ghd.sefatool.entity.UserInputTableValue;
import com.ghd.sefatool.projection.InputTableColumnsProjection;
import com.ghd.sefatool.repository.FormulaRepository;
import com.ghd.sefatool.repository.InputTableMetaRepository;
import com.ghd.sefatool.repository.InputTableNameLookupRepository;
import com.ghd.sefatool.repository.InputTableNameRepository;
import com.ghd.sefatool.repository.LookupFormulaRepository;
import com.ghd.sefatool.repository.RefDataRepository;
import com.ghd.sefatool.repository.UserInputTableValueRepository;
import com.ghd.sefatool.service.InputTableService;
import com.ghd.sefatool.vo.InputLookupValuesJson;
import com.ghd.sefatool.vo.InputValuesJson;

@Service
public class InputTableServiceImpl implements InputTableService {

	@Autowired
	FormulaRepository formulaRepository;
	
	@Autowired
	LookupFormulaRepository lookupFormulaRepository;
	
	@Autowired
	RefDataRepository refDataRepository;
	
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
		for(int numberOfRecords=0; numberOfRecords<inputValuesJson.getColumnValues().size(); numberOfRecords++) {
			Map<String,String> values = inputValuesJson.getColumnValues().get(numberOfRecords);
			Integer inputTableNameId = findUserInputTableId(inputValuesJson.getInputTableName());
			String rowIdentifier = UUID.randomUUID().toString();
			String siteName = inputValuesJson.getSiteName();
			String remedialOptionName = inputValuesJson.getRemedialOptionName();
			String componentName = inputValuesJson.getComponentName();
			String componentPhase = inputValuesJson.getComponentPhase();
			try {
				for (Map.Entry<String,String> entry : values.entrySet()) {
					UserInputTableValue userInputTableValue = new UserInputTableValue();
					String inputColumnCode = entry.getKey();
					Integer userInputColumnId = findUserInputColumnId(inputColumnCode, inputTableNameId);
					String userInputColumnValue = entry.getValue();
					userInputTableValue.setInputTableNameId(inputTableNameId);
					userInputTableValue.setUserInputColumnId(userInputColumnId);
					userInputTableValue.setUserInputColumnValue(userInputColumnValue);
					userInputTableValue.setRowIdentifier(rowIdentifier);
					userInputTableValue.setSiteName(siteName);
					userInputTableValue.setRemedialOptionName(remedialOptionName);
					userInputTableValue.setComponentName(componentName);
					userInputTableValue.setComponentPhase(componentPhase);
					userInputTableValueRepository.save(userInputTableValue);
				}	
				loadCalculatedValues(inputTableNameId, inputValuesJson, values, rowIdentifier);
			}
			catch(Exception e) {
				return new ResponseEntity<>("Values not saved - " + e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Values saved successfully", HttpStatus.OK);
	}
	
	public Integer findUserInputTableId(String inputTableName) {
		Integer id = inputTableNameRepository.getId(inputTableName,"user_input");
		return id;
	}
	
	public Integer findUserInputColumnId(String inputColumnCode, Integer inputTableNameId) {
		Integer id = inputTableMetaRepository.getColumnId(inputColumnCode, inputTableNameId);
		return id;
	}
	
	public String evaluateFormula(Map<String, String> variables, Integer inputTableColumnId) throws ScriptException {

		ScriptEngine js = new ScriptEngineManager().getEngineByName("javascript");
			
		Formula formula =  formulaRepository.findByInputTableColumnId(inputTableColumnId);
		
		String formulaToBeEvaluated = formula.getFormula();
		String [] variablesUsed = formula.getVariablesUsed().split(",");
		String tempFormula;
		
		for(int i = 0; i < variablesUsed.length; i++) {
			String variablesUsedFor = variablesUsed[i];
			String variableValue = variables.get(variablesUsed[i]);
			if(variables.containsKey(variablesUsedFor)) {
			tempFormula = formulaToBeEvaluated.replace(variablesUsedFor, variableValue);
			formulaToBeEvaluated = tempFormula;
			}
		}
		
		String result = String.valueOf(js.eval(formulaToBeEvaluated));
		return result;
	}
	
	
	public String evaluateLookup(Map<String, String> variables, Integer lookupTableId, Integer inputTableColumnId) {
		
		LookupFormula lookupFormula =  lookupFormulaRepository.findByInputTableColumnId(inputTableColumnId);
		
		String lookupRow = variables.get(lookupFormula.getLookupRow());
		String lookupColumn = new String();
		
		if(variables.get(lookupFormula.getLookupColumn()) != null) {
			lookupColumn = variables.get(lookupFormula.getLookupColumn());
		}
		else {
			lookupColumn = lookupFormula.getLookupColumn();
		}
	
		String result = inputTableNameLookupRepository.findLookupValue(lookupTableId, lookupRow, lookupColumn);
		return result;
	}
	
	
	public void loadCalculatedValues(Integer inputTableNameId, InputValuesJson inputValuesJson, Map<String,String> values, String rowIdentifier) 
			throws ScriptException {
		String siteName = inputValuesJson.getSiteName();
		String remedialOptionName = inputValuesJson.getRemedialOptionName();
		String componentName = inputValuesJson.getComponentName();
		String componentPhase = inputValuesJson.getComponentPhase();
		List<InputTableMeta> calculatedColumns = inputTableMetaRepository.getCalculatedColumns(inputTableNameId);
		List<InputTableMeta> lookupColumns = inputTableMetaRepository.getLookupColumns(inputTableNameId);
		for(int i=0;i<lookupColumns.size();i++) {
			if(!lookupColumns.get(i).getIsCalculated()) {
				UserInputTableValue userInputTableValue = new UserInputTableValue();
				userInputTableValue.setInputTableNameId(inputTableNameId);
				userInputTableValue.setSiteName(siteName);
				userInputTableValue.setRemedialOptionName(remedialOptionName);
				userInputTableValue.setComponentName(componentName);
				userInputTableValue.setComponentPhase(componentPhase);
				userInputTableValue.setRowIdentifier(rowIdentifier);
				String lookupValue = evaluateLookup(values, lookupColumns.get(i).getLookupTableId(), lookupColumns.get(i).getId());
				userInputTableValue.setUserInputColumnId(lookupColumns.get(i).getId());
				userInputTableValue.setUserInputColumnValue(lookupValue);
				userInputTableValueRepository.save(userInputTableValue);
				values.put(lookupColumns.get(i).getInputColumnCode(), lookupValue);
			}
			else {
				String lookupValue = evaluateLookup(values, lookupColumns.get(i).getLookupTableId(), lookupColumns.get(i).getId());
				values.put(lookupColumns.get(i).getInputColumnCode(), lookupValue);
			}
		}
		for(int i=0;i<calculatedColumns.size();i++) {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setSiteName(siteName);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String calculatedValue = evaluateFormula(values, calculatedColumns.get(i).getId());
			userInputTableValue.setUserInputColumnId(calculatedColumns.get(i).getId());
			userInputTableValue.setUserInputColumnValue(calculatedValue);
			userInputTableValueRepository.save(userInputTableValue);
			values.put(calculatedColumns.get(i).getInputColumnCode(), calculatedValue);
		}
	}
	
	@Override 
	public ResponseEntity<String> saveInputLookupValues(InputLookupValuesJson inputLookupValuesJson){
		RefData refData = new RefData();
		Integer inputTableNameId = findUserInputLookupTableId(inputLookupValuesJson.getTableName());
		Map<String, Map<String, String>> entities = inputLookupValuesJson.getEntities();
		
		try {
			for(Map.Entry<String, Map<String, String>> entity : entities.entrySet()) {
				String lookUpRow = entity.getKey();
				Map<String, String> columns = entity.getValue();
				
				refData.setClassCode(inputTableNameRepository.getLookupTableClassCode(inputTableNameId));
				refData.setValue(lookUpRow);
				refDataRepository.save(refData);
				
				
				for(Map.Entry<String, String> lookUpColumns : columns.entrySet()) {
					String lookUpColumnName = lookUpColumns.getKey();
					String lookUpColumnValue = lookUpColumns.getValue();
					InputTableNameLookup inputTableNameLookup = new InputTableNameLookup();
					inputTableNameLookup.setLookupTableNameId(inputTableNameId);
					inputTableNameLookup.setLookupRow(lookUpRow);
					inputTableNameLookup.setLookupColumn(lookUpColumnName);
					inputTableNameLookup.setLookupValue(lookUpColumnValue);
					inputTableNameLookup.setIsUserDefined(true);
					inputTableNameLookupRepository.save(inputTableNameLookup);
				}
				
			}
		}
		catch(Exception e) {
			return new ResponseEntity<>("Values not saved", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Values saved successfully", HttpStatus.OK);
	}
	
	private Integer findUserInputLookupTableId(String tableName) {
		return inputTableNameRepository.getTableId(tableName);
	}

}