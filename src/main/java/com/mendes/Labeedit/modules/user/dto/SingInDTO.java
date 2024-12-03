package com.mendes.Labeedit.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class SingInDTO {
  @Schema(example = "example", requiredMode = RequiredMode.REQUIRED)
  private String name;

  @Schema(example = "example@gmail.com", requiredMode = RequiredMode.REQUIRED)
  private String email;
  @Schema(example = "123456", requiredMode = RequiredMode.REQUIRED)
  private String password;

  public SingInDTO(String email,String password, String name){
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public SingInDTO(){}


  public String getEmail(){
    return this.email;
  }
  public  String getPassword(){
    return this.password;
  }
  public String getName() {
    return name;
  }
}


