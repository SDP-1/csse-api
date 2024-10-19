package com.csse.api.repository;

import com.csse.api.config.FirebaseConfig;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.Route;
import com.csse.api.model.WMA;
import com.csse.api.enums.FrequencyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CollectionScheduleRepositoryTest {

    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    @Autowired
    private RouteRepository routeRepository; // Ensure RouteRepository exists

    @Autowired
    private WMARepository wmaRepository; // Ensure WmaRepository exists

    @MockBean
    private FirebaseConfig firebaseConfig;

    private CollectionSchedule collectionSchedule;
    private Route route;
    private WMA wma;

    @BeforeEach
    public void setUp() {
        // Create and save a Wma
        wma = new WMA();
        wma.setAuthorityId(1L);
        wma.setAuthorityName("Test Authority");
        wma.setAddress("123 Test St.");
        wma.setContactNumber("123456789");
        wma.setLastAuditedDate(new Date());
        wma.setRegion("Test Region");

        // Save the Wma and flush to ensure it's persisted
        wma = wmaRepository.save(wma);

        // Create and save a Route
        route = new Route();
        route.setRouteId(1L); // Assuming you want to use this ID
        route.setRouteName("Test Route");
        route.setRouteDescription("Description of test route");
        route.setStartLocation("Start Location");
        route.setEndLocation("End Location");
        route.setArea("Area");
        route.setLastOptimizedDate(new Date());
        route.setWma(wma); // Set the Wma before saving

        // Save the route and flush to ensure it's persisted
        route = routeRepository.save(route);

        // Now set up the CollectionSchedule with the saved route
        collectionSchedule = new CollectionSchedule();
        collectionSchedule.setId(1L); // Set a valid ID for testing
        collectionSchedule.setStartDate(new Date());
        collectionSchedule.setEndDate(new Date());
        collectionSchedule.setFrequency(FrequencyType.WEEKLY);
        collectionSchedule.setRoute(route); // Set the existing route

        // Save the collection schedule and flush
        collectionScheduleRepository.save(collectionSchedule);
    }

    @Test
    public void findById_ShouldReturnCollectionSchedule_WhenExists() {
        Optional<CollectionSchedule> foundSchedule = collectionScheduleRepository.findById(collectionSchedule.getId());

        assertThat(foundSchedule).isPresent();
        assertThat(foundSchedule.get().getId()).isEqualTo(collectionSchedule.getId());
    }

    @Test
    public void findById_ShouldReturnEmpty_WhenNotExists() {
        Optional<CollectionSchedule> foundSchedule = collectionScheduleRepository.findById(999L);

        assertThat(foundSchedule).isNotPresent();
    }
}
