

import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.03.18
 */
public abstract class Agent


{
    // Whether the agent is exist or not.
    private boolean exist;
    // The agent's field.
    private Field field;
    // The agent's position in the field.
    private Location location;


    /**
     * Create a new agent at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Agent(Field field, Location location) {
        exist = true;
        this.field = field;
        setLocation(location);
    }

    /**
     * Make this agent act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAgents A list to receive new agents.
     */
    abstract public void act(List<Agent> newAgents, int currentStep);

    /**
     * Check whether the agent exists or not.
     *
     *
     * @return true if the agent still exists.
     */
    public boolean isExist()
    {
        return exist;
    }


    /**
     * Return the agent's location.
     * @return The agent's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the agent at the new location in the given field.
     * @param newLocation The agent's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the agent's field.
     * @return The agent's field.
     */
    protected Field getField()
    {
        return field;
    }
}
