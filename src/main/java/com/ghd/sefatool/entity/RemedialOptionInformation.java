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
public class RemedialOptionInformation extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer remedialOptionId;
	
	String remedialOptionName;
	
	Integer siteId;

	public Integer getRemedialOptionId() {
		return remedialOptionId;
	}

	public void setRemedialOptionId(Integer remedialOptionId) {
		this.remedialOptionId = remedialOptionId;
	}

	public String getRemedialOptionName() {
		return remedialOptionName;
	}

	public void setRemedialOptionName(String remedialOptionName) {
		this.remedialOptionName = remedialOptionName;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
}
