package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghd.sefatool.entity.Formula;

public interface FormulaRepository  extends JpaRepository<Formula, Integer>{
	public Formula findByFormulaName(String formulaName);
}
