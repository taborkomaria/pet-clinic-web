package pet;

public class Dog implements Pet {
    private final Animal animal;

    public Dog(Animal animal) {
        this.animal = animal;
    }

    public String getName() {
        return this.animal.getName();
    }
}