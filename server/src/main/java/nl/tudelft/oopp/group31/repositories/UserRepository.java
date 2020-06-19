package nl.tudelft.oopp.group31.repositories;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Searches for the User which has the specified netID.
     *
     * @param netID The netID of the user
     * @return Optional contains the wanted User if it exists
     */
    Optional<User> findByNetID(String netID);

}
