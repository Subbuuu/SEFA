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

	
}
