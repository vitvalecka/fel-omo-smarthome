package cz.housebrains.model;

import cz.housebrains.model.entities.Room;

import java.util.Random;

public abstract class Entity implements RoundClock {

    /**
     * Entity name.
     */
    protected final String name;

    private Random rand = new Random();

    protected Room currentRoom;

    /**
     * Abstract constructor for creating entity.
     * @param name name of entity
     * @param room room fot entity to spawn in
     */
    public Entity(String name, Room room) {
        this.name = name;
        this.currentRoom = room;
    }


    /**
     * Returns current room where this entity is.
     * @return current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Returns name of the entity.
     * @return name of entity
     */
    public String getName() {
        return name;
    }

    /**
     * Sets current room
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Generates random number from interval (min, max).
     * @param min
     * @param max
     * @return random number
     */
    protected int getRandomNumber(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
