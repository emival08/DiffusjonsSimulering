

import java.util.List;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.03.18
 */
public class FoodColoring extends Agent {
    // Characteristics shared by all rabbits (class variables).
    
    // The current step of the simulation
    private int currentStep;
    

    /**
     * Create a new instance of Food coloring.
     *
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public FoodColoring(Field field, Location location) {
        super(field, location);
    }


    public void act(List<Agent> newAgents, int currentStep) {
        this.currentStep = currentStep;
        if (isExist()) {
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if (newLocation != null) {
                setLocation(newLocation);
           }
        }
    }
}
