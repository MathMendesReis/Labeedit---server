package com.mendes.Labeedit.modules.reaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.utils.ApiRoutes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.reaction.entitie.ReactionEntitie;
import com.mendes.Labeedit.modules.reaction.repositorie.ReactionRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(ApiRoutes.REACT_BASE)
public class CreateReactController {
  @Autowired
  private ReactionRepository ReactionRepository;

  @Autowired
  private PostRepositorie postRepositorie;
  @PostMapping("/{post-id}")
  @Operation(summary = "Registro de reações", description = "Essa função é responsável por registrar uma reação de um usuario ,'like/dislike', ")
  @Tag(name = "Reações", description = "Listagem das vagas")
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = ReactionEntitie.class))
      }),
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<?> execute(@PathVariable("post-id") UUID postId,HttpServletRequest request,@RequestBody int value) {
      try {
        UUID  userid = UUID.fromString(request.getAttribute("app_user_id").toString());
        Optional<PostEntitie> postEntitie = this.postRepositorie.findById(postId);
         var res = this.ReactionRepository.findByPostIdAndUserId(postId, userid);
         ReactionEntitie reactionEntitie; 
         if(res.isEmpty()){
          reactionEntitie = new ReactionEntitie(value,userid,postId);
          this.ReactionRepository.save(reactionEntitie);
         };         
         if(res.isPresent()){
          reactionEntitie = res.get();
          reactionEntitie.setReactValue(value);
          this.ReactionRepository.save(reactionEntitie);
         };
        
          int dislikes = this.ReactionRepository.findByReactValue(0).size();
          int likes = this.ReactionRepository.findByReactValue(1).size();

          postEntitie.get().setDislikes(dislikes);
          postEntitie.get().setLikes(likes);
         this.postRepositorie.save(postEntitie.get());
        return new ResponseEntity<>( null,HttpStatus.CREATED);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
  }
  
}
