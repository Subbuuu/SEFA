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
public class InputTableMeta extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String inputColumnName;
	
	String inputColumnType;
	
	Integer inputTableNameId;
	
	String inputColumnUnit;
	
	Boolean isLookup;
	
	Boolean isCalculated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInputColumnName() {
		return inputColumnName;
	}

	public void setInputColumnName(String inputColumnName) {
		this.inputColumnName = inputColumnName;
	}

	public String getInputColumnType() {
		return inputColumnType;
	}

	public void setInputColumnType(String inputColumnType) {
		this.inputColumnType = inputColumnType;
	}

	public Integer getInputTableNameId() {
		return inputTableNameId;
	}

	public void setInputTableNameId(Integer inputTableNameId) {
		this.inputTableNameId = inputTableNameId;
	}

	public String getInputColumnUnit() {
		return inputColumnUnit;
	}

	public void setInputColumnUnit(String inputColumnUnit) {
		this.inputColumnUnit = inputColumnUnit;
	}

	public Boolean getIsLookup() {
		return isLookup;
	}

	public void setIsLookup(Boolean isLookup) {
		this.isLookup = isLookup;
	}

	public Boolean getIsCalculated() {
		return isCalculated;
	}

	public void setIsCalculated(Boolean isCalculated) {
		this.isCalculated = isCalculated;
	}
	
	
}
