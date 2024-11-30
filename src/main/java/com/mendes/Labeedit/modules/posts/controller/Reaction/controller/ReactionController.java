package com.mendes.Labeedit.modules.posts.controller.Reaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.posts.controller.Reaction.dto.RequestReactControllerDTo;
import com.mendes.Labeedit.modules.posts.controller.Reaction.entitie.ReactionEntitie;
import com.mendes.Labeedit.modules.posts.controller.Reaction.repositorie.ReactionRepositorie;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("v1/post/react")
public class ReactionController {
  @Autowired
  private ReactionRepositorie reactionRepositorie;

  @Autowired
  private AppUserRepositorie appUserRepositorie;

  @Autowired
  private PostRepositorie postRepositorie;
  @PostMapping()
  public ResponseEntity<?> postMethodName(HttpServletRequest request,@RequestBody RequestReactControllerDTo requestReactControllerDTo) {
      try {
        var appUserId = request.getAttribute("app_user_id");

        Optional<AppUser> appuser = this.appUserRepositorie.findById(UUID.fromString(appUserId.toString()));
        Optional<PostEntitie> posOptional = this.postRepositorie.findById(requestReactControllerDTo.getPostId());

        if (appuser.isEmpty()) {
          return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
        }
        if (posOptional.isEmpty()) {
          return new ResponseEntity<>("post not found",HttpStatus.NOT_FOUND);
        }

        Optional<ReactionEntitie> existingReaction = reactionRepositorie.findByUserIdAndPostId(appuser.get().getId(), requestReactControllerDTo.getPostId());
        
        PostEntitie postEntitie = posOptional.get();
          if (requestReactControllerDTo.getReact() == 0) {
            postEntitie.setDislikes(postEntitie.getDislikes() + 1);
            if(postEntitie.getLikes() > 0){
              postEntitie.setLikes(postEntitie.getLikes() - 1); 
            }
  
          } else {
            postEntitie.setLikes(postEntitie.getLikes() + 1); 
            if(postEntitie.getDislikes() > 0){
              postEntitie.setDislikes(postEntitie.getDislikes() - 1);
            }
          }

        if (existingReaction.isPresent()) {
          ReactionEntitie reaction = existingReaction.get();
          reaction.setReactValue(requestReactControllerDTo.getReact()); 
          reactionRepositorie.save(reaction);
          return new ResponseEntity<>(null,HttpStatus.CREATED);

      }
       ReactionEntitie reactionEntitie = new ReactionEntitie(requestReactControllerDTo,appuser.get().getId());
        
        this.reactionRepositorie.save(reactionEntitie);
        this.postRepositorie.save(postEntitie);

        return new ResponseEntity<>(null,HttpStatus.CREATED);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e.getMessage());
      }
      
  }
  
  
}
