package com.csse.api.repository;

import com.csse.api.config.FirebaseConfig;
import com.csse.api.model.Admin;
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
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @MockBean
    private FirebaseConfig firebaseConfig;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin(1L, "test@example.com", "password", UserType.ADMIN, "John Doe", false);
        adminRepository.save(admin);
    }

    @Test
    public void findByName_ShouldReturnAdmin_WhenExists() {
        Optional<Admin> foundAdmin = adminRepository.findByName("John Doe");

        assertThat(foundAdmin).isPresent();
        assertThat(foundAdmin.get().getName()).isEqualTo("John Doe");
    }

    @Test
    public void findByName_ShouldReturnEmpty_WhenNotExists() {
        Optional<Admin> foundAdmin = adminRepository.findByName("Nonexistent Name");

        assertThat(foundAdmin).isNotPresent();
    }
}
