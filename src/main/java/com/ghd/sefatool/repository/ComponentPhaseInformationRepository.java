package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.ComponentPhaseInformation;

@Repository
public interface ComponentPhaseInformationRepository extends JpaRepository<ComponentPhaseInformation, Integer> {

	@Query("select c from ComponentPhaseInformation c where c.componentId = :componentId")
	List<ComponentPhaseInformation> getComponentPhases(@Param("componentId") Integer componentId);

}
