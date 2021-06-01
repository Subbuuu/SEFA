package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.InputTableName;

@Repository
public interface InputTableNameRepository extends JpaRepository<InputTableName, Integer> {

	@Query("select i.id from InputTableName i where i.name = :name and i.tableType = :tableType")
	Integer getId(@Param("name") String name, @Param("tableType") String tableType);

}
