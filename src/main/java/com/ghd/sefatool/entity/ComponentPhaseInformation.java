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
public class ComponentPhaseInformation extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer componentPhaseId;
	
	String componentPhaseName;
	
	Integer componentId;

	public Integer getComponentPhaseId() {
		return componentPhaseId;
	}

	public void setComponentPhaseId(Integer componentPhaseId) {
		this.componentPhaseId = componentPhaseId;
	}

	public String getComponentPhaseName() {
		return componentPhaseName;
	}

	public void setComponentPhaseName(String componentPhaseName) {
		this.componentPhaseName = componentPhaseName;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	
}
