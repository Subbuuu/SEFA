package com.ghd.sefatool.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class InputTableNameLookup extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String lookupRow;
	
	String lookupColumn;
	
	String lookupValue;
	
	String lookupUnit;
	
	String defaultValue;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "input_table_name_id", referencedColumnName = "id")
	InputTableName inputTableNameId;
	
	Long inputTableColumnId;
	
	Boolean isUserDefined;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public InputTableName getInputTableNameId() {
		return inputTableNameId;
	}

	public void setInputTableNameId(InputTableName inputTableNameId) {
		this.inputTableNameId = inputTableNameId;
	}

	public Long getInputTableColumnId() {
		return inputTableColumnId;
	}

	public void setInputTableColumnId(Long inputTableColumnId) {
		this.inputTableColumnId = inputTableColumnId;
	}

	public Boolean getIsUserDefined() {
		return isUserDefined;
	}

	public void setIsUserDefined(Boolean isUserDefined) {
		this.isUserDefined = isUserDefined;
	}
	
	
}
