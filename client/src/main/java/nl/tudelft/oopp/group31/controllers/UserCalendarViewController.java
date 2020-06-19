package nl.tudelft.oopp.group31.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import nl.tudelft.oopp.group31.communication.ServerCommunication;
import nl.tudelft.oopp.group31.entities.CalendarEntry;
import nl.tudelft.oopp.group31.entities.TimeSlot;
import nl.tudelft.oopp.group31.entities.UserCalendarEntry;

public class UserCalendarViewController extends UserHelperController {

    @FXML
    private Button button;

    @FXML
    private Button save;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private ImageView image;

    @FXML
    private GridPane mainCalendar;

    private int yearChosen = 0;

    String monthOfYear = "";

    @FXML
    private ChoiceBox<String> monthList = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Integer> yearList = new ChoiceBox<>();

    ArrayList<CalendarEntry> calendarEntries = new ArrayList<>(42);

    private ArrayList<TimeSlot> resArray = null;

    public static ArrayList<TimeSlot> resArrayToReturn = null;

    private ArrayList<UserCalendarEntry> userCalendarArray = null;

    private static LocalDate localDate = LocalDate.now();

    HashMap<String, Integer> monthMap = new HashMap<>();

    HashMap<String, String> reservationMap = new HashMap<>();

    HashMap<String, String> calendarHash = new HashMap<>();

    static List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December");

    static String chosenDate = "0000-00-00";

    /**
     * Method initializes the scene. It adds the months to the month list and
     * calculates the next 10 years and adds to years list.
     */
    @FXML
    @Override
    public void initialize() {
        super.initialize();
        rootPane.setLeft(hamburgerMenu);
        initMonthMap(monthMap);
        initChoiceBoxes();
        String json = ServerCommunication.getReservationsMadeByUser(LoginSceneController.getUserName());
        Gson gson = new Gson();
        Type reservationListType = new TypeToken<ArrayList<TimeSlot>>() {
        }.getType();
        resArray = gson.fromJson(json, reservationListType);
        initReservationHash(reservationMap, resArray, 0);
        monthOfYear = "Nothing";
        json = ServerCommunication.getUserCalendar(LoginSceneController.getUserName());
        Type calendarListType = new TypeToken<ArrayList<UserCalendarEntry>>() {
        }.getType();
        userCalendarArray = gson.fromJson(json, calendarListType);
        try {
            initCalendarEntryMap(userCalendarArray, calendarHash);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Calls button clicked so on initial start update does not need to be clicked to show calendar.
        buttonClicked();
    }

    /**
     * changes to reserve room scene.
     *
     * @param event right click
     * @throws IOException exception
     */
    @FXML
    void roomReservation(ActionEvent event) throws IOException {
        main.sceneChange(event, "/UserOverviewBuildings.fxml");
    }

    /**
     * changes to reserve bike scene.
     *
     * @param event right click
     * @throws IOException exception
     */
    @FXML
    void bikeReservation(ActionEvent event) throws IOException {
        main.sceneChange(event, "/BikeBuildingSelection.fxml");
    }

    /**
     * changes value of the variable monthChosen with the month selected by user.
     *
     * @param event right click
     */
    @SuppressWarnings("unchecked")
    @FXML
    void monthChosen(ActionEvent event) {
        monthOfYear = (((ChoiceBox<String>) event.getSource()).getSelectionModel().getSelectedItem());
    }

    /**
     * changes value of the variable yearChosen with the year selected by user.
     *
     * @param event right click
     */
    @SuppressWarnings("unchecked")
    @FXML
    void yearChosen(ActionEvent event) {
        yearChosen = ((ChoiceBox<Integer>) event.getSource()).getSelectionModel().getSelectedItem();
    }

    /**
     * initializes the hashMap of months with the key as the position in the year.
     */
    public static void initMonthMap(HashMap<String, Integer> monthMap) {
        for (int i = 0; i <= 11; i++) {
            monthMap.put(months.get(i), i + 1);
        }
    }

    /**
     * initializes all the choice-boxes, the month choosing and the year choosing.
     */
    public void initChoiceBoxes() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        for (String month : months) {
            monthList.getItems().add(month);
        }
        for (int i = 0; i <= 10; i++) {
            yearList.getItems().add(year + i);
        }
    }

