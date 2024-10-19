package com.csse.api.repository;

import com.csse.api.config.FirebaseConfig;
import com.csse.api.model.Business;
import com.csse.api.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BusinessRepositoryTest {

    @Autowired
    private BusinessRepository businessRepository;

    @MockBean
    private FirebaseConfig firebaseConfig;

    private Business business;

    @BeforeEach
    public void setUp() {
        business = new Business();
        business.setId(1L);
        business.setName("Test Business");
        business.setAddress("123 Test St");
        business.setResidentialType("Commercial");
        business.setBusinessType("Retail");
        business.setBusinessRegistration("123456789");

        // Save the business and check if it's persisted
        businessRepository.save(business);

        // Optionally verify it was saved
        Optional<Business> savedBusiness = businessRepository.findById(1L);
        assertThat(savedBusiness).isPresent();  // Ensure the business was saved
    }

    @Test
    public void findById_ShouldReturnBusiness_WhenExists() {
        Optional<Business> foundBusiness = businessRepository.findById(1L);

        assertThat(foundBusiness).isPresent();
        assertThat(foundBusiness.get().getName()).isEqualTo("Test Business");
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenNotExists() {
        Optional<Business> foundBusiness = businessRepository.findById(2L);

        assertThat(foundBusiness).isNotPresent();
    }

    @Test
    public void findAll_ShouldReturnAllBusinesses() {
        Business business2 = new Business();
        business2.setId(2L);
        business2.setName("Another Business");
        business2.setAddress("456 Another St");
        businessRepository.save(business2);

        Iterable<Business> businesses = businessRepository.findAll();
        assertThat(businesses).hasSize(2);
    }

    @Test
    public void save_ShouldPersistBusiness() {
        Business newBusiness = new Business();
        newBusiness.setName("New Business");
        newBusiness.setAddress("789 New St");
        newBusiness.setResidentialType("Commercial");
        newBusiness.setBusinessType("Wholesale");
        newBusiness.setBusinessRegistration("987654321");

        Business savedBusiness = businessRepository.save(newBusiness);
        assertThat(savedBusiness.getId()).isNotNull();
        assertThat(savedBusiness.getName()).isEqualTo("New Business");
    }

    @Test
    public void deleteById_ShouldRemoveBusiness_WhenExists() {
        businessRepository.deleteById(business.getId());

        Optional<Business> foundBusiness = businessRepository.findById(business.getId());
        assertThat(foundBusiness).isNotPresent();
    }
}
