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
public class ComponentInformation extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer componentId;
	
	String componentName;
	
	Integer remedialOptionId;

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Integer getRemedialOptionId() {
		return remedialOptionId;
	}

	public void setRemedialOptionId(Integer remedialOptionId) {
		this.remedialOptionId = remedialOptionId;
	}
	
}
