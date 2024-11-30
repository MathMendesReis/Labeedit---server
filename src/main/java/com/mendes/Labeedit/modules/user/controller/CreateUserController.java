package com.mendes.Labeedit.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.user.dto.SingInDTO;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/user/sing-in")
public class CreateUserController {
  @Autowired
  private AppUserRepositorie appUserRepositorie;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping()
  public ResponseEntity<?> handle(@Valid @RequestBody SingInDTO singInDTO){
      try {
        var userExists = appUserRepositorie.findByEmail(singInDTO.getEmail());
        if(userExists.isPresent()){
          return new ResponseEntity<>("Email já registrado", HttpStatus.CONFLICT);
        }
        var hashedPassword = bCryptPasswordEncoder.encode(singInDTO.getPassword()).toString();

    		AppUser appUser = new AppUser("noel",singInDTO.getEmail(),hashedPassword);
        this.appUserRepositorie.save(appUser);
        return new ResponseEntity<>(appUser,HttpStatus.CREATED);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
      }
}
 
