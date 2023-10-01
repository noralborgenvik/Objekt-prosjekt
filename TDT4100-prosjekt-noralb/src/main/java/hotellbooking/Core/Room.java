package hotellbooking.Core;

public class Room {

    /**
     * Klasse som oppretter rom, og holder styr på om det er booket eller ikke
     */
    
    private int beds;
    private boolean isBooked;
    private int hotelRoomNumber;

    //Konstruktør
    public Room(int beds, int hotelRoomNumber, boolean isBooked) {
        this.beds = beds;
        this.hotelRoomNumber = hotelRoomNumber;
        this.isBooked = false;
        
    }
    
    /**
     * Metode som setter dette rommet til å bli booket
     */
    public void BookRoom(){
        this.isBooked = true;
    }

    //gettere og settere
    public boolean isBooked() {
        return isBooked;
    }

    public int getBeds() {
        return beds;
    }

    public int getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    
    @Override
    public String toString() {
        return "Room [beds = " + beds + ", isBooked = " + isBooked + ", hotelRoomNumber = " + hotelRoomNumber + "]";
    }

    
    



}
