package com.apelie.apelieapi.controllers;

import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.enums.StoreCategory;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;


/*@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")*/
public class StoreControllerIntegrationTest {
/*
    @Autowired
    private MockMvc mvc;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void givenStore_whenGetStores_thenStatus200() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<StoreCategory> categoryList = new ArrayList<>();
        categoryList.add(StoreCategory.FOOD);

        Store store = new Store();
        store.setAddressNumber("100");
        store.setCategoryList(categoryList);
        store.setBannerUrl("bannerurl.com");
        store.setZipCode("13095-541");
        store.setCity("TestCity");
        store.setEmail("email@email.com");
        store.setDescription("Description test");
        store.setState("StateTest");
        store.setFacebookAccount("test");
        store.setInstagramAccount("test");
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
        List<StoreCategory> categoryList = new ArrayList<>();
        categoryList.add(StoreCategory.FOOD);

        Store store = new Store();
        store.setAddressNumber("100");
        store.setCategoryList(categoryList);
        store.setBannerUrl("bannerurl.com");
        store.setZipCode("13095-541");
        store.setCity("TestCity");
        store.setEmail("email@email.com");
        store.setDescription("Description test");
        store.setState("StateTest");
        store.setFacebookAccount("test");
        store.setInstagramAccount("test");
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
    }*/
}
