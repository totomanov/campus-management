package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.BikeReservation;
import nl.tudelft.oopp.group31.repositories.BikeReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BikeReservationControllerTest {

    @InjectMocks
    private BikeReservationController bikeReservationController;

    @Mock
    private BikeReservationRepository bikeReservationRepository;

    private BikeReservation bikeReservation;
    private BikeReservation changedBikeReservation;
    private BikeReservation emptyBikeReservation;

    /**
     * Sets up the BikeReservations used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        bikeReservation = new BikeReservation("maikdevries", 1);
        changedBikeReservation = new BikeReservation("peterpan", 1);
        emptyBikeReservation = new BikeReservation();
    }

    @Test
    public void testAddBikeReservation() {
        when(bikeReservationRepository.save(bikeReservation)).thenReturn(bikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.addNewBikeReservation(bikeReservation);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(bikeReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeBikeReservationFail() {
        Optional<BikeReservation> optionalEmptyBikeReservation = Optional.empty();
        when(bikeReservationRepository.findById(emptyBikeReservation.getID())).thenReturn(optionalEmptyBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.changeDetails(emptyBikeReservation);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeBikeReservationSuccess() {
        Optional<BikeReservation> optionalBikeReservation = Optional.of(bikeReservation);
        when(bikeReservationRepository.findById(bikeReservation.getID())).thenReturn(optionalBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.changeDetails(changedBikeReservation);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedBikeReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteBikeReservationFail() {
        Optional<BikeReservation> optionalEmptyBikeReservation = Optional.empty();
        when(bikeReservationRepository.findById(emptyBikeReservation.getID())).thenReturn(optionalEmptyBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.deleteBikeReservation(emptyBikeReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteBikeReservationSuccess() {
        Optional<BikeReservation> optionalBikeReservation = Optional.of(bikeReservation);
        when(bikeReservationRepository.findById(bikeReservation.getID())).thenReturn(optionalBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.deleteBikeReservation(bikeReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetBikeReservationFail() {
        Optional<BikeReservation> optionalEmptyBikeReservation = Optional.empty();
        when(bikeReservationRepository.findById(emptyBikeReservation.getID())).thenReturn(optionalEmptyBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.getBikeReservation(emptyBikeReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetBikeReservationSuccess() {
        Optional<BikeReservation> optionalBikeReservation = Optional.of(bikeReservation);
        when(bikeReservationRepository.findById(bikeReservation.getID())).thenReturn(optionalBikeReservation);
        ResponseEntity<String> responseEntity = bikeReservationController.getBikeReservation(bikeReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(bikeReservation.toString(), responseEntity.getBody());
    }

}
