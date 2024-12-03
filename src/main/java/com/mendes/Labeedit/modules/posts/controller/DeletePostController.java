package com.mendes.Labeedit.modules.posts.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class DeletePostController {
  @Autowired
  private PostRepositorie postRepositorie;
  @DeleteMapping("/{id}")
  @Operation(summary = "Deleção de uma postagem", description = "Essa função é responsável por deletar uma postagem")
  @ApiResponses({
      @ApiResponse(responseCode = "204"),
  })
  @Tag(name = "Posts", description = "Deletar um post")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<?> execute(@PathVariable("id") UUID postId,HttpServletRequest request ){
    try {
      UUID userId = UUID.fromString((String) request.getAttribute("app_user_id"));
      PostEntitie post =  this.postRepositorie.findById(postId).orElseThrow(()->{
        throw new Error("Post not found");
      });

      if(!post.getUserId().equals(userId)){
        throw new Error("Não autorizado");
      }
      this.postRepositorie.deleteById(postId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
