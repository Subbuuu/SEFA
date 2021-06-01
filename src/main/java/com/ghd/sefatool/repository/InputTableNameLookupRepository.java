package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.InputTableNameLookup;

@Repository
public interface InputTableNameLookupRepository extends JpaRepository<InputTableNameLookup, Integer> {

	@Query("select l.lookupValue from InputTableNameLookup l where l.lookupRow = :lookupRow and l.lookupColumn = :lookupColumn and l.lookupTableNameId = :lookupTableNameId")
	String findLookupValue(@Param("lookupTableNameId") Integer lookupTableNameId, @Param("lookupRow") String lookupRow, @Param("lookupColumn") String lookupColumn);
	
}
