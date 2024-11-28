package com.mendes.Labeedit.modules.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mendes.Labeedit.modules.user.dto.SingUpWithEmailDTO;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.Instant;
import java.util.Arrays;
import java.time.Duration;




@RestController
@RequestMapping("v1/user/sing-up/email")
public class SingUpWithEmailController {
  
private AppUserRepositorie appUserRepositorie;
private BCryptPasswordEncoder encoder;

  @Value("${security.token.secret.user}")
  private String secretKey;
	public SingUpWithEmailController(AppUserRepositorie appUserRepositorie,BCryptPasswordEncoder encoder) {
		this.appUserRepositorie = appUserRepositorie;
    this.encoder = encoder;
  }
  @PostMapping()
  public ResponseEntity<?> handle(@Valid @RequestBody SingUpWithEmailDTO SingUpWithEmailDTO) {
   try {
        var user = this.appUserRepositorie.findByEmail(SingUpWithEmailDTO.getEmail()).orElseThrow(()-> {
          throw new UsernameNotFoundException("Username/password incorrect");
      });

      var passwordMatches = this.encoder.matches(SingUpWithEmailDTO.getPassword(), user.getPassword());

      if (!passwordMatches) {
        throw new UsernameNotFoundException("Username/password incorrect");
      } 
      var roles = Arrays.asList("");

      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
      var token = JWT.create()
          .withIssuer("")
          .withSubject(user.getId().toString())
          .withClaim("roles", roles)
          .withExpiresAt(expiresIn)
          .sign(algorithm); 
      return new ResponseEntity<>(token, HttpStatus.OK);
   } catch (Exception e) {
    // TODO: handle exception
    return ResponseEntity.badRequest().body(e.getMessage());
   }
  }
}
