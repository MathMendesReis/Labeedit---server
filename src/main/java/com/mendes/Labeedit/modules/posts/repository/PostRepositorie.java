package com.mendes.Labeedit.modules.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import java.util.*;
public interface PostRepositorie extends JpaRepository<PostEntitie,UUID>{
  List<PostEntitie> findByUserId(UUID userId);

}
