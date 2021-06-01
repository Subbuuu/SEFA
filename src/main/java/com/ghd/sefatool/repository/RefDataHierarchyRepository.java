package com.ghd.sefatool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.entity.RefDataHierarchy;

@Repository
public interface RefDataHierarchyRepository extends JpaRepository<RefDataHierarchy, Integer> {

}
