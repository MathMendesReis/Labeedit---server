package com.mendes.Labeedit.modules.user.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendes.Labeedit.utils.AbstractEntitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_users")
public class AppUser extends AbstractEntitie{
  @Column(nullable = false)
  private String name;
  @Column(unique = true, nullable = false)
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private UserRole role;

  public AppUser(String name,String email,String password){
    this.name = name;
    this.email = email;
    this.password = password;
  }
  public AppUser(){
    
  }

  public String getEmail(){
    return this.email;
  }

  public String getName(){
    return this.name;
  }
  public UserRole getRole(){
    return this.role;
  }
  public String getPassword() {
    return password;
}

  
}
