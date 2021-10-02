package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.RemedialOptionInformation;

@Repository
public interface RemedialOptionInformationRepository extends JpaRepository<RemedialOptionInformation, Integer> {

	@Query("select r from RemedialOptionInformation r where r.siteId = :siteId")
	List<RemedialOptionInformation> getRemedialOptions(@Param("siteId") Integer siteId);
	
}
