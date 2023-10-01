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

public class SecondWindowController {

    //Kontroller dersom du skal bestille for en annen person

    private Hotel hotel;
    private PersonBooking personBooking;
    private static final String fil = "Bookings";
    private BookingFileHandler handler = new BookingFileHandler();

    @FXML private TextField navn;
    @FXML private TextField alder;
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

        String text = alder.getText();
        int value = Integer.parseInt(text);
        PersonInBooking person1 = new PersonInBooking(navn.getText(), value);
        Booking booking = new Booking(personBooking, hotel, person1);
        bestilling.setText(booking.toString());

        try {
            this.hotel.findAvaliableRoom(booking);
        }
        catch(IllegalStateException e){
            this.handleInvalidInput("Ingen ledige rom");
            return;
        }

        try{
            handler.writeOneBooking(SecondWindowController.fil, this.hotel, booking);
            } catch (IOException e) {
                e.printStackTrace();

                Alert alert = new Alert(AlertType.WARNING, "Kunne lagre booking");
                alert.show();
            }
    }





    private void checkValidInput(){

        if (navn.getText().isEmpty()|| alder.getText().isEmpty()){
                this.handleInvalidInput("Du må fylle ut alle feltene");
                return;
            }

        if (!isValidName(navn.getText())){
            this.handleInvalidInput("Ikke gyldig navn");
            return;
        }

        if(!isValidAge(Integer.parseInt(alder.getText()))){
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
        if (Integer.parseInt(alder.getText()) < 0){
            return false;
        }
        return true;
    }


}
