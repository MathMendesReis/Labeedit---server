package com.mendes.Labeedit.modules.posts.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterPostRequestDTO {
  @Schema(example = "Tô tentando lembrar sem sucesso de um finalista de libertadores pior que esse time do atlético, se alguém lembrar é só por nos comentários....")
  private String content;

  public RegisterPostRequestDTO(){}
  public RegisterPostRequestDTO(String content){
    this.content = content;
  }

  public String getContent(){
    return this.content;
  }
}
