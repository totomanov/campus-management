package nl.tudelft.oopp.group31.controllers;

import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.repositories.BuildingRepository;

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
@RequestMapping(path = "/building")
public class BuildingController {

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * Creates a new instance of Building and saves it in the database.
     *
     * @param b The Building to be added into the database
     * @return A JSON representation of the added Building
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewBuilding(@RequestBody Building b) {
        buildingRepository.save(b);
        return new ResponseEntity<>(b.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of Building in the database.
     *
     * @param n A Building containing the changes to be made
     * @return A JSON representation of the changed Building
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody Building n) {
        Building b = buildingRepository.findById(n.getID()).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            buildingRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of Building in the database.
     *
     * @param id The id of the Building to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteBuilding(@RequestParam int id) {
        Building b = buildingRepository.findById(id).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            buildingRepository.delete(b);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of Building in the database.
     *
     * @return An Iterable Object containing all the instances of Building
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    /**
     * Returns the requested Building instance from the database.
     *
     * @param id The id of the requested Building
     * @return A JSON representation of the Building
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getBuilding(@RequestParam int id) {
        Building b = buildingRepository.findById(id).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(b.toString(), HttpStatus.OK);
        }
    }

    /**
     * Rents the number of bikes supplied, deducts/adds them from available bikes of the Building.
     *
     * @param id The id of the Building to rent a bike from
     * @param numberOfBikes Number of bikes to rent (negative for bikes that are returned)
     * @return A JSON representation of the Building with new number of bikes available to rent
     */
    @GetMapping(path = "/rent", produces = "application/json")
    public ResponseEntity<String> rentBike(@RequestParam int id,
                                           @RequestParam int numberOfBikes) {
        Building b = buildingRepository.findById(id).orElse(null);

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (b.getBikeRental() - numberOfBikes < 0) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            b.rentBike(numberOfBikes);
            buildingRepository.save(b);
            return new ResponseEntity<>(b.toString(), HttpStatus.OK);
        }
    }

}
