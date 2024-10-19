import java.util.HashMap;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public void setExits(String direction, Room neighbour)
    {
        exits.put(direction, neighbour);
    }

    public String getDescription()
    {
        return description;
    }
}
