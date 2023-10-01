package hotellbooking.Core;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    
    /**
     * Klasse som oppretter et hotell og holder styr på alle bookingene. 
     * klassen implementerer også BookingFileHandler som hjelper klassen å skrive bookingene til en fil
     * 
     */
    
    //felter
    private String hotelName;
    private List<Booking> allBookings = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    

    //Konstrukør
    public Hotel(String hotelName, List<Booking> allBookings, List<Room> rooms) {
        this.hotelName = hotelName;
        this.allBookings = allBookings;
        this.rooms = rooms;
    }

    // Gettere og settere
    public void addHotelRooms(Room room){
        this.rooms.add(room);
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelName(){
        return this.hotelName;
    }

    public List<Booking> getAllBookings() {
        return this.allBookings;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * metode som finner hvilket rom som er ledig til en gitt booking b
     * @param b
     * @return
     */
    public Room findAvaliableRoom(Booking b){
            
            for (Room r : rooms){
                if (b.getPersonList().size() == 1 || b.getPersonList().size() == 2 ){
                    if (r.isBooked() == false && (r.getBeds() == 1 || r.getBeds() ==2)){
                        return r;
                    }
                }

                if (b.getPersonList().size() == 3 || b.getPersonList().size() == 4 ){
                    if (r.isBooked() == false && (r.getBeds() == 3 || r.getBeds() ==4)){
                        return r;
                    }
                }
            }
            //return null; //Måtte returnere null her, slik at riktig feilmelding ble vist i appen
            throw new IllegalStateException("Ingen ledige rom");
        }

    /**
     * metode som legger til en booking i listen over alle bookingene
     * @param booking
     */
    public void addBooking(Booking booking){
        this.allBookings.add(booking);
    }
    

    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        List<Booking> bookings = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(1, 101, false);
        Room room3 = new Room(2,103,false);
        rooms.add(room1);
        rooms.add(room3);

        Hotel hotel1 = new Hotel("Paradise Hotel", bookings, rooms);
        PersonBooking personBooking = new PersonBooking("John Doe", 24,12345678);
        PersonBooking personBooking2 = new PersonBooking("Petter", 25, 87463523);
        PersonInBooking personInBooking1 = new PersonInBooking("Mette", 26);

        Booking booking1 = new Booking(personBooking, hotel1);
        Booking booking2 = new Booking(personBooking2, hotel1, personInBooking1);

        System.out.println(booking1);
        System.out.println(booking2);

        BookingFileHandler handler = new BookingFileHandler();
        handler.writeBooking("Bookings", hotel1);
        handler.readBooking("Bookings", hotel1);
    }

    
}
