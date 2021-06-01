package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.WellMaterialsRequired;
import com.ghd.sefatool.projection.WellMaterialProjection;

@Repository
public interface WellMaterialsRequiredRepository extends JpaRepository<WellMaterialsRequired, Integer> {

	List<WellMaterialProjection> findAllByWellDetailsIdIn(List<Integer> wellDetailsId);

}
