package com.ghd.sefatool.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.RefData;

@Repository
public interface RefDataRepository extends JpaRepository<RefData, Integer> {

	@Query("select r.value from RefData r where r.classCode = :classCode")
	List<String> findValueByClassCode(@Param("classCode") String classCode);

	@Query("select r.code from RefDataHierarchy r where r.parentCode = :parentCode")
	List<String> findCodeByParentCode(@Param("parentCode") String parentCode);
	
	
}
