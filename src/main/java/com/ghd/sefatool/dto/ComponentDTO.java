package com.ghd.sefatool.dto;

import java.util.List;

public class ComponentDTO {

	Integer componentId;
	
	String component;
	
	List<ComponentPhaseDTO> phases;
	
	public Integer getComponentId() {
		return componentId;
	}
	
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	
	public String getComponent() {
		return component;
	}
	
	public void setComponent(String component) {
		this.component = component;
	}
	
	public List<ComponentPhaseDTO> getPhases() {
		return phases;
	}
	
	public void setPhases(List<ComponentPhaseDTO> phases) {
		this.phases = phases;
	}
	
}
