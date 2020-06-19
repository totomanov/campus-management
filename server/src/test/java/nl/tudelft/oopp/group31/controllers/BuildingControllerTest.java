package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.Building;
import nl.tudelft.oopp.group31.repositories.BuildingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BuildingControllerTest {

    @InjectMocks
    private BuildingController buildingController;

    @Mock
    private BuildingRepository buildingRepository;

    private Building building;
    private Building changedBuilding;
    private Building emptyBuilding;

    /**
     * Sets up the Buildings used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        building = new Building("EWI", "0900", "2100", "Faculty of Electrical Engineering, Mathematics and Computer Science", 75);
        changedBuilding = new Building("AE", "0900", "2100", "Faculty of Aerospace Engineering.", 150);
        emptyBuilding = new Building();
    }

    @Test
    public void testAddBuilding() {
        when(buildingRepository.save(building)).thenReturn(building);
        ResponseEntity<String> responseEntity = buildingController.addNewBuilding(building);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(building.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeBuildingFail() {
        Optional<Building> optionalEmptyBuilding = Optional.empty();
        when(buildingRepository.findById(emptyBuilding.getID())).thenReturn(optionalEmptyBuilding);
        ResponseEntity<String> responseEntity = buildingController.changeDetails(emptyBuilding);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeBuildingSuccess() {
        Optional<Building> optionalBuilding = Optional.of(building);
        when(buildingRepository.findById(building.getID())).thenReturn(optionalBuilding);
        ResponseEntity<String> responseEntity = buildingController.changeDetails(changedBuilding);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedBuilding.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteBuildingFail() {
        Optional<Building> optionalEmptyBuilding = Optional.empty();
        when(buildingRepository.findById(emptyBuilding.getID())).thenReturn(optionalEmptyBuilding);
        ResponseEntity<String> responseEntity = buildingController.deleteBuilding(emptyBuilding.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteBuildingSuccess() {
        Optional<Building> optionalBuilding = Optional.of(building);
        when(buildingRepository.findById(building.getID())).thenReturn(optionalBuilding);
        ResponseEntity<String> responseEntity = buildingController.deleteBuilding(building.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetBuildingFail() {
        Optional<Building> optionalEmptyBuilding = Optional.empty();
        when(buildingRepository.findById(emptyBuilding.getID())).thenReturn(optionalEmptyBuilding);
        ResponseEntity<String> responseEntity = buildingController.getBuilding(emptyBuilding.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetBuildingSuccess() {
        Optional<Building> optionalBuilding = Optional.of(building);
        when(buildingRepository.findById(building.getID())).thenReturn(optionalBuilding);
        ResponseEntity<String> responseEntity = buildingController.getBuilding(building.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(building.toString(), responseEntity.getBody());
    }

    @Test
    public void testRentBikeFailBuilding() {
        Optional<Building> optionalEmptyBuilding = Optional.empty();
        when(buildingRepository.findById(emptyBuilding.getID())).thenReturn(optionalEmptyBuilding);
        ResponseEntity<String> responseEntity = buildingController.rentBike(emptyBuilding.getID(), 1);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testRentBikeFailBike() {
        Optional<Building> optionalEmptyBuilding = Optional.of(emptyBuilding);
        when(buildingRepository.findById(emptyBuilding.getID())).thenReturn(optionalEmptyBuilding);
        ResponseEntity<String> responseEntity = buildingController.rentBike(emptyBuilding.getID(), 1);

        assertEquals(406, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testRentBikeSuccess() {
        Optional<Building> optionalBuilding = Optional.of(building);
        when(buildingRepository.findById(building.getID())).thenReturn(optionalBuilding);
        ResponseEntity<String> responseEntity = buildingController.rentBike(building.getID(), 1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(building.toString(), responseEntity.getBody());
        assertEquals(74, building.getBikeRental());
    }

    @Test
    public void testReturnBikeSuccess() {
        Optional<Building> optionalBuilding = Optional.of(building);
        when(buildingRepository.findById(building.getID())).thenReturn(optionalBuilding);
        ResponseEntity<String> responseEntity = buildingController.rentBike(building.getID(), -1);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(building.toString(), responseEntity.getBody());
        assertEquals(76, building.getBikeRental());
    }

}
