package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    /**
     * Searches for Rooms associated with the buildingID specified.
     *
     * @param buildingID The ID of the Building
     * @return Collection of Rooms associated with the buildingID
     */
    @Query(
        value = "SELECT * FROM room r WHERE r.buildingID = ?1",
        nativeQuery = true)
    Collection<Room> findByBuilding(int buildingID);

    /**
     * Gets the Building's name from a Room's buildingID.
     *
     * @param buildingID The ID of the Building
     * @return String containing the name of the Building
     */
    @Query(
        value = "SELECT b.name FROM building b WHERE b.id = ?1",
        nativeQuery = true)
    String getBuildingName(int buildingID);

}
