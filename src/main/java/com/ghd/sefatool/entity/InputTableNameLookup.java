package com.ghd.sefatool.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class InputTableNameLookup extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String lookupRow;
	
	String lookupColumn;
	
	String lookupValue;
	
	String lookupUnit;
	
	String defaultValue;
	
	Integer lookupTableNameId;
	
	Boolean isUserDefined;

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

	public String getLookupValue() {
		return lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public String getLookupUnit() {
		return lookupUnit;
	}

	public void setLookupUnit(String lookupUnit) {
		this.lookupUnit = lookupUnit;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getLookupTableNameId() {
		return lookupTableNameId;
	}

	public void setLookupTableNameId(Integer lookupTableNameId) {
		this.lookupTableNameId = lookupTableNameId;
	}

	public Boolean getIsUserDefined() {
		return isUserDefined;
	}

	public void setIsUserDefined(Boolean isUserDefined) {
		this.isUserDefined = isUserDefined;
	}

	
	
}
