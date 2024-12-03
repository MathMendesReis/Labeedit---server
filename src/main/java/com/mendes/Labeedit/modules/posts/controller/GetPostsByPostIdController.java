package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.posts.dto.response.FindAllPostDTOResponse;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.ApiRoutes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.media.ArraySchema;



@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class GetPostsByPostIdController {
  @Autowired
  private PostRepositorie postRepositorie;
  @GetMapping("/{post-id}")

  @Operation(summary = "Listagem de uma unico post", description = "Essa função é responsável por listar um determinado post.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = FindAllPostDTOResponse.class))
      }),
     
  })
  @Tag(name = "Posts", description = "Listagem de uma unico post")
  public ResponseEntity<?> handle(@PathVariable("post-id") UUID postId) {
      try {
        var result = this.postRepositorie.findById(postId);
        if (result.isEmpty()) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
      }
        FindAllPostDTOResponse response = new FindAllPostDTOResponse(result.get());
        return new ResponseEntity<>(response,HttpStatus.OK);

      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
  }
 
  

  
  
}
