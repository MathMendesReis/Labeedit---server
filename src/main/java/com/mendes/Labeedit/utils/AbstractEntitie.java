package com.mendes.Labeedit.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntitie {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @UpdateTimestamp
  private LocalDateTime updateDate;
  @CreationTimestamp
  private LocalDateTime createdDate;
  public UUID getId() {
    return id;
  }

  LocalDateTime getUpdateDate(){
    return this.updateDate;
  }
  LocalDateTime getCreatedDate(){
    return this.createdDate;
  }
  void setCreatedDate(LocalDateTime createdDate){
    this.createdDate = createdDate;
  }
  
}
