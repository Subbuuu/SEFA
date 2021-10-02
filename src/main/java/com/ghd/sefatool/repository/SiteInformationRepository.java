package com.ghd.sefatool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ghd.sefatool.dto.SiteNameDTO;
import com.ghd.sefatool.entity.SiteInformation;

@Repository
public interface SiteInformationRepository extends JpaRepository<SiteInformation, Integer> {

	@Query("select NEW com.ghd.sefatool.dto.SiteNameDTO(s.siteId, s.siteName) from SiteInformation s")
	List<SiteNameDTO> getSiteData();
}
