package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.BikeReservation;

import org.springframework.data.repository.CrudRepository;

public interface BikeReservationRepository extends CrudRepository<BikeReservation, Integer> {

    /**
     * Searches for BikeReservation by netID in the database.
     *
     * @param netID The netID of the user
     * @return Collection of BikeReservations made by this User
     */
    Collection<BikeReservation> findByNetID(String netID);

}
