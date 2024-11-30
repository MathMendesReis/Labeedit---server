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
import com.mendes.Labeedit.modules.posts.useCases.CreateUserPostUseCase;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;
import com.mendes.Labeedit.utils.ApiRoutes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class CreateUserPostController {
  @Autowired
  private CreateUserPostUseCase createUserPostUseCase;

  @PostMapping()
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
