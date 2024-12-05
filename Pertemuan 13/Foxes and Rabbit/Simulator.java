import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private List<Animal> animals;
    private Field field;
    private int step;

    public Simulator(int depth, int width) {
        animals = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    public void simulate(int numSteps) {
        for (int i = 0; i < numSteps; i++) {
            simulateOneStep();
        }
    }

    private void simulateOneStep() {
        step++;
        List<Animal> newAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            animal.act(newAnimals);
        }
        animals.addAll(newAnimals);
        printField();
    }

    private void reset() {
        step = 0;
        animals.clear();
        field.clear();
        populate();
        printField();
    }

    private void populate() {
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (Randomizer.getRandom().nextDouble() <= 0.05) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(field, location);
                    animals.add(fox);
                    field.place(fox, location);
                } else if (Randomizer.getRandom().nextDouble() <= 0.15) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(field, location);
                    animals.add(rabbit);
                    field.place(rabbit, location);
                }
            }
        }
    }

    private void printField() {
        System.out.println("Step: " + step);
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(new Location(row, col));
                if (animal instanceof Fox) {
                    System.out.print("F ");
                } else if (animal instanceof Rabbit) {
                    System.out.print("R ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
