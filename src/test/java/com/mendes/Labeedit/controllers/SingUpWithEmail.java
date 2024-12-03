package com.mendes.Labeedit.controllers;

import org.springframework.http.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mendes.Labeedit.modules.user.dto.SingInDTO;
import com.mendes.Labeedit.modules.user.repository.AppUserRepositorie;
import com.mendes.Labeedit.utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class SingUpWithEmail {
  private  MockMvc mockMvc;

  @Autowired
  private AppUserRepositorie appUserRepositorie;



  @Autowired
  private WebApplicationContext context;

  @Autowired
  public SingUpWithEmail(MockMvc mockMvc) {
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
  public void it_must_be_possible_to_log_in() throws Exception{

    SingInDTO singInDTO = new SingInDTO("teste@email.com","123456","teste");
     mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/sing-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(singInDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated());
  
  }

    
}
