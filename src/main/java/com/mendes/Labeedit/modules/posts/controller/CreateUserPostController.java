package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.posts.dto.request.RegisterPostRequestDTO;
import com.mendes.Labeedit.modules.posts.useCases.CreateUserPostUseCase;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.ApiRoutes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class CreateUserPostController {
  @Autowired
  private CreateUserPostUseCase createUserPostUseCase;

  @PostMapping()
  @Operation(summary = "Cadastro de postagens", description = "Essa função é responsável por cadastrar uma postagem")
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = AppUser.class))
      })
  })
  @Tag(name = "Posts", description = "Registro de novo post")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<?> handle(@Valid @RequestBody RegisterPostRequestDTO registerPostRequestDTO,HttpServletRequest request) {
    try {
        var userId = request.getAttribute("app_user_id").toString();
        this.createUserPostUseCase.execute(userId, registerPostRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
  }

}
