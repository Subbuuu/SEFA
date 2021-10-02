package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.Formula;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Integer> {

	public Formula findByInputTableColumnId(Integer inputTableColumnId);
}
