package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.FoodReservation;

import org.springframework.data.repository.CrudRepository;


public interface FoodReservationRepository extends CrudRepository<FoodReservation, Integer> {

    /**
     * Searches for FoodReservations made by specified User.
     *
     * @param netID The netID of the user
     * @return Collection of FoodReservations made by User
     */
    Collection<FoodReservation> findByNetID(String netID);

}
