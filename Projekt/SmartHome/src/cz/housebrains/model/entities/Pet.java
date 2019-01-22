package cz.housebrains.model.entities;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.Entity;
import cz.housebrains.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pet extends Entity {

    private final String animal;
    protected int counter = 0;
    protected boolean hungry = false;
    protected List<Person> observers = new ArrayList<>();
    private final List<Tool> tools = HouseController.getInstance().getTools();
    protected HouseController houseController = HouseController.getInstance();

    /**
     * Creates new pet with given animal species, name and in given room.
     * @param animal string description of animal specie
     * @param name   name of the animal
     * @param room   room for the animal to spawn in
     */
    public Pet(String animal, String name, Room room) {
        super(name, room);
        this.animal = animal;
    }

    /**
     * Returns pet entity.
     * @return pet as an entity
     */
    public String getAnimal() {
        return animal;
    }

    /**
     * Returns boolean value representing if the animal is hungry.
     * @return boolean value if animal is hungry
     *      true    animal is hungry, or animal is a cat and has a name Garfield
     *      false   otherwise
     */
    public boolean getHungry() {
        if (this.animal == "cat" && this.name == "Garfield") {
            return true;
        }
        return hungry;
    }

    /**
     * Sets animal that it is hungry and notifies all observers.
     */
    public void isHungry() {
        hungry = true;
        notifyAllObservers();
    }

    /**
     * Adds given observer to the animal.
     * @param person person to observe the animal
     */
    public void addOvserver(Person person){
        observers.add(person);
    }

    /**
     * Notifies all observers about pet's activity and needs.
     */
    public void notifyAllObservers() {
        if (!observers.isEmpty()) {
            String strObserverList = "";

            for (Person observer : observers) {
                observer.update(this, "feed pet");
                strObserverList += observer.getName() + ", ";
            }

            strObserverList = strObserverList.substring(0, strObserverList.length()-2);
            System.out.println(name + " is hungry. Notify sent to: " + strObserverList);
            houseController.eventLog.writeToLog(name + " is hungry. Notify sent to: " + strObserverList);
        } else {
            System.out.println(name + " has no observers.");
        }

    }

    /**
     * Feeds an animal. Returns boolean information whether the feeding was successful.
     * @return boolean information whether feeding was successful
     *      true    if pet was hungry and it was successfully fed
     *      false   otherwise
     */
    public boolean feed() {
        if (hungry) {
            hungry = false;
            return true;
        }
        return false;
    }

    /**
     * Calculates changes and moves time to next round.
     */
    @Override
    public void nextRound() {
        counter++;
        if ((counter % Constants.PET_FEED_CYCLE == 0) || (this.animal == "cat" && this.name == "Garfield")) {
            isHungry();
        }

        int magicNumber = getRandomNumber(0, 100);

        if (magicNumber < 5) {
            Collections.shuffle(tools);

            for (Tool tool : tools) {
                if (tool.destroy()) {
                    System.out.println(name + " destroyed " + tool.getName());
                    houseController.eventLog.writeToLog(name + " destroyed " + tool.getName());
                    houseController.activityUsageLog.writeToLog(name + " destroyed " + tool.getName());
                    break;
                }
            }
        }
    }
}
