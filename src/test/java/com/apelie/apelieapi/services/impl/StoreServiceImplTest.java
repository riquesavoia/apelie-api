package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.models.Store;
import com.apelie.apelieapi.models.enums.StoreCategory;
import com.apelie.apelieapi.repositories.StoreRepository;
import com.apelie.apelieapi.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/*
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")*/
public class StoreServiceImplTest {
/*
    @Autowired
    private StoreService storeService;

    @MockBean
    private StoreRepository storeRepository;

    @Before
    public void setUp() {
        Store store = new Store();
        Store store2 = new Store();
        List<StoreCategory> categoryList = new ArrayList<>();
        categoryList.add(StoreCategory.FOOD);

        store.setStoreId(Long.valueOf(0));
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

        store2.setStoreId(Long.valueOf(1));
        store2.setAddressNumber("101");
        store2.setCategoryList(categoryList);
        store2.setBannerUrl("banner.com/banner.png");
        store2.setZipCode("11542-541");
        store2.setCity("AnotherCity");
        store2.setEmail("email2@email.com");
        store2.setDescription("Description test");
        store2.setState("AnotherState");
        store2.setFacebookAccount("facebook.com/test2");
        store2.setInstagramAccount("instagram.com/test2");
        store2.setLogoUrl("logourl.com/logo1.png");
        store2.setName("Name test");
        store2.setNeighbourhood("Another Neighbourhood");

        Mockito.when(storeRepository.findById(store.getStoreId()))
                .thenReturn(java.util.Optional.of(store));

        List<Store> storesResponse = new ArrayList<>();
        storesResponse.add(store);
        storesResponse.add(store2);
        Mockito.when(storeRepository.findAll())
                .thenReturn(storesResponse);
    }

    @Test
    public void whenValidStore_thenStoreShouldBeFoundById() {
        Store store = storeService.getStoreById(Long.valueOf(0));

        assertThat(store.getName()).isEqualTo("Test name");
    }

    @Test
    public void whenValidStores_thenStoresShouldBeFound() {
        List<Store> stores = storeService.getAllStores(null);

        assertThat(stores.size()).isEqualTo(2);
    }*/
}