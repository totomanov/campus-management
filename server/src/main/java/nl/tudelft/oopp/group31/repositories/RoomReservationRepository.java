package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.RoomReservation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomReservationRepository extends CrudRepository<RoomReservation, Integer> {

    /**
     * Searches for all RoomReservations made by a specific User.
     *
     * @param netID The netID of the user
     * @return Collection containing all RoomReservations made by specified User
     */
    Collection<RoomReservation> findByNetID(String netID);

    /**
     * Creates a Collection of RoomReservations associated with the given date and roomID.
     *
     * @param date The desired date
     * @param roomID The id of the room
     * @return Collection of RoomReservations on the specified date and for the specified roomID
     */
    @Query(
            value = "SELECT * FROM room_reservation r WHERE r.date = ?1 AND r.roomID = ?2",
            nativeQuery = true)
    Collection<RoomReservation> findTakenTimeSlots(String date, Integer roomID);

}
