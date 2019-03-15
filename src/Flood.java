

/**
 * Represents one piece of a flood. In other words it will take up one location in the field.
 */
public class Flood {


    private Location location;

    private boolean alive;

    private Field field;

    public Flood(Field field, Location location) {
        this.location = location;
        this.field = field;
        alive = true;
    }

    protected void act(int currentSteps){
        int col = location.getCol();
        int row = location.getRow();
        col++;
        Location newLocation = new Location(row, col);
        setLocation(newLocation, currentSteps);

    }

    protected void setLocation(Location newLocation, int currentSteps){
        if(location != null){
            field.clear(location);
        }
        field.place(this, newLocation, currentSteps);
        location = newLocation;
    }

    public Field getField() {
        return field;
    }

    public Location getLocation() {
        return location;
    }
}
