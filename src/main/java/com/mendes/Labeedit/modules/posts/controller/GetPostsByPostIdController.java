package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.posts.dto.response.FindAllPostDTOResponse;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.utils.ApiRoutes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class GetPostsByPostIdController {
  @Autowired
  private PostRepositorie postRepositorie;
  @GetMapping("/{post-id}")
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
