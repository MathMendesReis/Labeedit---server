package com.mendes.Labeedit.modules.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.user.dto.SingInDTO;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/user/sing-in")
public class SingInUserController {
private AppUserRepositorie appUserRepositorie;
private BCryptPasswordEncoder encoder;

	public SingInUserController(AppUserRepositorie appUserRepositorie,BCryptPasswordEncoder encoder) {
		this.appUserRepositorie = appUserRepositorie;
    this.encoder = encoder;
  }

  @PostMapping()
  public ResponseEntity<?> handle(@Valid @RequestBody SingInDTO singInDTO){
        var userExists = appUserRepositorie.findByEmail(singInDTO.getEmail());
        if(userExists.isPresent()){
          return new ResponseEntity<>("Email já registrado", HttpStatus.CONFLICT);
        }
        var hashedPassword = encoder.encode("123456");

    		AppUser appUser = new AppUser("noel'", "noel@gmail.com",hashedPassword);
        appUserRepositorie.save(appUser);
        return new ResponseEntity<>(appUser,HttpStatus.CREATED);
      }
}
