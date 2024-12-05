import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {
    private Object[][] field;

    public Field(int depth, int width) {
        field = new Object[depth][width];
    }

    public void clear() {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                field[row][col] = null;
            }
        }
    }

    public void clear(Location location) {
        field[location.getRow()][location.getCol()] = null;
    }

    public void place(Object animal, Location location) {
        field[location.getRow()][location.getCol()] = animal;
    }

    public Object getObjectAt(Location location) {
        return field[location.getRow()][location.getCol()];
    }

    public Location freeAdjacentLocation(Location location) {
        List<Location> adjacent = adjacentLocations(location);
        for (Location loc : adjacent) {
            if (getObjectAt(loc) == null) {
                return loc;
            }
        }
        return null;
    }

    public List<Location> getFreeAdjacentLocations(Location location) {
        List<Location> free = new ArrayList<>();
        List<Location> adjacent = adjacentLocations(location);
        for (Location loc : adjacent) {
            if (getObjectAt(loc) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    public List<Location> adjacentLocations(Location location) {
        List<Location> locations = new ArrayList<>();
        int row = location.getRow();
        int col = location.getCol();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r != 0 || c != 0) {
                    int newRow = row + r;
                    int newCol = col + c;
                    if (newRow >= 0 && newRow < field.length && newCol >= 0 && newCol < field[0].length) {
                        locations.add(new Location(newRow, newCol));
                    }
                }
            }
        }
        Collections.shuffle(locations);
        return locations;
    }

    public int getDepth() {
        return field.length;
    }

    public int getWidth() {
        return field[0].length;
    }
}
