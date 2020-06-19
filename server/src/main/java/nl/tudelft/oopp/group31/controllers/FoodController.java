package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.Food;
import nl.tudelft.oopp.group31.repositories.FoodRepository;

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
@RequestMapping(path = "/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    /**
     * Creates a new instance of Food and saves it in the database.
     *
     * @param f The Food to be added into the database
     * @return A JSON representation of the added Food
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewFood(@RequestBody Food f) {
        foodRepository.save(f);
        return new ResponseEntity<>(f.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of Food in the database.
     *
     * @param n A Food containing the changes to be made
     * @return A JSON representation of the changed Food
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody Food n) {
        Food f = foodRepository.findById(n.getID()).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            foodRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of Food in the database.
     *
     * @param id The id of the Food to be deleted.
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete")
    public ResponseEntity<String> deleteFood(@RequestParam int id) {
        Food f = foodRepository.findById(id).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            foodRepository.delete(f);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of Food in the database.
     *
     * @return An Iterable Object containing all instances of Food
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    /**
     * Returns the requested Food instance from the database.
     *
     * @param id The id of the requested Food
     * @return A JSON representation of the Food
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getFood(@RequestParam int id) {
        Food f = foodRepository.findById(id).orElse(null);

        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(f.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all Foods with a specific buildingID.
     *
     * @param buildingID The id of the Building
     * @return A Collection containing all Foods of the Building
     */
    @GetMapping(path = "/building")
    public @ResponseBody Collection<Food> getBuildingFoods(@RequestParam int buildingID) {
        return foodRepository.getBuildingFoods(buildingID);
    }

}
