package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.InputTableName;

@Repository
public interface InputTableNameRepository extends JpaRepository<InputTableName, Integer> {

	@Query("select i.id from InputTableName i where i.name = :name and i.tableType = :tableType")
	Integer getId(@Param("name") String name, @Param("tableType") String tableType);

	@Query("SELECT i.classCodeForLookup from InputTableName i where i.id = :tableID")
	String getLookupTableClassCode(@Param("tableID") Integer id);
	
	@Query("select i.id from InputTableName i where i.name = :name")
	Integer getTableId(@Param("name") String name);
	
	@Query("select i.id from InputTableName i where i.tableType = :tableType")
	List<Integer> getIdByTableType(@Param("tableType") String tableType);
	
	@Query("select i.name from InputTableName i where i.id = :id")
	String getNameById(@Param("id") Integer id);
	
}
