package com.mendes.Labeedit.modules.posts.controller.Reaction.repositorie;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.Labeedit.modules.posts.controller.Reaction.entitie.ReactionEntitie;

public interface ReactionRepositorie extends JpaRepository<ReactionEntitie, UUID>{
  Optional<ReactionEntitie> findByUserIdAndPostId(UUID userId, UUID postId);

}
