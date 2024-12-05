import java.util.List;

public class Fox extends Animal {
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 30;
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 2;
    private static final int RABBIT_FOOD_VALUE = 9;

    private int foodLevel;

    public Fox(Field field, Location location) {
        super(field, location);
        foodLevel = Randomizer.getRandom().nextInt(RABBIT_FOOD_VALUE);
    }

    @Override
    public void act(List<Animal> newAnimals) {
        incrementAge();
        incrementHunger();
        if (alive) {
            giveBirth(newAnimals);
            Location newLocation = findFood(); // Cari makanan
            if (newLocation == null) {
                newLocation = field.freeAdjacentLocation(location); // Cari lokasi kosong
            }
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

    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        for (Location loc : adjacent) {
            Object animal = field.getObjectAt(loc);
            if (animal instanceof Rabbit rabbit && rabbit.isAlive()) {
                rabbit.setDead();
                foodLevel = RABBIT_FOOD_VALUE; // Perbarui level makanan
                return loc; // Lokasi makanan ditemukan
            }
        }
        return null;
    }
    

    private void giveBirth(List<Animal> newAnimals) {
        List<Location> freeLocations = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && freeLocations.size() > 0; b++) {
            Location loc = freeLocations.remove(0);
            Fox young = new Fox(field, loc);
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
