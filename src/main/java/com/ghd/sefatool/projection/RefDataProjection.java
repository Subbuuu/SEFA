package com.ghd.sefatool.projection;

public class RefDataProjection {

	Long id;
	String classCode;
	String value;
	Long inputTableColumnId;
	
	public RefDataProjection (Long id, String classCode, String value, Long inputTableColumnId) {
		this.id = id;
		this.classCode = classCode;
		this.value = value;
		this.inputTableColumnId = inputTableColumnId;
	}
}
