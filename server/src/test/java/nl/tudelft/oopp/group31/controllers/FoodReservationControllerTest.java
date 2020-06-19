package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.FoodReservation;
import nl.tudelft.oopp.group31.repositories.FoodReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FoodReservationControllerTest {

    @InjectMocks
    private FoodReservationController foodReservationController;

    @Mock
    private FoodReservationRepository foodReservationRepository;

    private FoodReservation foodReservation;
    private FoodReservation changedFoodReservation;
    private FoodReservation emptyFoodReservation;

    /**
     * Sets up the FoodReservations used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        foodReservation = new FoodReservation("maikdevries", "2020-03-26", 5, "1800", 9);
        changedFoodReservation = new FoodReservation("peterpan", "2020-03-27", 5, "1400", 7);
        emptyFoodReservation = new FoodReservation();
    }

    @Test
    public void testAddFoodReservation() {
        when(foodReservationRepository.save(foodReservation)).thenReturn(foodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.addNewFoodReservation(foodReservation);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(foodReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeFoodReservationFail() {
        Optional<FoodReservation> optionalEmptyFoodReservation = Optional.empty();
        when(foodReservationRepository.findById(emptyFoodReservation.getID())).thenReturn(optionalEmptyFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.changeDetails(emptyFoodReservation);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeFoodReservationSuccess() {
        Optional<FoodReservation> optionalFoodReservation = Optional.of(foodReservation);
        when(foodReservationRepository.findById(foodReservation.getID())).thenReturn(optionalFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.changeDetails(changedFoodReservation);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedFoodReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteFoodReservationFail() {
        Optional<FoodReservation> optionalEmptyFoodReservation = Optional.empty();
        when(foodReservationRepository.findById(emptyFoodReservation.getID())).thenReturn(optionalEmptyFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.deleteFoodReservation(emptyFoodReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteFoodReservationSuccess() {
        Optional<FoodReservation> optionalFoodReservation = Optional.of(foodReservation);
        when(foodReservationRepository.findById(foodReservation.getID())).thenReturn(optionalFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.deleteFoodReservation(foodReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetFoodReservationFail() {
        Optional<FoodReservation> optionalEmptyFoodReservation = Optional.empty();
        when(foodReservationRepository.findById(emptyFoodReservation.getID())).thenReturn(optionalEmptyFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.getFoodReservation(emptyFoodReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetFoodReservationSuccess() {
        Optional<FoodReservation> optionalFoodReservation = Optional.of(foodReservation);
        when(foodReservationRepository.findById(foodReservation.getID())).thenReturn(optionalFoodReservation);
        ResponseEntity<String> responseEntity = foodReservationController.getFoodReservation(foodReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(foodReservation.toString(), responseEntity.getBody());
    }

}
