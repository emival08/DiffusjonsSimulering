

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

//    /**
//     * Check whether or not this rabbit is to give birth at this step.
//     * New births will be made into free adjacent locations.
//     *
//     * @param foodColoring A list to return foodColoring.
//     */
//    private void spawnFoodColoring(List<Agent> foodColoring) {
//        // New food coloring is placed into adjacent locations.
//        // Get a list of adjacent free locations.
//        Field field = getField();
//        List<Location> free = field.getFreeAdjacentLocations(getLocation());
//        for (int b = 0; free.isEmpty(); b++) {
//            Location loc = free.remove(0);
//            FoodColoring young = new FoodColoring(field, loc, logWriter);
//            foodColoring.add(young);
//        }
//    }


    public void act(List<Agent> newAgents, int currentStep) {
        this.currentStep = currentStep;
        if (isExist()) {
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                //do something
            }
        }
    }
}
