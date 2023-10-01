package hotellbooking.Core.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hotellbooking.Core.Booking;
import hotellbooking.Core.BookingFileHandler;
import hotellbooking.Core.Hotel;
import hotellbooking.Core.PersonBooking;
import hotellbooking.Core.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HotelBookingController {

    //Hovedkontroller til appen 
    
    private Hotel hotel; 
    private PersonBooking person1;
    private ObservableList<String> bookeListe = FXCollections.observableArrayList("Ingen", "En person", "To personer","Tre personer");
    private static final String fil = "Bookings";
    private BookingFileHandler handler = new BookingFileHandler();
    
    @FXML private TextField navn;
    @FXML private TextField alder;
    @FXML private TextField telefonnummer;
    @FXML private ChoiceBox bookeForAndre;
    @FXML private Button fortsett;
    @FXML private Label utskrift;

    //Initialiserer appen og ppretter hotel+leser fra fil
    @FXML
    private void initialize(){
        try{
            bookeForAndre.setItems(bookeListe);

            List<Room> rooms = new ArrayList<>();
            List<Booking> bookings= new ArrayList<>();
            rooms.add(new Room(1, 101, false));
            rooms.add(new Room(1, 102, false));
            rooms.add(new Room(2, 103, false));
            rooms.add(new Room(2, 104, false));
            rooms.add(new Room(3, 105, false));
            rooms.add(new Room(3, 106, false));
            rooms.add(new Room(4, 107, false));
            rooms.add(new Room(4, 108, false));

            this.hotel = new Hotel("Paradise Hotel", bookings, rooms);

        } catch (Exception e){
            e.printStackTrace();
            this.handleCriticalError("Kunne ikke åpne appen");
            return;
        }

        try{
            handler.readBooking(HotelBookingController.fil, this.hotel);

        } catch (IOException e) {
            //e.printStackTrace();
            Alert alert = new Alert(AlertType.WARNING, "Kunne ikke lese bookinger");
            alert.show();
        }
        
    }
    
    //Hva som skjer når du trykker på fortsett
    @FXML
    public void startNewBooking(ActionEvent event) throws IOException{

        this.checkValidInput();

        String antallIBooking = (String) bookeForAndre.getValue();
        String text = alder.getText();
        int value = Integer.parseInt(text);
        int tlfnr = Integer.parseInt(telefonnummer.getText());
        this.person1 = new PersonBooking(navn.getText(),value, tlfnr);
        
        
        //Hvis personen ikke skal booke for andre, oppreter den en booking
        if (antallIBooking.equals("Ingen")){
            Booking booking = new Booking(person1, hotel);
            utskrift.setText(booking.toString());

            try {
                this.hotel.findAvaliableRoom(booking);
            }
            catch(IllegalStateException e){
                this.handleInvalidInput("Ingen ledige rom");
                return;
            }
            
            try{
            handler.writeOneBooking(HotelBookingController.fil, this.hotel, booking);
            } catch (IOException e) {
                e.printStackTrace();

                Alert alert = new Alert(AlertType.WARNING, "Kunne ikke lagre booking");
                alert.show();
            }
        }

        //Create a new stage for the second window
        else if (antallIBooking.equals("En person")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HotelBooking/secondWindow.fxml"));
            Parent root = loader.load();

            SecondWindowController secondWindowController = loader.getController();
            secondWindowController.setHotel(hotel);
            secondWindowController.setPersonBooking(person1);

            Stage secondStage = new Stage();
            secondStage.setTitle("Second Window");
            secondStage.setScene(new Scene(root));
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setOpacity(1);
            secondStage.showAndWait();
        }

        // Creates a stage for the third window
        else if (antallIBooking.equals("To personer")){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HotelBooking/thirdWindow.fxml"));
            Parent root = loader.load();

            ThirdWindowController thirdWindowController = loader.getController();
            thirdWindowController.setHotel(hotel);
            thirdWindowController.setPersonBooking(person1);

            Stage thirdStage = new Stage();
            thirdStage.setTitle("Third Window");
            thirdStage.setScene(new Scene(root));
            thirdStage.initModality(Modality.APPLICATION_MODAL);
            thirdStage.setOpacity(1);
            thirdStage.showAndWait();

        }

        // Creates a stage for the fourth window
        else if (antallIBooking.equals("Tre personer")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HotelBooking/fourthWindow.fxml"));
            Parent root = loader.load();

            FourthWindowController fourthWindowController = loader.getController();
            fourthWindowController.setHotel(hotel);
            fourthWindowController.setPersonBooking(person1);

            Stage fourthStage = new Stage();
            fourthStage.setTitle("Fourth Window");
            fourthStage.setScene(new Scene(root));
            fourthStage.initModality(Modality.APPLICATION_MODAL);
            fourthStage.setOpacity(1);
            fourthStage.showAndWait();

        }

        
    }

    /**
     * Metoder for å håndtere feil
     */

    private void checkValidInput(){

        if (navn.getText().isEmpty()|| alder.getText().isEmpty() || telefonnummer.getText().isEmpty() 
            || bookeForAndre.getValue() == null){
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

        if (!this.isValidPhoneNumber(Integer.parseInt(telefonnummer.getText()))){
            this.handleInvalidInput("Ikke gyldig telefonnummer");
            return;
        }
    }

    //Feilhåndteringsmetoder
    private void handleCriticalError(String message){
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
        System.exit(1);
    }

    private void handleInvalidInput(String message){
        Alert alert = new Alert(AlertType.WARNING, message);
        alert.show();
    }



    /**
     * 
     * @param name
     * @return
     * Hjelpemetoder
     */

    public boolean isValidName(String name) {
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
        if (Integer.parseInt(alder.getText()) < 18){
            return false;
        }
        if (Integer.parseInt(alder.getText()) < 0){
            return false;
        }
        return true;
    }

    private boolean isValidPhoneNumber(int number){
        String numberString = String.valueOf(Integer.parseInt(telefonnummer.getText()));
        if (numberString.matches("\\d{8}") == false){
            return false;
        }
        if (numberString.matches("\\d{8}") == true){
            return true;
        }
        return true;
    }



}
