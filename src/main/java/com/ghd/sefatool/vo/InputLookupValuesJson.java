package com.ghd.sefatool.vo;

import java.util.Map;

import lombok.Data;

@Data
public class InputLookupValuesJson {
	String tableName;
	Map<String, Map<String, String>> entities;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, Map<String, String>> getEntities() {
		return entities;
	}
	public void setEntities(Map<String, Map<String, String>> entities) {
		this.entities = entities;
	}
	
	
}