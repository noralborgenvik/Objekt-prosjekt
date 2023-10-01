package hotellbooking.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BookingFileHandler implements IBookingFileHandler{
    
    //Klasse for filhåndtering

    private String navn;
    private int alder;
    private int tlfnr;
    private int antall;
    private int romnr;
    private boolean test = false;
    
    //Metode for å lese fra fil
    @Override
    public void readBooking(String path, Hotel hotel) throws FileNotFoundException, IOException {
        
        try (Scanner scanner = new Scanner(new FileReader(getFilePath(path).toFile()))) {
            
            while (scanner.hasNextLine()) {
                for(int i = 0; i < 6; i++){
                    String[] bookingData = scanner.nextLine().split("= ");
                    if (i == 0){continue;}
                    if (i == 1){this.navn = bookingData[1];}
                    if (i == 2){this.alder = Integer.parseInt(bookingData[1]);}
                    if (i == 3){this.tlfnr = Integer.parseInt(bookingData[1]);}
                    if (i == 4){this.antall = Integer.parseInt(bookingData[1]);}
                    if (i == 5){this.romnr = Integer.parseInt(bookingData[1]);}
                }

                PersonBooking personBooking = new PersonBooking(navn, alder, tlfnr);
                PersonInBooking[] liste = new PersonInBooking[antall];

                Room room = new Room(antall, romnr, false);
                //hotel.addHotelRooms(room);
                Booking booking = new Booking(personBooking, hotel, liste);
                hotel.addBooking(booking);
                
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }

    //Metoder for å skrive til fil
    @Override
    public void writeBooking(String path, Hotel hotel) throws FileNotFoundException, IOException {
        try (PrintWriter writer = new PrintWriter(getFilePath(path).toFile())){
            
            for (Booking booking : hotel.getAllBookings()){
                
                writer.println(booking);

            }
        }
    }

    public void writeOneBooking(String path, Hotel hotel, Booking booking) throws FileNotFoundException, IOException {
        try {

            FileWriter fileWriter = new FileWriter(getFilePath(path).toFile(), true);
            PrintWriter writer = new PrintWriter(fileWriter);

                writer.println(booking);

            writer.close();
            fileWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Metodfe for å finne riktig fielpath
    @Override
    public Path getFilePath(String filename) {
        if (this.test == false){
            return Path.of(((System.getProperty("user.dir") + "/src/main/resources/" + filename + ".txt")));
        }
        else {
            return Path.of(((System.getProperty("user.dir") + "/src/main/resources/" + filename +  "test.txt")));
        }
    }

    //Hjelpemetoder til tester
    public void isTest(){
        this.test = true;
    }

    public String getContent(String filename) throws IOException{
        return Files.readString(this.getFilePath(filename));
    }
    
    
}
