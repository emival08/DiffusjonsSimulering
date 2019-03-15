

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.03.18
 */
public class Simulator {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    // List of agents in the field.
    private List<Agent> agents;
    // The current state of the field.
    private Field field;
    // The current currentStep of the simulation.
    public int currentStep;
    // A graphical view of the simulation.
    private List<SimulatorView> views;


    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }


    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        BufferedReader br = null;

        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        agents = new ArrayList<>();
        field = new Field(depth, width);

        views = new ArrayList<>();

        SimulatorView view = new GridView(depth, width);
        view.setColor(FoodColoring.class, Color.RED);
        views.add(view);

        view = new GraphView(500, 150, 500);
        view.setColor(FoodColoring.class, Color.RED);
        graphview = (GraphView)view;
        views.add(view);

        // Setup a valid starting point.
        reset();

        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            boolean running = true;

            while (running) {
                System.out.print("Enter something : ");
                String input = br.readLine();
                String[] splittedString = input.split(" ");

                if (splittedString.length > 1) {
                    if ("simulate".equals(splittedString[0])) {
                        String stringNum = splittedString[1];
                        int steps = Integer.parseInt(stringNum);
                        simulate(steps);
                    }
                } else if ("q".equals(input)) {
                    System.out.println("Exit!");
                    running = false;
                } else if ("simulate".equals(input)) {
                    runLongSimulation();
                }

                System.out.println("input : " + input);
                System.out.println("-----------\n");
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     *
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && views.get(0).isViable(field); step++) {
            simulateOneStep();
            delay(0);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single currentStep.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep() {

        currentStep++;

        // Provide space for new agents.
        List<Agent> newAgents = new ArrayList<>();
        // Lets the food color act.
        for (Iterator<Agent> it = agents.iterator(); it.hasNext(); ) {
            Agent agent = it.next();
            agent.act(newAgents, currentStep);
            if (! agent.isExist()) {
                it.remove();
            }
        }

        // Adds the new food coloring to the main lists.
        agents.addAll(newAgents);

        updateViews();
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        currentStep = 0;
        agents.clear();
        for (SimulatorView view : views) {
            view.reset();
        }

        populate();
        updateViews();
    }

    /**
     * Update all existing views.
     */
    private void updateViews() {
        for (SimulatorView view : views) {
            view.showStatus(currentStep, field);
        }
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    //TODO create new populate method
    private void populate() {

    }

    /**
     * Pause for a given time.
     *
     * @param millisec The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
            // wake up
        }
    }
}
