package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.LookupFormula;

@Repository
public interface LookupFormulaRepository extends JpaRepository<LookupFormula, Integer> {
	
	public LookupFormula findByInputTableColumnId(Integer inputTableColumnId);

}
