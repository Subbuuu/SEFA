package com.ghd.sefatool.vo;

import java.util.Map;

import lombok.Data;

@Data
public class InputLookupValuesJson {
	String tableName;
	Map<String, Map<String, String>> entities;
}