package com.mendes.Labeedit.modules.user.dto;

public class SingInDTO {
  String email;
  String password;

  public SingInDTO(String email,String password){
    this.email = email;
    this.password = password;
  }

  public SingInDTO(){}


  public String getEmail(){
    return this.email;
  }
  public String getPassword(){
    return this.password;
  }
}

