package hotellbooking.Core.app;

import java.io.IOException;

import hotellbooking.Core.Booking;
import hotellbooking.Core.BookingFileHandler;
import hotellbooking.Core.Hotel;
import hotellbooking.Core.PersonBooking;
import hotellbooking.Core.PersonInBooking;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ThirdWindowController {
    
    //Kontroller dersom du skal bestille for to andre

    private Hotel hotel;
    private PersonBooking personBooking;
    private static final String fil = "Bookings";
    private BookingFileHandler handler = new BookingFileHandler();

    @FXML private TextField navn1;
    @FXML private TextField alder1;
    @FXML private TextField navn2;
    @FXML private TextField alder2;
    @FXML private Button bestill;
    @FXML private Label bestilling;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setPersonBooking(PersonBooking personBooking) {
        this.personBooking = personBooking;
    }

    @FXML 
    public void makeBooking(){

        this.checkValidInput();

        String text1 = alder1.getText();
        int value1 = Integer.parseInt(text1);
        PersonInBooking person1 = new PersonInBooking(navn1.getText(), value1);

        String text2 = alder2.getText();
        int value2 = Integer.parseInt(text2);
        PersonInBooking person2 = new PersonInBooking(navn2.getText(), value2);

        Booking booking = new Booking(personBooking, hotel, person1,person2);
        bestilling.setText(booking.toString());

        try {
            this.hotel.findAvaliableRoom(booking);
        }catch(IllegalStateException e){
            this.handleInvalidInput("Ingen ledige rom");
            return;
        }

        try{
            handler.writeOneBooking(ThirdWindowController.fil, this.hotel,booking);
            } catch (IOException e) {
                e.printStackTrace();

                Alert alert = new Alert(AlertType.WARNING, "Kunne lagre booking");
                alert.show();
            }
    }
    

    private void checkValidInput(){

        if (navn1.getText().isEmpty()|| alder1.getText().isEmpty() || navn2.getText().isEmpty()|| alder2.getText().isEmpty()){
                this.handleInvalidInput("Du må fylle ut alle feltene");
                return;
            }

        if (!isValidName(navn1.getText()) || !isValidName(navn2.getText())){
            this.handleInvalidInput("Ikke gyldig navn");
            return;
        }

        if(!isValidAge(Integer.parseInt(alder1.getText())) || !isValidAge(Integer.parseInt(alder2.getText()))){
            this.handleInvalidInput("Ikke gyldig alder");
            return;
        }

    }


    private void handleInvalidInput(String message){
        Alert alert = new Alert(AlertType.WARNING, message);
        alert.show();
    }

    private boolean isValidName(String name) {
        if (name.contains(" ")){
            String fornavn = name.substring(0, name.indexOf(" "));
            String etternavn = name.substring(name.indexOf(" ") + 1);

            char[] chars1 = fornavn.toCharArray();
            char[] chars2 = etternavn.toCharArray();

            for (char c : chars1) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }

            for (char c : chars2) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
            return true;

        }
        char[] chars = name.toCharArray();
    
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
    
        return true;
    }

    private boolean isValidAge(int age){
        if (age < 0){
            return false;
        }
        return true;
    }





}
