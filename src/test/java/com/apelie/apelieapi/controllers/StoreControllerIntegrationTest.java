package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.enums.Category;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class StoreControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void givenStore_whenGetStores_thenStatus200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Store store = new Store();
        store.setAddressNumber("100");
        store.setCategory(Category.FOOD);
        store.setBannerUrl("bannerurl.com");
        store.setCep("13095-541");
        store.setCity("TestCity");
        store.setEmail("email@email.com");
        store.setDescription("Description test");
        store.setState("StateTest");
        store.setFacebookLink("facebook.com/test");
        store.setInstagramLink("instagram.com/test");
        store.setLogoUrl("logourl.com/logo.png");
        store.setName("Test name");
        store.setNeighbourhood("Test");

        storeRepository.save(store);

        mvc.perform(get("/store/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(store)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStore_whenGetStoreById_thenStatus200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Store store = new Store();
        store.setAddressNumber("100");
        store.setCategory(Category.FOOD);
        store.setBannerUrl("bannerurl.com");
        store.setCep("13095-541");
        store.setCity("TestCity");
        store.setEmail("email@email.com");
        store.setDescription("Description test");
        store.setState("StateTest");
        store.setFacebookLink("facebook.com/test");
        store.setInstagramLink("instagram.com/test");
        store.setLogoUrl("logourl.com/logo.png");
        store.setName("Test name");
        store.setNeighbourhood("Test");

        storeRepository.save(store);

        mvc.perform(get("/store/" + store.getStoreId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(store)))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test name"));
    }
}