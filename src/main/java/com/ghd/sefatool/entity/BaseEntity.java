package com.ghd.sefatool.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@MappedSuperclass
@ToString
@Data
public class BaseEntity {

	/** The createdDate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	protected Date createdDate;

	/** The createdById. */
	protected Integer createdById;

	/** The modifiedById. */
	protected Integer modifiedById;

	/** The modifiedDate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	protected Date modifiedDate;
	
	@PrePersist
	protected void onPrePersist() {
		this.setCreatedDate(new Date());
	}
	
	@PreUpdate
	protected void onPreUpdate() {
		this.setModifiedDate(new Date());
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(Integer modifiedById) {
		this.modifiedById = modifiedById;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
