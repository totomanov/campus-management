package nl.tudelft.oopp.group31.controllers;

public class AdminHomepageController extends AdminHelperController {

    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
    }
}
