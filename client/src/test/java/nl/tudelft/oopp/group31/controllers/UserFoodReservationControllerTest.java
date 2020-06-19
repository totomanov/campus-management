package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import nl.tudelft.oopp.group31.entities.Food;

import org.junit.jupiter.api.Test;

public class UserFoodReservationControllerTest {

    @Test
    void testValidateFormNullBuildingID() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(null, list, "2000", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please select a building.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormNullList() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, null, "2000", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please select a food item.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormWrongTime() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "200", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid hour.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormUnparsableTime() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "sss", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid hour.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormWrongHour() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "2600", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid hour.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormWrongMinute() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "2270", "2020-03-28");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid hour.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormNullDate() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "2200", null);
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please select a date.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateForm() {
        UserFoodReservationController cc = new UserFoodReservationController();
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Kebab", 15, 2.99, "Kebabs are various cooked meat dishes."));
        ArrayList<String> inputErrorMessages = cc.validateForm(2, list, "2200", "2020-03-28");
        assertEquals(0, inputErrorMessages.size());
    }

}
