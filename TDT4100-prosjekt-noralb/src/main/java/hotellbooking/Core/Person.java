package hotellbooking.Core;

public class Person {

    /**
     * Superklasse som personBooking og personInBooking arver fra
     */
    
    private String name;

    // Konstruktør
    public Person(String name) {
        if (isValidName(name)){
            this.name = name;
        }
    }

    /**
     * @param name
     * Metode for å sjekke om det er er et godkjent navn som kommer inn
     */

    public boolean isValidName(String name) {
        if (name.contains(" ")){
            String fornavn = name.substring(0, name.indexOf(" "));
            String etternavn = name.substring(name.indexOf(" ") + 1);

            char[] chars1 = fornavn.toCharArray();
            char[] chars2 = etternavn.toCharArray();

            for (char c : chars1) {
                if(!Character.isLetter(c)) {
                    throw new IllegalArgumentException("Kan ikke ha tall i navnet");
                }
            }

            for (char c : chars2) {
                if(!Character.isLetter(c)) {
                    throw new IllegalArgumentException("Kan ikke ha tall i navnet");
                }
            }
            return true;

        }
        char[] chars = name.toCharArray();
    
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("Kan ikke ha tall i navnet");
            }
        }
    
        return true;
    }

    // gettere og settere

    public void setName(String name){
        if (isValidName(name)){
            this.name = name;
        }
    }
    
    public String getName(){
        return this.name;
    }


}
