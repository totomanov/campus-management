package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.RoomReservation;
import nl.tudelft.oopp.group31.repositories.RoomReservationRepository;

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
@RequestMapping(path = "/reservation")
public class RoomReservationController {

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    /**
     * Creates a new instance of RoomReservation and saves it in the database.
     *
     * @param r The RoomReservation to be added into the database
     * @return A JSON representation of the added RoomReservation
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewRoomReservation(@RequestBody RoomReservation r) {
        roomReservationRepository.save(r);
        return new ResponseEntity<>(r.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of RoomReservation in the database.
     *
     * @param n A RoomReservation containing the changes to be made
     * @return A JSON representation of the changed RoomReservation
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody RoomReservation n) {
        RoomReservation r = roomReservationRepository.findById(n.getID()).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomReservationRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of a RoomReservation in the database.
     *
     * @param id The id of the RoomReservation to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteRoomReservation(@RequestParam int id) {
        RoomReservation r = roomReservationRepository.findById(id).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            roomReservationRepository.delete(r);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of RoomReservation in the database.
     *
     * @return An Iterable Object containing all instances of RoomReservation
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<RoomReservation> getAllReservations() {
        return roomReservationRepository.findAll();
    }

    /**
     * Returns the requested RoomReservation instance from the database.
     *
     * @param id The id of the requested RoomReservation
     * @return A JSON representation of the RoomReservation
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getRoomReservation(@RequestParam int id) {
        RoomReservation r = roomReservationRepository.findById(id).orElse(null);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(r.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all RoomReservations with a specific netID.
     *
     * @param netID The netID of the RoomReservation
     * @return A Collection containing all the RoomReservations made by the User
     */
    @GetMapping(path = "/user")
    public @ResponseBody Collection<RoomReservation> getRoomReservationsByNetID(@RequestParam String netID) {
        return roomReservationRepository.findByNetID(netID);
    }

    /**
     * Gets all RoomReservations with a specific date and roomID.
     *
     * @param date The date of the RoomReservation
     * @param roomID The id of the Room
     * @return A Collection containing all the RoomReservations of the Room on specified date
     */
    @GetMapping(path = "/taken")
    public @ResponseBody Collection<RoomReservation> getTakenTimeslots(@RequestParam String date,
                                                                       @RequestParam int roomID) {
        return roomReservationRepository.findTakenTimeSlots(date, roomID);
    }

    /**
     * Block a specific Room on the date specified.
     *
     * @param roomID The id of the Room to block
     * @param date The date to block RoomReservations on
     * @return A JSON representation of the RoomReservation that blocks the date
     */
    @PostMapping(path = "/block", produces = "application/json")
    public ResponseEntity<String> blockRoomReservation(@RequestParam int roomID,
                                                       @RequestParam String date) {
        RoomReservation r = new RoomReservation(null, date, roomID);
        roomReservationRepository.save(r);
        return new ResponseEntity<>(r.toString(), HttpStatus.OK);
    }
}
