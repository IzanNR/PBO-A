import java.util.List;

public abstract class Animal {
    protected int age;
    protected boolean alive;
    protected Location location;
    protected Field field;

    public Animal(Field field, Location location) {
        this.field = field;
        this.location = location;
        alive = true;
        age = 0;
    }

    public abstract void act(List<Animal> newAnimals);

    public boolean isAlive() {
        return alive;
    }

    protected void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location newLocation) {
        if (field != null && location != null) {
            field.clear(location); // Hapus posisi lama
        }
        location = newLocation;
        if (field != null && location != null) {
            field.place(this, location); // Tempatkan di lokasi baru
        }
    }
    
}
