package com.challenge.simpleApi.domains.users.security;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit implements Serializable {
  
  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date created_at;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Date updated_at;
  
}
