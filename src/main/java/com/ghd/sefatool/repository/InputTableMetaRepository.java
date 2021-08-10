package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.InputTableMeta;
import com.ghd.sefatool.projection.InputTableColumnsProjection;

@Repository
public interface InputTableMetaRepository extends JpaRepository<InputTableMeta, Integer> {

	@Query("select i.inputColumnName, i.inputColumnType from InputTableMeta i where i.inputTableNameId = :id")
	List<String> getColumnName(@Param("id") Integer id);

	List<InputTableColumnsProjection> findAllByInputTableNameId(Integer id);
	
	@Query("select i.id from InputTableMeta i where i.inputColumnCode = :inputColumnCode and i.inputTableNameId = :inputTableNameId")
	Integer getColumnId(@Param("inputColumnCode") String inputColumnCode, @Param("inputTableNameId") Integer inputTableNameId);

	@Query("select i from InputTableMeta i where i.inputTableNameId = :id and i.isCalculated = 1")
	List<InputTableMeta> getCalculatedColumns(@Param("id") Integer id);

	@Query("select i from InputTableMeta i where i.inputTableNameId = :id and i.isLookup = 1")
	List<InputTableMeta> getLookupColumns(@Param("id") Integer id);

}
