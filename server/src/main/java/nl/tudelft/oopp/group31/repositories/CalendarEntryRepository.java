package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.CalendarEntry;

import org.springframework.data.repository.CrudRepository;

public interface CalendarEntryRepository extends CrudRepository<CalendarEntry, Integer> {

    /**
     * Searches for all CalendarEntries in the database by netID.
     *
     * @param netID The netID of the user
     * @return Collection of CalendarEntries made by the specified User
     */
    Collection<CalendarEntry> findByNetID(String netID);

}
