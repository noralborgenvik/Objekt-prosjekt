package HotelBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import hotellbooking.Core.PersonBooking;


public class PersonBookingTest {


    @Test
    public void testValidAge() {
        PersonBooking person = new PersonBooking("John", 21, 94848585);
        assertEquals(21, person.getAge());
    }

    @Test //(expected = IllegalArgumentException.class)
    public void testInvalidAgeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonBooking("John", -1, 12321234);
        });
        
    }

    @Test //(expected = IllegalArgumentException.class)
    public void testInvalidAgeTooYoung() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonBooking("John", 17,94837485);
        });
    }

    @Test
    public void testInvalidPhoneNumber(){
        assertThrows(IllegalArgumentException.class, () -> { 
            new PersonBooking("John", 17,9483748);
        });
    }

    @Test
    public void testSetAge() {
        PersonBooking person = new PersonBooking("John", 21,94857384);
        person.setAge(30);
        assertEquals(30, person.getAge());
    }

    
}
