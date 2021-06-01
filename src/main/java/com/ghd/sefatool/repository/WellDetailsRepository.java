package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.WellDetails;

@Repository
public interface WellDetailsRepository extends JpaRepository<WellDetails, Integer> {

	@Query("select w.id from WellDetails w where w.isInUse = 1")
	List<Integer> findIdByActice();

}
