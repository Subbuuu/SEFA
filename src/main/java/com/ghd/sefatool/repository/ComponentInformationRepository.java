package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.ComponentInformation;

@Repository
public interface ComponentInformationRepository extends JpaRepository<ComponentInformation, Integer> {

	@Query("select r from ComponentInformation r where r.remedialOptionId = :remedialOptionId")
	List<ComponentInformation> getComponents(@Param("remedialOptionId") Integer remedialOptionId);
	
}
