package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.RoomReservation;
import nl.tudelft.oopp.group31.repositories.RoomReservationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RoomReservationControllerTest {

    @InjectMocks
    private RoomReservationController roomReservationController;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    private RoomReservation roomReservation;
    private RoomReservation changedRoomReservation;
    private RoomReservation emptyRoomReservation;

    /**
     * Sets up the RoomReservations used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        roomReservation = new RoomReservation("maikdevries", "2020-03-26", 5, "1000", "1030");
        changedRoomReservation = new RoomReservation("peterpan", "2020-03-27", 5, "1100", "1130");
        emptyRoomReservation = new RoomReservation();
    }

    @Test
    public void testAddRoomReservation() {
        when(roomReservationRepository.save(roomReservation)).thenReturn(roomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.addNewRoomReservation(roomReservation);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(roomReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeRoomReservationFail() {
        Optional<RoomReservation> optionalEmptyRoomReservation = Optional.empty();
        when(roomReservationRepository.findById(emptyRoomReservation.getID())).thenReturn(optionalEmptyRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.changeDetails(emptyRoomReservation);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeRoomReservationSuccess() {
        Optional<RoomReservation> optionalRoomReservation = Optional.of(roomReservation);
        when(roomReservationRepository.findById(roomReservation.getID())).thenReturn(optionalRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.changeDetails(changedRoomReservation);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedRoomReservation.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteRoomReservationFail() {
        Optional<RoomReservation> optionalEmptyRoomReservation = Optional.empty();
        when(roomReservationRepository.findById(emptyRoomReservation.getID())).thenReturn(optionalEmptyRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.deleteRoomReservation(emptyRoomReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteRoomReservationSuccess() {
        Optional<RoomReservation> optionalRoomReservation = Optional.of(roomReservation);
        when(roomReservationRepository.findById(roomReservation.getID())).thenReturn(optionalRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.deleteRoomReservation(roomReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetRoomReservationFail() {
        Optional<RoomReservation> optionalEmptyRoomReservation = Optional.empty();
        when(roomReservationRepository.findById(emptyRoomReservation.getID())).thenReturn(optionalEmptyRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.getRoomReservation(emptyRoomReservation.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetRoomReservationSuccess() {
        Optional<RoomReservation> optionalRoomReservation = Optional.of(roomReservation);
        when(roomReservationRepository.findById(roomReservation.getID())).thenReturn(optionalRoomReservation);
        ResponseEntity<String> responseEntity = roomReservationController.getRoomReservation(roomReservation.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(roomReservation.toString(), responseEntity.getBody());
    }

}
