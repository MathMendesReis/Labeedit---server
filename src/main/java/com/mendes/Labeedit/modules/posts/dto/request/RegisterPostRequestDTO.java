package com.mendes.Labeedit.modules.posts.dto.request;

public class RegisterPostRequestDTO {
  private String content;

  public RegisterPostRequestDTO(){}
  public RegisterPostRequestDTO(String content){
    this.content = content;
  }

  public String getContent(){
    return this.content;
  }
}
