package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.Room;
import nl.tudelft.oopp.group31.repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Creates a new instance of Room and saves it in the database.
     *
     * @param r The room to be added into the database
     * @return A JSON representation of the added Room
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewRoom(@RequestBody Room r) {
        roomRepository.save(r);
        return new ResponseEntity<>(r.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of Room in the database.
     *
     * @param n A Room containing the changes to be made
     * @return A JSON representation of the changed Room
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody Room n) {
        Room r = roomRepository.findById(n.getID()).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of Room in the database.
     *
     * @param id The id of the Room to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteRoom(@RequestParam int id) {
        Room r = roomRepository.findById(id).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomRepository.delete(r);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of Room in the database.
     *
     * @return An Iterable Object containing all the instances of Room
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Returns the requested Room instance from the database.
     *
     * @param id The id of the requested Room
     * @return A JSON representation of the Room
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getRoom(@RequestParam int id) {
        Room r = roomRepository.findById(id).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(r.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all Rooms with a specific buildingID.
     *
     * @param buildingID The id of the Building
     * @return A Collection containing all the Rooms of the Building
     */
    @GetMapping(path = "/building")
    public @ResponseBody Collection<Room> getRoomsByBuilding(@RequestParam int buildingID) {
        return roomRepository.findByBuilding(buildingID);
    }

    /**
     * Returns name of the Building associated to a Room.
     *
     * @param buildingID The id of the Building
     * @return A String containing the name of the associated Building
     */
    @GetMapping(path = "/buildingname")
    public ResponseEntity<String> getBuildingName(@RequestParam int buildingID) {
        return new ResponseEntity<>(roomRepository.getBuildingName(buildingID), HttpStatus.OK);
    }

}
