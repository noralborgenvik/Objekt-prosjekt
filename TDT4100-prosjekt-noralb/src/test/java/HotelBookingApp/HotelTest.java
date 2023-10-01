package HotelBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hotellbooking.Core.Booking;
import hotellbooking.Core.Hotel;
import hotellbooking.Core.PersonBooking;
import hotellbooking.Core.PersonInBooking;
import hotellbooking.Core.Room;

public class HotelTest {
    @Test
    public void testBookingCreation() {
        List<Booking> bookings = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(1, 101, false);
        Room room3 = new Room(4,103,false);
        rooms.add(room1);
        rooms.add(room3);
        Hotel hotel = new Hotel("Hotel Litago", bookings, rooms);


        // Test creating a booking with one person
        PersonBooking personBooking1 = new PersonBooking("Alice", 47, 23454345);
        Booking booking1 = new Booking(personBooking1, hotel);
        assertNotNull(booking1);
        assertEquals(personBooking1, booking1.getPersonBooking());
        assertEquals(1, booking1.getPersonList().size());
        assertEquals(room1, booking1.getRoom());


        // Test creating a booking with three people
        PersonBooking personBooking2 = new PersonBooking("Bob", 24,83746374);
        PersonInBooking person1 = new PersonInBooking("Carol", 13);
        PersonInBooking person2 = new PersonInBooking("Dave", 15);
        Booking booking2 = new Booking(personBooking2, hotel, person1, person2);
        assertNotNull(booking2);
        assertEquals(personBooking2, booking2.getPersonBooking());
        assertEquals(3, booking2.getPersonList().size());
        assertEquals(room3, booking2.getRoom());


        // Test creating a booking with too many people
        try{
        PersonInBooking person3 = new PersonInBooking("Eve", 16);
        PersonInBooking person4 = new PersonInBooking("Peter",19);
        Booking booking3 = new Booking(personBooking2, hotel, person1, person2, person3, person4);
        fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
        assertEquals("Det er for mange peroner i bookingen", e.getMessage());


        // Test no avaliable rooms
        try{
        PersonBooking personBooking3 = new PersonBooking("Bob", 24,94848583);
        PersonInBooking person5 = new PersonInBooking("Carol", 13);
        PersonInBooking person6 = new PersonInBooking("Dave", 15);
        Booking booking3 = new Booking(personBooking2, hotel, person5, person6);
        fail("Excpected IllegalStateExeption was not thrown");
        } catch (IllegalStateException e2){
            assertEquals("Ingen ledige rom", e2.getMessage());
        }
    }
    }
        
}