    /**
     * Test version of previous method that doesnt use the FXML attributes and
     * istead uses other ones.
     */
    public static void initChoiceBoxesTestMethod(ChoiceBox<String> monthList, ChoiceBox<Integer> yearList) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        for (String month : months) {
            monthList.getItems().add(month);
        }
        for (int i = 0; i <= 10; i++) {
            yearList.getItems().add(year + i);
        }
    }

    /**
     * makes a hashMap of the reservations returned by the server-communication and
     * associates it with the reservation date as the key.
     */
    public static void initReservationHash(HashMap<String, String> reservationMap, ArrayList<TimeSlot> resArray,
                                           int usage) {
        String text;
        String roomName;
        for (TimeSlot res : resArray) {
            String openingHour = Integer.toString(res.getStartTime());
            openingHour = openingHour.length() == 4 ? openingHour.substring(0, 2) : "0" + openingHour.substring(0, 1);
            String openingMin = String.format("%04d", res.getStartTime());
            openingMin = openingMin.substring(2,4);
            String closingHour = Integer.toString(res.getEndTime());
            closingHour = closingHour.length() == 4 ? closingHour.substring(0, 2) : "0" + closingHour.substring(0, 1);
            String closingMin = String.format("%04d",res.getEndTime());
            closingMin = closingMin.substring(2,4);
            if (usage == 0) {
                roomName = res.getRoomName();
            } else {
                roomName = res.getRoomID();
            }
            text = "\n You have a reservation at " + openingHour + ":" + openingMin + "\n until " + closingHour + ":"
                    + closingMin + " at " + roomName;
            reservationMap.put(res.getDate(), text);
        }
    }

    /**
     * Takes all the calendar entries and makes it a hash map.
     *
     * @throws ParseException if the string cannot be parsed.
     */
    public static void initCalendarEntryMap(ArrayList<UserCalendarEntry> userCalendarArray,
                                            HashMap<String, String> calendarHash) throws ParseException {
        for (UserCalendarEntry res : userCalendarArray) {
            if (res.getMonth() < localDate.getMonthValue()) {
                ServerCommunication.deleteUserCalendar(res.getId());
            } else {
                calendarHash.put(res.getDate(), res.getEvent());
            }
        }
    }

    /**
     * A class that gives logic as to calculate the number of days in a month.
     */
    public static int setDates(int month, int year) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Calculates the number equivalent of a month.
     *
     * @param month name of the month.
     * @return the position of the month in the year.
     */
    public static int getMonth(String month, HashMap<String, Integer> hashMap) {
        return hashMap.get(month);

    }

    /**
     * Calculates what month and year it is based on the current date at time of
     * calling the function.
     *
     * @return the month and the year as an array
     */
    public static int[] calculateMonth(int yearChosen, String monthOfYear, HashMap<String, Integer> monthMap) {
        int year;
        int month;
        if (yearChosen == 0) {
            year = localDate.getYear();
        } else {
            year = yearChosen;
        }
        if (monthOfYear.contains("Nothing")) {
            month = localDate.getMonthValue();
        } else {
            month = getMonth(monthOfYear, monthMap);
        }
        int[] monthYear = new int[2];
        monthYear[0] = month;
        monthYear[1] = year;
        return monthYear;
    }

    /**
     * Calls buttonClicked().
     *
     * @param event that the button is clicked.
     */
    @FXML
    void buttonClicked(ActionEvent event) {
        buttonClicked();
    }


    /**
     * On clicking button update, it will update the scene with the information of
     * that month available to user.
     */
    void buttonClicked() {
        int[] monthYear = calculateMonth(yearChosen, monthOfYear, monthMap);
        int year = monthYear[1];
        int month = monthYear[0];
        int maxDate = setDates(month, year);
        LocalDate inputDate = LocalDate.of(year, month, 1);
        int firstDay = inputDate.getDayOfWeek().getValue();
        String monthString;
        if (month < 10) {
            monthString = "" + 0 + month;
        } else {
            monthString = "" + month;
        }
        String currentYearMonth = year + "-" + monthString + "-";
        chosenDate = currentYearMonth;
        populateCalendar(firstDay, maxDate, currentYearMonth, calendarEntries, mainCalendar, reservationMap,
                calendarHash);
        save.setText("Save Changes");
        save.toFront();
    }

    /**
     * On clicking save, all info added and existing on calender sent to server.
     *
     * @param event the button is clicked.
     */
    @FXML
    void saveClicked(ActionEvent event) {
        String json;
        DecimalFormat df = new DecimalFormat("00");
        for (int i = 0; i <= 41; i++) {
            if (calendarEntries.get(i).isUsed() && (calendarEntries.get(i).getTextArea().getText().length() > 1)) {
                json = "{\"netID\":\"" + LoginSceneController.getUserName() + "\", \"date\":\"" + chosenDate
                        + df.format(i - 1) + "\", \"event\":\"" + calendarEntries.get(i).getTextArea().getText()
                        + "\"}";
                ServerCommunication.sendUserCalendar(json);
            }
        }
        save.setText("Saved!");

    }

    /**
     * Test method for save clicked without the server communication and private object usage to allow for testing.
     *
     * @param calendarEntries array of the entries.
     * @return string of json
     */
    static String saveClickedTestMethod(ArrayList<CalendarEntry> calendarEntries) {
        String json = null;
        DecimalFormat df = new DecimalFormat("00");
        for (int i = 0; i <= calendarEntries.size() - 1; i++) {
            if (calendarEntries.get(i).isUsed() && (calendarEntries.get(i).getTextArea().getText().length() > 1)) {
                json = "{\"netID\":\"" + "user" + "\", \"date\":\""
                        + chosenDate
                        + df.format(i - 1) + "\", \"event\":\"" + calendarEntries.get(i).getTextArea().getText()
                        + "\"}";
            }
        }
        return json;
    }

    /**
     * Adds labels and textarea to all the tables in the calendar. and then updates he text with reservations and
     * personal entries one may have.
     *
     * @param firstDay         First day of th emonth i.e. monday as 1 etc.
     * @param maxDate          Number of days in the month.
     * @param currentYearMonth Currecy year and month.
     * @param calendarEntries  Hashmap of the users calendar entries.
     * @param mainCalendar     The girdpane that makes up the calendar.
     * @param reservationMap   Hashmap of the rservations user has.
     * @param calendarHash     The hashMap of each cell in the gridpane.
     */
    public static void populateCalendar(int firstDay, int maxDate, String currentYearMonth,
                                        ArrayList<CalendarEntry> calendarEntries, GridPane mainCalendar, HashMap<String, String> reservationMap,
                                        HashMap<String, String> calendarHash) {
        //K is the count of position in the calendarentries array, and day is the count of what day it is.
        int k = 0;
        int day = 1;
        String date;
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 6; j++) {
                addCalendarEntry(mainCalendar, calendarEntries, i, j, k);
                CalendarEntry calendarEntry = calendarEntries.get(k);
                if ((k >= firstDay - 1) && (day <= maxDate)) {
                    Label lab = calendarEntry.getLabel();
                    lab.setStyle("-fx-text-fill: black;");
                    date = setLabelAndReturnDate(lab, day);
                    lab.toFront();
                    lab.setAlignment(Pos.TOP_CENTER);
                    lab.setPrefHeight(Double.MAX_VALUE);
                    calendarEntries.get(k).setUsed(true);
                    date = currentYearMonth + date;
                    addReservation(reservationMap, date, mainCalendar, calendarEntry, i, j);
                    if (calendarHash.get(date) != null) {
                        calendarEntry.getTextArea().setText(calendarHash.get(date));
                    }
                    day++;
                }
                k++;
            }
        }
    }


    /**
     * Sets the label with a date as text and a day appended with a 0 if needed.
     *
     * @param lab label
     * @param day day
     * @return string of day of date
     */
    public static String setLabelAndReturnDate(Label lab, int day) {
        String date;
        String str = " ";
        String str2 = "   ";
        if (day < 10) {
            lab.setText(str2 + day + " ");
            date = "" + 0 + day;
        } else {
            lab.setText(str + day + " ");
            date = "" + day;
        }
        return date;
    }

    /**
     * same as setLabelAndReturnDate without fxml.
     * 
     * @param day day
     * @return string of day of date
     */
    public static String setLabelAndReturnDateTestMethod(int day) {
        String date = "";
        if (day < 10) {
            date = "" + 0 + day;
        } else {
            date = "" + day;
        }
        return date;
    }

    /**
     * Adds the reservations onto the calendar enrties depnding on if the user had one for that day.
     *
     * @param reservationMap the hashmap of reservations
     * @param date           the date being checked.
     * @param mainCalendar   the gridpane
     * @param calendarEntry  specific calendarentry
     * @param i              row of gridpane
     * @param j              column of gridpane
     */
    public static void addReservation(HashMap<String, String> reservationMap, String date, GridPane mainCalendar,
                                      CalendarEntry calendarEntry, int i, int j) {
        if (reservationMap.get(date) != null) {
            Text text = new Text();
            text.setText(reservationMap.get(date));
            mainCalendar.add(text, j, i);
            calendarEntry.setUsed(true);
        }
    }

    /**
     * same as addReservation but having all fxml removed.
     * @param reservationMap the hashmap of reservations
     * @param date           the date being checked.
     * @param calendarEntry  specific calendarentry
     * @return               Sting with the text that would be set to the text label in fxml
     */
    public static String addReservationTestMethod(HashMap<String, String> reservationMap, String date,
            CalendarEntry calendarEntry) {
        String text = "";
        if (reservationMap.get(date) != null) {
            text = reservationMap.get(date);
            calendarEntry.setUsed(true);
        }
        return text;
    }

    /**
     * Addsa label and a textarea to the calendarentry of gridpane and adds the calendarentry to the array of calendarentries.
     *
     * @param mainCalendar    gridpane
     * @param calendarEntries array of calendarentries
     * @param i               row
     * @param j               column
     * @param k               position in calendarentries array
     */
    public static void addCalendarEntry(GridPane mainCalendar, ArrayList<CalendarEntry> calendarEntries, int i, int j, int k) {
        TextArea area = new TextArea();
        Label label = new Label();
        // j = column, i = row
        CalendarEntry entry = new CalendarEntry(area, label);
        mainCalendar.add(label, j, i);
        mainCalendar.add(area, j, i);
        calendarEntries.add(k, entry);
    }

    /**
     * Same as method addCalendarEntry, but fxml is removed completely.
     * 
     * @param calendarEntries array of calendarentries
     * @param k               position in calendarentries array
     */
    public static void addCalendarEntryTestMethod(ArrayList<CalendarEntry> calendarEntries, int k) {
        CalendarEntry entry = new CalendarEntry(null, null);
        calendarEntries.add(k, entry);
    }

}
