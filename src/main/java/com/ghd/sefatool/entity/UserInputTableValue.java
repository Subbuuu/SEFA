package com.ghd.sefatool.entity;

import java.io.Serializable;
import java.util.UUID;

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
public class UserInputTableValue extends BaseEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Integer inputTableNameId;
	
	Integer userInputColumnId;
	
	String userInputColumnValue;
	
	Boolean isUserInputTableValueOverridden;
	
	String UserInputTableValueOverridden;
	
	String rowIdentifier;
	
	String siteName;
	
	String remedialOptionName;
	
	String componentName;
	
	String componentPhase;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInputTableNameId() {
		return inputTableNameId;
	}

	public void setInputTableNameId(Integer inputTableNameId) {
		this.inputTableNameId = inputTableNameId;
	}

	public Integer getUserInputColumnId() {
		return userInputColumnId;
	}

	public void setUserInputColumnId(Integer userInputColumnId) {
		this.userInputColumnId = userInputColumnId;
	}

	public String getUserInputColumnValue() {
		return userInputColumnValue;
	}

	public void setUserInputColumnValue(String userInputColumnValue) {
		this.userInputColumnValue = userInputColumnValue;
	}

	public Boolean getIsUserInputTableValueOverridden() {
		return isUserInputTableValueOverridden;
	}

	public void setIsUserInputTableValueOverridden(Boolean isUserInputTableValueOverridden) {
		this.isUserInputTableValueOverridden = isUserInputTableValueOverridden;
	}

	public String getUserInputTableValueOverridden() {
		return UserInputTableValueOverridden;
	}

	public void setUserInputTableValueOverridden(String userInputTableValueOverridden) {
		UserInputTableValueOverridden = userInputTableValueOverridden;
	}

	public String getRowIdentifier() {
		return rowIdentifier;
	}

	public void setRowIdentifier(String rowIdentifier) {
		this.rowIdentifier = rowIdentifier;
	}

	public String getRemedialOptionName() {
		return remedialOptionName;
	}

	public void setRemedialOptionName(String remedialOptionName) {
		this.remedialOptionName = remedialOptionName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentPhase() {
		return componentPhase;
	}

	public void setComponentPhase(String componentPhase) {
		this.componentPhase = componentPhase;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
