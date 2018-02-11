package pet;
public class Animal implements Pet {
    private final String name;

    public Animal(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}