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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/user/sing-in")
public class CreateUserController {
  @Autowired
  private AppUserRepositorie appUserRepositorie;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping()
   @Operation(summary = "Cadastro de candidato", description = "Essa função é responsável por cadastrar um candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = AppUser.class))
      }),
      @ApiResponse(responseCode = "400", description = "Usuário já existe")
  })
  @Tag(name = "Usuarios", description = "Registro de novo usuario")
  public ResponseEntity<?> handle(@Valid @RequestBody SingInDTO singInDTO){
      try {
        var userExists = appUserRepositorie.findByEmail(singInDTO.getEmail());
        if(userExists.isPresent()){
          return new ResponseEntity<>("Email já registrado", HttpStatus.CONFLICT);
        }
        var hashedPassword = bCryptPasswordEncoder.encode(singInDTO.getPassword()).toString();

    		AppUser appUser = new AppUser("noel",singInDTO.getEmail(),hashedPassword);
        this.appUserRepositorie.save(appUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
      }
}
 
