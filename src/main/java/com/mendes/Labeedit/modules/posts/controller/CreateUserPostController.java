package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.exceptions.UserFoundException;
import com.mendes.Labeedit.modules.posts.dto.request.RegisterPostRequestDTO;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("v1/posts")
public class CreateUserPostController {
  @Autowired
  private PostRepositorie postRepositorie;

  @Autowired
  private AppUserRepositorie appUserRepositorie;
  @PostMapping("/register")
  public ResponseEntity<?> handle(@Valid @RequestBody RegisterPostRequestDTO registerPostRequestDTO,HttpServletRequest request) {
    try {
        var appUserId = request.getAttribute("app_user_id");

        var user = this.appUserRepositorie.findById(UUID.fromString(appUserId.toString()))
        .orElseThrow(() -> {
            throw new UserFoundException();
        });
        PostEntitie postEntitie = new PostEntitie(UUID.fromString(user.getId().toString()),registerPostRequestDTO.getContent());
        this.postRepositorie.save(postEntitie);
        return new ResponseEntity<>(HttpStatus.CREATED);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());

      }
  }

}
