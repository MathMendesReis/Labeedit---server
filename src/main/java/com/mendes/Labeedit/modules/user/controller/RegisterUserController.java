package com.mendes.Labeedit.modules.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

@RestController
@RequestMapping("v1/users")
public class RegisterUserController {
private AppUserRepositorie appUserRepositorie;
private BCryptPasswordEncoder encoder;
 @Value("${api.security.token.secret}")
  private String secret;
	public RegisterUserController(AppUserRepositorie appUserRepositorie,BCryptPasswordEncoder encoder) {
		this.appUserRepositorie = appUserRepositorie;
    this.encoder = encoder;
  }

  @PostMapping("register")
  public ResponseEntity<?> handle(){
        var userExists = appUserRepositorie.findByEmail("noel@gmail.com");
        System.out.println(this.secret);

        if(userExists.isPresent()){
          return new ResponseEntity<>("Email já registrado", HttpStatus.CONFLICT);
        }
        var hashedPassword = encoder.encode("123456");

    		AppUser appUser = new AppUser("noel'", "noel@gmail.com",hashedPassword);
        appUserRepositorie.save(appUser);
        return new ResponseEntity<>(appUser,HttpStatus.CREATED);
      }
}
