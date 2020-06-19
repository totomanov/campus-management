package nl.tudelft.oopp.group31.controllers;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.CalendarEntry;
import nl.tudelft.oopp.group31.repositories.CalendarEntryRepository;

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
@RequestMapping(path = "/calendarentry")
public class CalendarEntryController {

    @Autowired
    private CalendarEntryRepository calendarEntryRepository;

    /**
     * Creates a new instance of CalendarEntry and saves it in the database.
     *
     * @param c The CalendarEntry to be added into the database
     * @return A JSON representation of the added CalendarEntry
     */
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addNewCalendarEntry(@RequestBody CalendarEntry c) {
        calendarEntryRepository.save(c);
        return new ResponseEntity<>(c.toString(), HttpStatus.CREATED);
    }

    /**
     * Changes the details of an instance of CalendarEntry in the database.
     *
     * @param n A CalendarEntry containing the changes to be made
     * @return A JSON representation of the changed CalendarEntry
     */
    @PostMapping(path = "/change", produces = "application/json")
    public ResponseEntity<String> changeDetails(@RequestBody CalendarEntry n) {
        CalendarEntry c = calendarEntryRepository.findById(n.getID()).orElse(null);

        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            calendarEntryRepository.save(n);
            return new ResponseEntity<>(n.toString(), HttpStatus.OK);
        }
    }

    /**
     * Deletes an instance of CalendarEntry in the database.
     *
     * @param id The id of the CalendarEntry to be deleted
     * @return ResponseEntity with HTTP Status set to NOT_FOUND/OK depending on result
     */
    @GetMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteCalendarEntry(@RequestParam int id) {
        CalendarEntry c = calendarEntryRepository.findById(id).orElse(null);

        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            calendarEntryRepository.delete(c);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Finds all instances of CalendarEntry in the database.
     *
     * @return An Iterable Object containing all the instances of CalendarEntry
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<CalendarEntry> getAllCalendarEntries() {
        return calendarEntryRepository.findAll();
    }

    /**
     * Returns the requested CalendarEntry instance from the database.
     *
     * @param id The id of the requested CalendarEntry
     * @return A JSON representation of the CalendarEntry
     */
    @GetMapping(path = "/get", produces = "application/json")
    public ResponseEntity<String> getCalendarEntry(@RequestParam int id) {
        CalendarEntry c = calendarEntryRepository.findById(id).orElse(null);

        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(c.toString(), HttpStatus.OK);
        }
    }

    /**
     * Returns all CalendarEntries with a specific netID.
     *
     * @param netID The netID of the CalendarEntries
     * @return A Collection containing all the CalendarEntries made by the User
     */
    @GetMapping(path = "/user")
    public @ResponseBody Collection<CalendarEntry> getUserCalendarEntities(@RequestParam String netID) {
        return calendarEntryRepository.findByNetID(netID);
    }
}
