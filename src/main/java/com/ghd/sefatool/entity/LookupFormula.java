package com.ghd.sefatool.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class LookupFormula implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	Integer id;
	
	String lookupRow;
	
	String lookupColumn;
	
	Integer lookupTableId;
	
	Integer inputTableColumnId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLookupRow() {
		return lookupRow;
	}

	public void setLookupRow(String lookupRow) {
		this.lookupRow = lookupRow;
	}

	public String getLookupColumn() {
		return lookupColumn;
	}

	public void setLookupColumn(String lookupColumn) {
		this.lookupColumn = lookupColumn;
	}

	public Integer getLookupTableId() {
		return lookupTableId;
	}

	public void setLookupTableId(Integer lookupTableId) {
		this.lookupTableId = lookupTableId;
	}

	public Integer getInputTableColumnId() {
		return inputTableColumnId;
	}

	public void setInputTableColumnId(Integer inputTableColumnId) {
		this.inputTableColumnId = inputTableColumnId;
	}

}
