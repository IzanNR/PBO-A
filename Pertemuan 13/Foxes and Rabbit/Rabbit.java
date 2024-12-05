import java.util.List;

public class Rabbit extends Animal {
    private static final int BREEDING_AGE = 3;
    private static final int MAX_AGE = 20;
    private static final double BREEDING_PROBABILITY = 0.15;
    private static final int MAX_LITTER_SIZE = 3;

    public Rabbit(Field field, Location location) {
        super(field, location);
    }

    @Override
    public void act(List<Animal> newAnimals) {
        incrementAge();
        if (alive) {
            giveBirth(newAnimals);
            Location newLocation = field.freeAdjacentLocation(location);
            if (newLocation != null) {
                setLocation(newLocation); // Perbarui lokasi
            } else {
                setDead();
            }
        }
    }
    

    private void incrementAge() {
        age++;
        if (age > MAX_AGE) {
            setDead();
        }
    }

    private void giveBirth(List<Animal> newAnimals) {
        List<Location> freeLocations = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && freeLocations.size() > 0; b++) {
            Location loc = freeLocations.remove(0);
            Rabbit young = new Rabbit(field, loc);
            newAnimals.add(young);
        }
    }

    private int breed() {
        if (canBreed() && Randomizer.getRandom().nextDouble() <= BREEDING_PROBABILITY) {
            return Randomizer.getRandom().nextInt(MAX_LITTER_SIZE) + 1;
        }
        return 0;
    }

    private boolean canBreed() {
        return age >= BREEDING_AGE;
    }
}
