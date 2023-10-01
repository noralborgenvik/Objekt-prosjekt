package hotellbooking.Core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface IBookingFileHandler {

    /**
     * Interface som en klasse som skal lese til og fra en fil for hotelbooking må implementere
     * Klassen hotel implementerer dette interfacet 
     * @param path
     * @param hotel
     * @throws FileNotFoundException
     * @throws IOException
     */
    
    void readBooking(String path, Hotel hotel) throws FileNotFoundException, IOException;

    void writeBooking(String path, Hotel hotel) throws FileNotFoundException, IOException;

    Path getFilePath(String filename);
}
