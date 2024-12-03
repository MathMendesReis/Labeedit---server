package com.mendes.Labeedit.modules.posts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

import java.util.List;
@RestController
@RequestMapping(ApiRoutes.POSTS_BASE)
public class GetPostsController {

  @Autowired
  private PostRepositorie postRepositorie;

  @GetMapping()
  @Operation(summary = "Listagens de todos os posts", description = "Essa função é responsável por listar todos os posts.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = FindAllPostDTOResponse.class)))

      }),
     
  })
  @Tag(name = "Posts", description = "Listagens de posts")
  public ResponseEntity<?> handle(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) {
      try {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostEntitie> postsPage = postRepositorie.findAll(pageable);
        List<FindAllPostDTOResponse> response = postsPage.stream()
        .map(FindAllPostDTOResponse::new) 
        .toList();
        return new ResponseEntity<>(response,HttpStatus.OK);

      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
  }
}
