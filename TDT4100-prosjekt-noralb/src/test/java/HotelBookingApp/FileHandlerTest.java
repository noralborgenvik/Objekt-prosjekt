package HotelBookingApp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotellbooking.Core.Booking;
import hotellbooking.Core.BookingFileHandler;
import hotellbooking.Core.Hotel;
import hotellbooking.Core.PersonBooking;
import hotellbooking.Core.PersonInBooking;
import hotellbooking.Core.Room;

public class FileHandlerTest {
    
        private BookingFileHandler bookingFileHandler;
        private Hotel hotel;
        private String testFilePath = "Booking";

        @BeforeEach
        public void setUp() throws Exception {
            bookingFileHandler = new BookingFileHandler();

            List<Booking> bookings = new ArrayList<>();
            List<Room> rooms = new ArrayList<>();
            Room room1 = new Room(1, 101, false);
            Room room3 = new Room(4,103,false);
            rooms.add(room1);
            rooms.add(room3);

            hotel = new Hotel("Paradise Hotel", bookings, rooms);
            PersonBooking personBooking1 = new PersonBooking("John Doe", 30, 12345678);
            PersonInBooking personInBooking1 = new PersonInBooking("Jane Doe", 25);
            PersonInBooking personInBooking2 = new PersonInBooking("Alice Smith", 45);
            Booking booking1 = new Booking(personBooking1, hotel, personInBooking1, personInBooking2);
        }
        
        
    @Test
    public void testWriteBooking() throws IOException {

        bookingFileHandler.isTest();
        bookingFileHandler.writeBooking(testFilePath, hotel);
        
        String content = bookingFileHandler.getContent(testFilePath);

        assertTrue(content.contains("Booking:"));
        assertTrue(content.contains("Navn = John Doe"));
        assertTrue(content.contains("Alder = 30"));
        assertTrue(content.contains("Telefonnummer = 12345678"));
        assertTrue(content.contains("Antall personer = 3"));
        assertTrue(content.contains("Romnummer = 103"));

    }

}
