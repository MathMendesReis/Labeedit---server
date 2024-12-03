package com.mendes.Labeedit.modules.reaction.repositorie;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.Labeedit.modules.reaction.entitie.ReactionEntitie;

public interface ReactionRepository extends JpaRepository<ReactionEntitie, UUID> {
    Optional<ReactionEntitie> findByPostIdAndUserId(UUID postId, UUID userId);
    List<ReactionEntitie> findByReactValue(int reactValue);
}
