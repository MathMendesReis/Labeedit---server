package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.posts.dto.response.FindAllPostDTOResponse;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.ApiRoutes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.*;

@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class GetPostsByUserIdController {
    @Autowired
  private PostRepositorie postRepositorie;

  @GetMapping("/findAllByUserid/{user-id}")
  @Operation(summary = "Listagens de posts de um usuario", description = "Essa função é responsável por listar todos os posts de um determinado usuario.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = FindAllPostDTOResponse.class)))

      }),
     
  })
  @Tag(name = "Posts", description = "Listagens de posts de um usuario")
  public ResponseEntity<?> handle(@PathVariable("user-id") UUID userId) {
      try {
        List<PostEntitie> result = this.postRepositorie.findByUserId(userId);
        List<FindAllPostDTOResponse> response = result.stream()
        .map(FindAllPostDTOResponse::new) 
        .toList();
        return new ResponseEntity<>(response,HttpStatus.OK);

      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
  }
}
