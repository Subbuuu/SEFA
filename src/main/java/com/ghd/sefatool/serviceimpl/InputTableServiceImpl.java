package com.ghd.sefatool.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghd.sefatool.dto.InputTableColumnsDTO;
import com.ghd.sefatool.entity.InputTableNameLookup;
import com.ghd.sefatool.entity.RefData;
import com.ghd.sefatool.entity.UserInputTableValue;
import com.ghd.sefatool.projection.InputTableColumnsProjection;
import com.ghd.sefatool.repository.InputTableMetaRepository;
import com.ghd.sefatool.repository.InputTableNameLookupRepository;
import com.ghd.sefatool.repository.InputTableNameRepository;
import com.ghd.sefatool.repository.RefDataRepository;
import com.ghd.sefatool.repository.UserInputTableValueRepository;
import com.ghd.sefatool.service.InputTableService;
import com.ghd.sefatool.vo.InputLookupValuesJson;
import com.ghd.sefatool.vo.InputValuesJson;

@Service
public class InputTableServiceImpl implements InputTableService {

	
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
		} break;
		case 5: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float hp = Float.parseFloat(values.get("HP"));
			Float loadFactor = Float.parseFloat(values.get("Load Factor"))/100;
			Float efficiency = Float.parseFloat(values.get("Efficiency"))/100;
			Float hoursUsed = Float.parseFloat(values.get("Hours Used"));
			String userInputColumnValueElectricalRating = String.valueOf(hp*loadFactor/efficiency*0.746);
			String userInputColumnValueEnergyUsed = String.valueOf((hp*loadFactor/efficiency*0.746)*hoursUsed);
			userInputTableValue.setUserInputColumnId(41);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueElectricalRating);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			userInputTableValue.setUserInputColumnId(43);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueEnergyUsed);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 3: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float hp = Float.parseFloat(values.get("HP"));
			Float loadFactor = Float.parseFloat(values.get("Load Factor"))/100;
			String equipmentFuelType = values.get("Equipment Fuel Type");
			Float equipmentHoursOperated = Float.parseFloat(values.get("Equipment Hours Operated"));
			Float numberOfRoundtrips = Float.parseFloat(values.get("Number of Equipment Roundtrips to Site"));
			Float roundtripDistance = Float.parseFloat(values.get("Roundtrip Distance to Site"));
			Float equipmentWeight = Float.parseFloat(values.get("Equipment weight"));
			String modeOfTransportataion = values.get("Mode of Transportation");
			String transportFuelType = values.get("Transport Fuel Type");
			String lookupValueEquipmentFuelUsageRate = inputTableNameLookupRepository.findLookupValue(13, equipmentFuelType, "Units per HP-hr");
			String userInputColumnValueEquipmentFuelUsageRate = String.valueOf(Float.parseFloat(lookupValueEquipmentFuelUsageRate)*hp*loadFactor);
			userInputTableValue.setUserInputColumnId(14);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueEquipmentFuelUsageRate);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String userInputColumnValueEquipmentFuelUsed = String.valueOf((Float.parseFloat(userInputColumnValueEquipmentFuelUsageRate)*equipmentHoursOperated));
			userInputTableValue.setUserInputColumnId(16);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueEquipmentFuelUsed);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String userInputColumnValueTotalDistance =  String.valueOf(numberOfRoundtrips*roundtripDistance);
			userInputTableValue.setUserInputColumnId(20);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueTotalDistance);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String lookupValueTransportFuel = inputTableNameLookupRepository.findLookupValue(4, modeOfTransportataion, transportFuelType);
			userInputTableValue.setUserInputColumnId(23);
			userInputTableValue.setUserInputColumnValue(lookupValueTransportFuel);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String conditionCheck = modeOfTransportataion.substring(modeOfTransportataion.length() - 6);
			Float fuelUsedForEquipmentTransport = (float) 0;
			if (conditionCheck == "(gptm)") {
				fuelUsedForEquipmentTransport = (equipmentWeight*Float.parseFloat(lookupValueTransportFuel)*
						Float.parseFloat(userInputColumnValueTotalDistance));
			}
			else {
				fuelUsedForEquipmentTransport = Float.parseFloat(userInputColumnValueTotalDistance)/
						Float.parseFloat(lookupValueTransportFuel);
			}	
			String userInputColumnValueFuelUsedForEquipmentTransport = String.format("%.1f",fuelUsedForEquipmentTransport);
			userInputTableValue.setUserInputColumnId(24);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueFuelUsedForEquipmentTransport);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 10: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String sourceOfWater = values.get("Source of Water Used");
			Float quantity = Float.parseFloat(values.get("Quantity"));
			String lookupValueUnit = inputTableNameLookupRepository.findLookupValue(11, sourceOfWater, "Units");
			Float lookupValueTons = Float.parseFloat(inputTableNameLookupRepository.findLookupValue(11, sourceOfWater, "Conv to tons"));
			userInputTableValue.setUserInputColumnId(85);
			userInputTableValue.setUserInputColumnValue(lookupValueUnit);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			userInputTableValue.setUserInputColumnId(87);
			String userInputColumnValueTons = String.valueOf(quantity*lookupValueTons);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueTons);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 6: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float quantity = Float.parseFloat(values.get("Quantity"));
			Integer numberOfOneWayTrips = Integer.parseInt(values.get("Number of One-way Trips to Site"));
			String materialType = values.get("Material Type");
			String includeReturnTrip = values.get("Include Return Trip in Calculations?");
			String modeOfTransportataion = values.get("Mode of Transportation");
			String transportFuelType = values.get("Transport Fuel Type");
			String lookupValueUnit = inputTableNameLookupRepository.findLookupValue(7, materialType, "Units");
			userInputTableValue.setUserInputColumnId(50);
			userInputTableValue.setUserInputColumnValue(lookupValueUnit);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float lookupValueTons = Float.parseFloat(inputTableNameLookupRepository.findLookupValue(7, materialType, "Conv to tons"));
			String userInputColumnValueTons = String.valueOf((lookupValueTons*quantity));
			userInputTableValue.setUserInputColumnId(52);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueTons);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String lookupValueDefaultDistance =  inputTableNameLookupRepository.findLookupValue(7, materialType, "Default distance");
			userInputTableValue.setUserInputColumnId(56);
			userInputTableValue.setUserInputColumnValue(lookupValueDefaultDistance);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float totalDistance = (float) 0;
			if (includeReturnTrip.equals("Yes")) {
				if (numberOfOneWayTrips == 0) {
					totalDistance = (float) (0.5*2*Float.parseFloat(lookupValueDefaultDistance));
				}
				else {
					totalDistance = numberOfOneWayTrips*2*Float.parseFloat(lookupValueDefaultDistance);
				}
			}
			else {
				if (numberOfOneWayTrips == 0) {
					totalDistance = Float.parseFloat(lookupValueDefaultDistance);
				}
				else {
					totalDistance = numberOfOneWayTrips*Float.parseFloat(lookupValueDefaultDistance);
				}
			}
			String userInputValueTotalDistance = String.valueOf(totalDistance);
			userInputTableValue.setUserInputColumnId(59);
			userInputTableValue.setUserInputColumnValue(userInputValueTotalDistance);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String lookupValueTransportFuel = inputTableNameLookupRepository.findLookupValue(4, modeOfTransportataion, transportFuelType);
			userInputTableValue.setUserInputColumnId(62);
			userInputTableValue.setUserInputColumnValue(lookupValueTransportFuel);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String conditionCheck = modeOfTransportataion.substring(modeOfTransportataion.length() - 6);
			Float fuelUsedForMaterialsTransport = (float) 0;
			if (conditionCheck.equals("(gptm)")) {
				fuelUsedForMaterialsTransport = Float.parseFloat(userInputColumnValueTons)*Float.parseFloat(lookupValueTransportFuel)*
						Float.parseFloat(lookupValueDefaultDistance);
			}
			else {
				fuelUsedForMaterialsTransport = totalDistance/Float.parseFloat(lookupValueTransportFuel); 
			}
			String userInputColumnValueFuelUsedForMaterialsTransport = String.format("%.3f",fuelUsedForMaterialsTransport);
			userInputTableValue.setUserInputColumnId(63);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueFuelUsedForMaterialsTransport);
			userInputTableValueRepository.save(userInputTableValue);
		} break;
		case 8: {
			UserInputTableValue userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float quantity = Float.parseFloat(values.get("Quantity"));
			Integer numberOfOneWayTrips = Integer.parseInt(values.get("Number of One-way Trips to Site"));
			String wasteDestination = values.get("Waste Destination");
			String includeReturnTrip = values.get("Include Return Trip in Calculations?");
			String modeOfTransportataion = values.get("Mode of Transportation");
			String transportFuelType = values.get("Transport Fuel Type");
			String lookupValueUnit = inputTableNameLookupRepository.findLookupValue(9, wasteDestination, "Units");
			userInputTableValue.setUserInputColumnId(69);
			userInputTableValue.setUserInputColumnValue(lookupValueUnit);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float lookupValueTons = Float.parseFloat(inputTableNameLookupRepository.findLookupValue(9, wasteDestination, "Conv to tons"));
			String userInputColumnValueTons = String.valueOf((lookupValueTons*quantity));
			userInputTableValue.setUserInputColumnId(71);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueTons);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String lookupValueDefaultDistance =  inputTableNameLookupRepository.findLookupValue(9, wasteDestination, "Default distance");
			userInputTableValue.setUserInputColumnId(72);
			userInputTableValue.setUserInputColumnValue(lookupValueDefaultDistance);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			Float totalDistance = (float) 0;
			if (includeReturnTrip.equals("Yes")) {
				if (numberOfOneWayTrips == 0) {
					totalDistance = (float) (0.5*2*Float.parseFloat(lookupValueDefaultDistance));
				}
				else {
					totalDistance = numberOfOneWayTrips*2*Float.parseFloat(lookupValueDefaultDistance);
				}
			}
			else {
				if (numberOfOneWayTrips == 0) {
					totalDistance = Float.parseFloat(lookupValueDefaultDistance);
				}
				else {
					totalDistance = numberOfOneWayTrips*Float.parseFloat(lookupValueDefaultDistance);
				}
			}
			String userInputValueTotalDistance = String.valueOf(totalDistance);
			userInputTableValue.setUserInputColumnId(75);
			userInputTableValue.setUserInputColumnValue(userInputValueTotalDistance);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String lookupValueTransportFuel = inputTableNameLookupRepository.findLookupValue(4, modeOfTransportataion, transportFuelType);
			userInputTableValue.setUserInputColumnId(78);
			userInputTableValue.setUserInputColumnValue(lookupValueTransportFuel);
			userInputTableValueRepository.save(userInputTableValue);
			userInputTableValue = new UserInputTableValue();
			userInputTableValue.setInputTableNameId(inputTableNameId);
			userInputTableValue.setRemedialOptionName(remedialOptionName);
			userInputTableValue.setComponentName(componentName);
			userInputTableValue.setComponentPhase(componentPhase);
			userInputTableValue.setRowIdentifier(rowIdentifier);
			String conditionCheck = modeOfTransportataion.substring(modeOfTransportataion.length() - 6);
			Float fuelUsedForWasteTransport = (float) 0;
			if (conditionCheck.equals("(gptm)")) {
				fuelUsedForWasteTransport = Float.parseFloat(userInputColumnValueTons)*Float.parseFloat(lookupValueTransportFuel)*
						Float.parseFloat(lookupValueDefaultDistance);
			}
			else {
				fuelUsedForWasteTransport = totalDistance/Float.parseFloat(lookupValueTransportFuel); 
			}
			String userInputColumnValueFuelUsedForWasteTransport = String.format("%.1f",fuelUsedForWasteTransport);
			userInputTableValue.setUserInputColumnId(79);
			userInputTableValue.setUserInputColumnValue(userInputColumnValueFuelUsedForWasteTransport);
			userInputTableValueRepository.save(userInputTableValue);
		}
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