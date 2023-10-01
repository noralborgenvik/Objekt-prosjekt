package hotellbooking.Core;

public class PersonBooking extends Person{

    /**
     * Klasse som oppretter en person som skal booke hotelrom
     * klassen arver fra superklassen person
     */


    private int age;
    private int telephoneNumber;

    //Konstruktør
    public PersonBooking(String name, int age, int telephoneNumber) {
        super(name);
        if (isValidAge(age)){
            this.age = age;
        }
        if (isValidPhoneNumber(telephoneNumber)){
            this.telephoneNumber = telephoneNumber;
        }

    }

    //gettere og settere
    public void setAge(int age){
        if (isValidAge(age)){
            this.age = age;
        }
    }

    public int getAge(){
        return this.age;
    }

    public int getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public String getThisName() {
        return this.getName();
    }


    //valideringsmetoder
    private boolean isValidAge(int age){
        if (age < 18){
            throw new IllegalArgumentException("Du er for ung til å booke hotellet");
        }
        if (age < 0){
            throw new IllegalArgumentException("Ikke en gyldig alder");
        }
        return true;

    }

    private boolean isValidPhoneNumber(int number){
        String numberString = String.valueOf(number);
        if (numberString.matches("\\d{8}") == false){
            throw new IllegalArgumentException("Ikke et godkjent telefonnummer");
        }
        if (numberString.matches("\\d{8}") == true){
            return true;
        }
        return true;
    }

    //toString
    @Override
    public String toString() {
        return "Person [name=" + this.getName() + ", alder=" + age + ", Telefonnr = " + telephoneNumber + "]";
    }

    public static void main(String[] args) {
        PersonBooking p1 = new PersonBooking("Nora", 22,12345678);
        System.out.println(p1);

        PersonBooking p2 = new PersonBooking("Nora", 24,94840501);
        System.out.println(p2);
    }

    
}
