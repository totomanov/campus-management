package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.CalendarEntry;
import nl.tudelft.oopp.group31.repositories.CalendarEntryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CalendarEntryControllerTest {

    @InjectMocks
    private CalendarEntryController calendarEntryController;

    @Mock
    private CalendarEntryRepository calendarEntryRepository;

    private CalendarEntry calendarEntry;
    private CalendarEntry changedCalendarEntry;
    private CalendarEntry emptyCalendarEntry;

    /**
     * Sets up the CalendarEntries used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        calendarEntry = new CalendarEntry("maikdevries", "2020-03-26", "Eating fried rice for dinner.");
        changedCalendarEntry = new CalendarEntry("peterpan", "2020-03-27", "Eating fried noodles for dinner.");
        emptyCalendarEntry = new CalendarEntry();
    }

    @Test
    public void testAddCalendarEntry() {
        when(calendarEntryRepository.save(calendarEntry)).thenReturn(calendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.addNewCalendarEntry(calendarEntry);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(calendarEntry.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeCalendarEntryFail() {
        Optional<CalendarEntry> optionalEmptyCalendarEntry = Optional.empty();
        when(calendarEntryRepository.findById(emptyCalendarEntry.getID())).thenReturn(optionalEmptyCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.changeDetails(emptyCalendarEntry);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeCalendarEntrySuccess() {
        Optional<CalendarEntry> optionalCalendarEntry = Optional.of(calendarEntry);
        when(calendarEntryRepository.findById(calendarEntry.getID())).thenReturn(optionalCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.changeDetails(changedCalendarEntry);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedCalendarEntry.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteCalendarEntryFail() {
        Optional<CalendarEntry> optionalEmptyCalendarEntry = Optional.empty();
        when(calendarEntryRepository.findById(emptyCalendarEntry.getID())).thenReturn(optionalEmptyCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.deleteCalendarEntry(emptyCalendarEntry.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteCalendarEntrySuccess() {
        Optional<CalendarEntry> optionalCalendarEntry = Optional.of(calendarEntry);
        when(calendarEntryRepository.findById(calendarEntry.getID())).thenReturn(optionalCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.deleteCalendarEntry(calendarEntry.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetCalendarEntryFail() {
        Optional<CalendarEntry> optionalEmptyCalendarEntry = Optional.empty();
        when(calendarEntryRepository.findById(emptyCalendarEntry.getID())).thenReturn(optionalEmptyCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.getCalendarEntry(emptyCalendarEntry.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetCalendarEntrySuccess() {
        Optional<CalendarEntry> optionalCalendarEntry = Optional.of(calendarEntry);
        when(calendarEntryRepository.findById(calendarEntry.getID())).thenReturn(optionalCalendarEntry);
        ResponseEntity<String> responseEntity = calendarEntryController.getCalendarEntry(calendarEntry.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(calendarEntry.toString(), responseEntity.getBody());
    }

}
