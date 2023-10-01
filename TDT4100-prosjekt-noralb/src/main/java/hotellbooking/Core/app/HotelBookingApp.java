package hotellbooking.Core.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelBookingApp extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("HotelBooking app");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/hotelBooking/App.fxml"))));
        primaryStage.show();
    }
}
