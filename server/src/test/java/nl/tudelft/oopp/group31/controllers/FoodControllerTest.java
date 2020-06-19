package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Optional;

import nl.tudelft.oopp.group31.entities.Food;
import nl.tudelft.oopp.group31.repositories.FoodRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FoodControllerTest {

    @InjectMocks
    private FoodController foodController;

    @Mock
    private FoodRepository foodRepository;

    private Food food;
    private Food changedFood;
    private Food emptyFood;

    /**
     * Sets up the Foods used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        food = new Food("Kebab", 5, 2.99, "Kebab is nice.");
        changedFood = new Food("Fried noodles", 5, 3.99, "Fried noodles are better.");
        emptyFood = new Food();
    }

    @Test
    public void testAddFood() {
        when(foodRepository.save(food)).thenReturn(food);
        ResponseEntity<String> responseEntity = foodController.addNewFood(food);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(food.toString(), responseEntity.getBody());
    }

    @Test
    public void testChangeFoodFail() {
        Optional<Food> optionalEmptyFood = Optional.empty();
        when(foodRepository.findById(emptyFood.getID())).thenReturn(optionalEmptyFood);
        ResponseEntity<String> responseEntity = foodController.changeDetails(emptyFood);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeFoodSuccess() {
        Optional<Food> optionalFood = Optional.of(food);
        when(foodRepository.findById(food.getID())).thenReturn(optionalFood);
        ResponseEntity<String> responseEntity = foodController.changeDetails(changedFood);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedFood.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteFoodFail() {
        Optional<Food> optionalEmptyFood = Optional.empty();
        when(foodRepository.findById(emptyFood.getID())).thenReturn(optionalEmptyFood);
        ResponseEntity<String> responseEntity = foodController.deleteFood(emptyFood.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteFoodSuccess() {
        Optional<Food> optionalFood = Optional.of(food);
        when(foodRepository.findById(food.getID())).thenReturn(optionalFood);
        ResponseEntity<String> responseEntity = foodController.deleteFood(food.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetFoodFail() {
        Optional<Food> optionalEmptyFood = Optional.empty();
        when(foodRepository.findById(emptyFood.getID())).thenReturn(optionalEmptyFood);
        ResponseEntity<String> responseEntity = foodController.getFood(emptyFood.getID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetFoodSuccess() {
        Optional<Food> optionalFood = Optional.of(food);
        when(foodRepository.findById(food.getID())).thenReturn(optionalFood);
        ResponseEntity<String> responseEntity = foodController.getFood(food.getID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(food.toString(), responseEntity.getBody());
    }

}
