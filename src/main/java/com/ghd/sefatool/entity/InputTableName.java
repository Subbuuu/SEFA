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
public class InputTableName extends BaseEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String name;
	
	String input_table_code;
	
	String description;
	
	String tableType;
	
	String classCodeForLookup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInput_table_code() {
		return input_table_code;
	}

	public void setInput_table_code(String input_table_code) {
		this.input_table_code = input_table_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getClassCodeForLookup() {
		return classCodeForLookup;
	}

	public void setClassCodeForLookup(String classCodeForLookup) {
		this.classCodeForLookup = classCodeForLookup;
	}

	
}
