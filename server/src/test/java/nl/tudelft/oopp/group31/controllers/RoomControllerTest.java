package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.repositories.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomRepository roomRepository;

    private Room room;
    private Room changedRoom;
    private Room emptyRoom;

    /**
     * Sets up the Rooms used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        room = new Room("Project Room 8", 7, false, true, 100);
        changedRoom = new Room("Project Room 10", 7, false, true, 7);
        emptyRoom = new Room();
    }

    @Test
    public void testAddRoom() {
        when(roomRepository.save(room)).thenReturn(room);
        ResponseEntity<String> responseEntity = roomController.addNewRoom(room);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(room.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeRoomFail() {
        Optional<Room> optionalEmptyRoom = Optional.empty();
        when(roomRepository.findById(emptyRoom.getID())).thenReturn(optionalEmptyRoom);
        ResponseEntity<String> responseEntity = roomController.changeDetails(emptyRoom);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeRoomSuccess() {
        Optional<Room> optionalRoom = Optional.of(room);
        when(roomRepository.findById(room.getID())).thenReturn(optionalRoom);
        ResponseEntity<String> responseEntity = roomController.changeDetails(changedRoom);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedRoom.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteRoomFail() {
        Optional<Room> optionalEmptyRoom = Optional.empty();
        when(roomRepository.findById(emptyRoom.getID())).thenReturn(optionalEmptyRoom);
        ResponseEntity<String> responseEntity = roomController.deleteRoom(emptyRoom.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteRoomSuccess() {
        Optional<Room> optionalRoom = Optional.of(room);
        when(roomRepository.findById(room.getID())).thenReturn(optionalRoom);
        ResponseEntity<String> responseEntity = roomController.deleteRoom(room.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetRoomFail() {
        Optional<Room> optionalEmptyRoom = Optional.empty();
        when(roomRepository.findById(emptyRoom.getID())).thenReturn(optionalEmptyRoom);
        ResponseEntity<String> responseEntity = roomController.getRoom(emptyRoom.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetRoomSuccess() {
        Optional<Room> optionalRoom = Optional.of(room);
        when(roomRepository.findById(room.getID())).thenReturn(optionalRoom);
        ResponseEntity<String> responseEntity = roomController.getRoom(room.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(room.toString(), responseEntity.getBody());
    }

    @Test
    public void testGetBuildingNameSuccess() {
        when(roomRepository.getBuildingName(room.getBuildingID())).thenReturn("EWI");
        ResponseEntity<String> responseEntity = roomController.getBuildingName(room.getBuildingID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("EWI", responseEntity.getBody());
    }

}
