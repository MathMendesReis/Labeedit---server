package com.mendes.Labeedit.controllers;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mendes.Labeedit.modules.user.dto.SingInDTO;
import com.mendes.Labeedit.modules.user.dto.SingUpWithEmailDTO;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;
import com.mendes.Labeedit.utils.TestUtils;



@SpringBootTest
@AutoConfigureMockMvc
public class SingInUserControllerT {
  private  MockMvc mockMvc;

  @Autowired
  private AppUserRepositorie appUserRepositorie;



  @Autowired
  private WebApplicationContext context;

  @Autowired
  public SingInUserControllerT(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

   @Before
    public void setup(){
      mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

  @AfterEach
  void down(){
    appUserRepositorie.deleteAll();
  }


  @Test
  
  public void it_must_be_possible_to_save_a_user() throws Exception{
    String url = "/v1/user/sing-in";
    String email = "teste@email.com";
    String name = "teste";
    String password = "123456";

    SingInDTO singInDTO = new SingInDTO(email,password,name);
     mockMvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(singInDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated());

     SingUpWithEmailDTO singUpWithEmailDTO = new SingUpWithEmailDTO(email,password);       
     mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/sing-up/email")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(singUpWithEmailDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    
  }

   
}
