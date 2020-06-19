package nl.tudelft.oopp.group31.controllers;

import java.util.Base64;
import java.util.Optional;

import nl.tudelft.oopp.group31.entities.User;
import nl.tudelft.oopp.group31.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new instance of User and saves it in the database.
     *
     * @param n The User to be added into the database
     * @return A JSON representation of the added User
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewUser(@RequestBody User n) {
        n.setPassword(passwordEncoder.encode(n.getPassword()));
        User u = userRepository.findByNetID(n.getNetID()).orElse(null);

        if (u != null) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        } else {
            userRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.CREATED);
        }
    }

    /**
     * Changes the details of an instance of User in the database.
     *
     * @param n A User containing the changes to be made
     * @return A JSON representation of the changed User
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody User n) {
        User u = userRepository.findByNetID(n.getNetID()).orElse(null);

        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of a User in the database.
     *
     * @param netID The netID of the User to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteUser(@RequestParam String netID) {
        User u = userRepository.findByNetID(netID).orElse(null);

        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userRepository.delete(u);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of User in the database.
     *
     * @return An Iterable Object containing all the instances of User
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns the requested User instance from the database.
     *
     * @param netID The netID of the User
     * @return A JSON representation of the User
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getUser(@RequestParam String netID) {
        User u = userRepository.findByNetID(netID).orElse(null);

        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(u.toString(), HttpStatus.OK);
        }
    }

    /**
     * Sets the rentedBike boolean for User to true/false depending on if the User is renting a bike.
     *
     * @param netID The netID of the User to set the status for
     * @param rentedBike Boolean based on whether the User is renting a bike
     * @return A JSON representation of the User with the changed rentedBike status
     */
    @GetMapping(path = "/rent", produces = "application/json")
    public ResponseEntity<String> rentBike(@RequestParam String netID,
                                           @RequestParam boolean rentedBike) {
        User u = userRepository.findByNetID(netID).orElse(null);

        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (u.getRentedBike() && rentedBike) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            u.rentBike(rentedBike);
            userRepository.save(u);
            return new ResponseEntity<>(u.toString(), HttpStatus.OK);
        }
    }

    /**
     * Logs in a User if the User is present in the database.
     *
     * @param basicAuth HTTP Basic Authorization field
     * @return A JSON representation of the User
     */
    @GetMapping(path = "/login", produces = "application/json")
    public @ResponseBody ResponseEntity<String> login(@RequestHeader("Authorization") String basicAuth) {
        String base64 = new StringBuilder(basicAuth).delete(0, 6).toString();
        byte[] userData = Base64.getDecoder().decode(base64);
        String netid = new String(userData).split(":")[0];
        Optional<User> user = userRepository.findByNetID(netid);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
