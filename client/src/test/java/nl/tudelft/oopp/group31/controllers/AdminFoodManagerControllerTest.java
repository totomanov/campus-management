package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import nl.tudelft.oopp.group31.entities.Food;

import org.junit.jupiter.api.Test;

public class AdminFoodManagerControllerTest {

    @Test
    void testValidateFormNullID() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(null, "name", "2.00", "description");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please select a building.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormNullName() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, null, "2.00", "description");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid food name.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormEmptyName() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, "", "2.00", "description");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid food name.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateFormUnparsableDouble() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, "name", "sss", "description");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid price.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateNullDescription() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, "name", "2.00", null);
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid description.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateEmptyDescription() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, "name", "2.00", "");
        assertEquals(1, inputErrorMessages.size());
        assertEquals("Please enter a valid description.", inputErrorMessages.get(0));
    }

    @Test
    void testValidateForm() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateForm(2, "name", "2.00", "description");
        assertEquals(0, inputErrorMessages.size());
    }

    @Test
    void testRemoveValidateForm() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateRemoveForm(new Food());
        assertEquals(0, inputErrorMessages.size());
    }

    @Test
    void testRemoveValidateFormNullFood() {
        AdminFoodManagerController cc = new AdminFoodManagerController();
        ArrayList<String> inputErrorMessages = cc.validateRemoveForm(null);
        assertEquals(1, inputErrorMessages.size());
    }

}
