package com.csse.api.repository;

import com.csse.api.config.FirebaseConfig;
import com.csse.api.model.BinTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Rollback(false)
class BinTypesRepositoryTest {

    @Autowired
    private BinTypesRepository repository;

    @MockBean
    private FirebaseConfig firebaseConfig;

    private BinTypes binType;

    @BeforeEach
    void setUp() {
        binType = new BinTypes();
        binType.setName("Recycling Bin");
        binType.setCapacity("50L");
        binType.setProducer("EcoCorp");
        binType.setType("Plastic");
    }

    @Test
    void save_ShouldPersistBinType() {
        BinTypes savedBinType = repository.save(binType);

        assertNotNull(savedBinType);
        assertNotNull(savedBinType.getId());  // ID should be generated
        assertEquals(binType.getName(), savedBinType.getName());
    }

    @Test
    void findById_ShouldReturnBinType_WhenExists() {
        BinTypes savedBinType = repository.save(binType);

        Optional<BinTypes> foundBinType = repository.findById(savedBinType.getId());

        assertTrue(foundBinType.isPresent());
        assertEquals(savedBinType.getName(), foundBinType.get().getName());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        Optional<BinTypes> foundBinType = repository.findById(Long.MAX_VALUE); // Assuming this ID does not exist

        assertFalse(foundBinType.isPresent());
    }

    @Test
    void deleteById_ShouldRemoveBinType() {
        BinTypes savedBinType = repository.save(binType);
        long id = savedBinType.getId();

        repository.deleteById(id);

        Optional<BinTypes> foundBinType = repository.findById(id);
        assertFalse(foundBinType.isPresent());
    }

    @Test
    void existsById_ShouldReturnTrue_WhenExists() {
        BinTypes savedBinType = repository.save(binType);
        long id = savedBinType.getId();

        assertTrue(repository.existsById(id));
    }

    @Test
    void existsById_ShouldReturnFalse_WhenNotExists() {
        assertFalse(repository.existsById(Long.MAX_VALUE)); // Assuming this ID does not exist
    }
}
