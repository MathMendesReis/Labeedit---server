package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.exceptions.UserFoundException;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("v1/post")
public class PostController {
  @Autowired
  private PostRepositorie postRepositorie;

  @Autowired
  private AppUserRepositorie appUserRepositorie;
  @PostMapping()
  public ResponseEntity<?> insertNewPost(HttpServletRequest request) {
    var appUserId = request.getAttribute("app_user_id");
      try {

        this.appUserRepositorie.findById(UUID.fromString(appUserId.toString()))
        .orElseThrow(() -> {
            throw new UserFoundException();
        });
        PostEntitie postEntitie = new PostEntitie(UUID.fromString(appUserId.toString()),"conteudo");
        this.postRepositorie.save(postEntitie);
        return new ResponseEntity<>(null,HttpStatus.OK);
      } catch (Exception e) {
        // TODO: handle exception
        return ResponseEntity.badRequest().body(e.getMessage());

      }
  }



  
  
}
