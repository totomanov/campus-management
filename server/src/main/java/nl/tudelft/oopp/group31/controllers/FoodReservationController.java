package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.FoodReservation;
import nl.tudelft.oopp.group31.repositories.FoodReservationRepository;

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
@RequestMapping(path = "/foodreservation")
public class FoodReservationController {

    @Autowired
    private FoodReservationRepository foodReservationRepository;

    /**
     * Creates a new instance of FoodReservation and saves it in the database.
     *
     * @param f The FoodReservation to be added into the database
     * @return A JSON representation of the added FoodReservation
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewFoodReservation(@RequestBody FoodReservation f) {
        foodReservationRepository.save(f);
        return new ResponseEntity<>(f.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of FoodReservation in the database.
     *
     * @param n A FoodReservation containing the changes to be made
     * @return A JSON representation of the changed FoodReservation
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody FoodReservation n) {
        FoodReservation f = foodReservationRepository.findById(n.getID()).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            foodReservationRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of a FoodReservation in the database.
     *
     * @param id The id of the FoodReservation to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteFoodReservation(@RequestParam int id) {
        FoodReservation f = foodReservationRepository.findById(id).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of FoodReservation in the database.
     *
     * @return An Iterable Object containing all the instances of FoodReservation
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<FoodReservation> getAllFoodReservations() {
        return foodReservationRepository.findAll();
    }

    /**
     * Returns the requested FoodReservation instance from the database.
     *
     * @param id The id of the requested FoodReservation
     * @return A JSON representation of the FoodReservation
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getFoodReservation(@RequestParam int id) {
        FoodReservation f = foodReservationRepository.findById(id).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(f.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all FoodReservations made by a certain User.
     *
     * @param netID The netID of the User
     * @return A Collection containing all the FoodReservations made by the User
     */
    @GetMapping(path = "/user")
    public @ResponseBody Collection<FoodReservation> getFoodReservations(@RequestParam String netID) {
        return foodReservationRepository.findByNetID(netID);
    }

}

