package nl.tudelft.oopp.group31.repositories;

import java.util.Collection;

import nl.tudelft.oopp.group31.entities.Food;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {

    /**
     * Returns a Collection of Food items associated with the specified Building.
     *
     * @param buildingID The ID of the Building
     * @return Collection of Food items associated with the buildingID
     */
    @Query(
        value = "SELECT * FROM food f WHERE f.buildingID = ?1",
        nativeQuery = true)
    Collection<Food> getBuildingFoods(int buildingID);
}
