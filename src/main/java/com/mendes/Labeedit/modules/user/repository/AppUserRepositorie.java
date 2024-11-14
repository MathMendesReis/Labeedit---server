package com.mendes.Labeedit.modules.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.Labeedit.modules.user.entities.AppUser;

import java.util.UUID;;

public interface AppUserRepositorie extends JpaRepository<AppUser,UUID> {
  Optional<AppUser> findByEmail(String email);

}
