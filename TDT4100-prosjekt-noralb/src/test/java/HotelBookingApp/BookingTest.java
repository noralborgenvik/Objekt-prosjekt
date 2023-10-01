package HotelBookingApp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotellbooking.Core.Booking;
import hotellbooking.Core.Hotel;
import hotellbooking.Core.Person;
import hotellbooking.Core.PersonBooking;
import hotellbooking.Core.Room;

public class BookingTest {
    private Booking booking;
    private Hotel hotel;
    private PersonBooking personBooking;

    @BeforeEach
    public void setUp() {
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(1, 101, false);
        Room room3 = new Room(4,103,false);
        rooms.add(room1);
        rooms.add(room3);

        hotel = new Hotel("Hotel California", new ArrayList<>(), rooms);

        personBooking = new PersonBooking("JohnDoe", 24, 94848501);

        booking = new Booking(personBooking, hotel);
    }

    @Test
    public void testGetPersonBooking() {
        Assertions.assertEquals(personBooking, booking.getPersonBooking());
    }

    @Test
    public void testSetPersonBooking() {
        PersonBooking newPersonBooking = new PersonBooking("JaneSmith", 64, 45695485);
        booking.setPersonBooking(newPersonBooking);
        Assertions.assertEquals(newPersonBooking, booking.getPersonBooking());
    }

    @Test
    public void testGetPersonList() {
        List<Person> personList = booking.getPersonList();
        Assertions.assertEquals(1, personList.size());
        Assertions.assertEquals(personBooking, personList.get(0));
    }

    @Test
    public void testGetRoom() {
        Room room = booking.getRoom();
        Assertions.assertNotNull(room);
        Assertions.assertTrue(room.isBooked());
        Assertions.assertTrue(hotel.getRooms().contains(room));
    }
}
