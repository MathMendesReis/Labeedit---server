package com.mendes.Labeedit.modules.reaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterReactionRequestDTO {
  @Schema(example = "0")
  private int value;

  public RegisterReactionRequestDTO(int value) {
    this.value = value;
  }

  public RegisterReactionRequestDTO() {
  }

  public int getValue() {
    return value;
  }

}
