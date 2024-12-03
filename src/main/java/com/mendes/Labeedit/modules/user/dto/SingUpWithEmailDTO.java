package com.mendes.Labeedit.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class SingUpWithEmailDTO {
  @Schema(example = "example@gmail.com", requiredMode = RequiredMode.REQUIRED)
  private String email;

  @Schema(example = "123456", requiredMode = RequiredMode.REQUIRED)
  private String password;

  public String getPassword() {
    return password;
  }
  public String getEmail() {
    return email;
  }
  public SingUpWithEmailDTO(){}
  public SingUpWithEmailDTO(String email, String password){
    this.email = email;
    this.password = password;
  }
}
