package com.example.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditEntity {

	@Column(name = "create_time", updatable = false)
	@CreatedDate
	private long createTime;

	@Column(name = "update_time")
	@LastModifiedDate
	private long updateTime;

	@Column(name = "create_by", updatable = false)
	@CreatedBy
	private String createBy;

	@Column(name = "last_modified_by")
	@LastModifiedBy
	private String lastModifiedBy;
}
