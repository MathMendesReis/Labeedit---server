package com.mendes.Labeedit.modules.user.dto;

public class SingUpWithEmailDTO {
  private String email;
  public String getEmail() {
    return email;
  }
  private String password;

  public String getPassword() {
    return password;
  }
  public SingUpWithEmailDTO(){}
  public SingUpWithEmailDTO(String email, String password){
    this.email = email;
    this.password = password;
  }
}
