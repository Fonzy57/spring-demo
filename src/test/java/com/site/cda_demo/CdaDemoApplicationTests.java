package com.site.cda_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.cda_demo.model.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class CdaDemoApplicationTests {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mvc;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }


  // Ici test utilitaire
  @Test
  public void getAllEtiquettes_shouldBe200Ok() throws Exception {
    mvc.perform(get("/etiquettes"))
        .andExpect(status().isOk());
  }

  @WithMockUser(username = "user@toto.fr", roles = {"UTILISATEUR"})
  @Test
  public void getAllProduits_shouldBe200Ok() throws Exception {
    mvc.perform(get("/produits"))
        .andExpect(status().isOk());
  }

  @WithMockUser(username = "user@toto.fr", roles = {"UTILISATEUR"})
  // @WithUserDetails("user@toto.fr") // Obligatoire si le contrôleur utilise @AuthenticationPrincipal
  @Test
  public void deleteOneProduit_shouldBe403Forbidden() throws Exception {
    mvc.perform(delete("/produit/1"))
        .andExpect(status().isForbidden());
  }

  @WithMockUser(username = "admin@toto.fr", roles = {"ADMINISTRATEUR"})
  @Test
  public void getClientWithId1_shouldIncludeOnlyNeededInformations() throws Exception {
    mvc.perform(get("/utilisateur/1"))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.email").exists())
        .andExpect(jsonPath("$.role").exists())
        .andExpect(jsonPath("$.password").doesNotExist());
  }

  @Test
  @WithUserDetails("admin@toto.fr")
  void addNewProduitWithMandatoryFields_shouldBe201Created() throws Exception {
    Produit produit = new Produit();
    produit.setNom("Toto");
    produit.setCode("Code_produit");
    produit.setPrix(2);

    String jsonProduit = objectMapper.writeValueAsString(produit);

    mvc.perform(
            post("/produit")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonProduit)
        )
        .andExpect(status().isCreated());

  }

}
