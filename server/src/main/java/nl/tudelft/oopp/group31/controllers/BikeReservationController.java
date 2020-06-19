package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.BikeReservation;
import nl.tudelft.oopp.group31.repositories.BikeReservationRepository;

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
@RequestMapping(path = "/bikereservation")
public class BikeReservationController {

    @Autowired
    private BikeReservationRepository bikeReservationRepository;

    /**
     * Creates a new instance of BikeReservation and saves it in the database.
     *
     * @param b The BikeReservation to be added into the database
     * @return A JSON representation of the added BikeReservation
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewBikeReservation(@RequestBody BikeReservation b) {
        bikeReservationRepository.save(b);
        return new ResponseEntity<>(b.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of BikeReservation in the database.
     *
     * @param n A BikeReservation containing the changes to be made
     * @return A JSON representation of the changed BikeReservation
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody BikeReservation n) {
        BikeReservation b = bikeReservationRepository.findById(n.getID()).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bikeReservationRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of a BikeReservation in the database.
     *
     * @param id The id of the BikeReservation to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteBikeReservation(@RequestParam int id) {
        BikeReservation b = bikeReservationRepository.findById(id).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bikeReservationRepository.delete(b);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of BikeReservation in the database.
     *
     * @return An Iterable Object containing all instances of BikeReservation
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<BikeReservation> getAllBikeReservations() {
        return bikeReservationRepository.findAll();
    }

    /**
     * Returns the requested BikeReservation instance from the database.
     *
     * @param id The id of the requested BikeReservation
     * @return A JSON representation of the BikeReservation
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getBikeReservation(@RequestParam int id) {
        BikeReservation b = bikeReservationRepository.findById(id).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(b.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all BikeReservations with a specific netID.
     *
     * @param netID The netID of the BikeReservation
     * @return A Collection containing all the BikeReservations made by the User
     */
    @GetMapping(path = "/user")
    public @ResponseBody Collection<BikeReservation> getBikeReservationsByNetID(@RequestParam String netID) {
        return bikeReservationRepository.findByNetID(netID);
    }
}
