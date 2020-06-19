package nl.tudelft.oopp.group31.entities;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CalendarEntry {

    TextArea text;
    Label label;
    Boolean used = false;

    public CalendarEntry(TextArea textArea, Label l) {
        text = textArea;
        label = l;
    }

    public TextArea getTextArea() {
        return text;
    }

    public Label getLabel() {
        return label;
    }

    public void setUsed(Boolean bool) {
        used = bool;
    }

    public boolean isUsed() {
        return used;
    }

}
