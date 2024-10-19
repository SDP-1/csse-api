package com.csse.api.repository;

import com.csse.api.config.FirebaseConfig; // Import your config class
import com.csse.api.model.GarbageCollector;
import com.csse.api.model.WMA; // Import the WMA model
import com.csse.api.enums.VehicleType; // Import the VehicleType enum
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
public class GarbageCollectorRepositoryTest {

    @Autowired
    private GarbageCollectorRepository garbageCollectorRepository;

    @Autowired
    private WMARepository wmaRepository; // Assuming you have a WMA repository

    @MockBean
    private FirebaseConfig firebaseConfig; // Mock FirebaseConfig if needed

    private GarbageCollector garbageCollector;
    private WMA wma;

    @BeforeEach
    public void setUp() {
        // Set up WMA
        wma = new WMA();
        wma.setAuthorityName("Test Authority");
        wma.setRegion("Test Region");
        wma.setContactNumber("123456789");
        wma.setAddress("Test Address");
        // Save the WMA to the repository
        wma = wmaRepository.save(wma);

        // Set up GarbageCollector
        garbageCollector = new GarbageCollector();
        garbageCollector.setCollectorId("C123");
        garbageCollector.setVehicleRegNo("ABC-123");
        garbageCollector.setVehicleType(VehicleType.MD);
        garbageCollector.setModel("Model X");
        garbageCollector.setCurrentStatus("ACTIVE");
        garbageCollector.setCurrentLocation("Location A");
        garbageCollector.setWma(wma); // Set the WMA association

        // Save the garbage collector to the repository
        garbageCollector = garbageCollectorRepository.save(garbageCollector);
    }

    @Test
    public void findById_ShouldReturnGarbageCollector_WhenExists() {
        Optional<GarbageCollector> foundCollector = garbageCollectorRepository.findById(garbageCollector.getId());

        assertThat(foundCollector).isPresent();
        assertThat(foundCollector.get().getCollectorId()).isEqualTo("C123");
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenNotExists() {
        Optional<GarbageCollector> foundCollector = garbageCollectorRepository.findById(999L); // An ID that doesn't exist

        assertThat(foundCollector).isNotPresent();
    }

    @Test
    public void delete_ShouldRemoveGarbageCollector() {
        Long id = garbageCollector.getId();
        garbageCollectorRepository.deleteById(id);

        Optional<GarbageCollector> foundCollector = garbageCollectorRepository.findById(id);
        assertThat(foundCollector).isNotPresent();
    }

    @Test
    public void save_ShouldPersistGarbageCollector() {
        GarbageCollector newCollector = new GarbageCollector();
        newCollector.setCollectorId("C124");
        newCollector.setVehicleRegNo("XYZ-789");
        newCollector.setVehicleType(VehicleType.XL);
        newCollector.setModel("Model Y");
        newCollector.setCurrentStatus("ACTIVE");
        newCollector.setCurrentLocation("Location B");
        newCollector.setWma(wma); // Set the WMA association

        GarbageCollector savedCollector = garbageCollectorRepository.save(newCollector);

        assertThat(savedCollector).isNotNull();
        assertThat(savedCollector.getId()).isNotNull();
        assertThat(savedCollector.getCollectorId()).isEqualTo("C124");
    }
}
