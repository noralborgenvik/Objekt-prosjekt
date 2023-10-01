package hotellbooking.Core;

public class PersonInBooking extends Person{

    /**
     * Klasse som oppretter personer som andre booker for
     */

    private int age;
    
    //Konstruktør
    public PersonInBooking(String name, int age) {
        super(name);
        if(age < 0){
            throw new IllegalArgumentException("Kan ikke ha bnegativ alder");
        }
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return super.toString() + " [age=" + age + "]";
    }
}
