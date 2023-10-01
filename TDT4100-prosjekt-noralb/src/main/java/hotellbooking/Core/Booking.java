package hotellbooking.Core;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Booking {

    /**
     * Klasse som oppretter bookinger
     * Du kan opprette booking for en person opp til fire personer
     */
    
    private PersonBooking personBooking;
    private List<Person> personList;
    private Hotel hotel;
    private Room room;
    
    
    //Konstruktør for å opprette en booking for opp til fire personer
    public Booking(PersonBooking personBooking, Hotel hotel, PersonInBooking ... personInBooking ) {
        
        this.personBooking = personBooking;
        this.personList = new ArrayList<>(Arrays.asList(personInBooking));

        if (personList.size()>3){
            throw new IllegalArgumentException("Det er for mange peroner i bookingen");
        }
        this.personList.add(personBooking);
        this.hotel = hotel;
        this.hotel.addBooking(this);
        
        this.room = hotel.findAvaliableRoom(this);
        
        this.room.BookRoom();

    }

    //Konstruktør for å opprette en booking for bare en person
    public Booking(PersonBooking personBooking, Hotel hotel){
        this.personBooking = personBooking;
        this.hotel = hotel;
        this.hotel.addBooking(this);
        this.personList = new ArrayList<>();
        this.personList.add(personBooking);
        this.room = hotel.findAvaliableRoom(this);
        this.room.BookRoom();
    }
    
    //gettere og settere
    public PersonBooking getPersonBooking() {
        return this.personBooking;
    }

    public void setPersonBooking(PersonBooking personBooking) {
        this.personBooking = personBooking;
    }

    public List<Person> getPersonList() {
        return this.personList;
    }

    public Room getRoom(){
        return this.room;
    }



    //toString
    @Override
    public String toString() {
        return "Booking: \n Navn = " + personBooking.getName() + "\n Alder = " + personBooking.getAge() + "\n Telefonnummer = " + personBooking.getTelephoneNumber() +"\n Antall personer = " + personList.size() 
        +  "\n Romnummer = " + room.getHotelRoomNumber();
    }

    

  
    
    
}
