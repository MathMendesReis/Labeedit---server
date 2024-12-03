package com.mendes.Labeedit.modules.posts.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.Labeedit.exceptions.UserFoundException;
import com.mendes.Labeedit.modules.posts.dto.request.RegisterPostRequestDTO;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.posts.repository.PostRepositorie;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

@Service
public class CreateUserPostUseCase {
   @Autowired
  private PostRepositorie postRepositorie;

  @Autowired
  private AppUserRepositorie appUserRepositorie;
  public void execute(String userId,RegisterPostRequestDTO registerPostRequestDTO){
    var user = this.appUserRepositorie.findById(UUID.fromString(userId))
        .orElseThrow(() -> {
            throw new UserFoundException();
        });
        PostEntitie postEntitie = new PostEntitie(UUID.fromString(user.getId().toString()),registerPostRequestDTO.getContent());
        this.postRepositorie.save(postEntitie);
  }
}
