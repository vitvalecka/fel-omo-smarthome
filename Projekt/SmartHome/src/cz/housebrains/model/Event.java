package cz.housebrains.model;

import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Pet;
import cz.housebrains.model.entities.Tool;

public class Event {

    /**
     * Tool assigned to this event.
     */
    public Tool tool;

    /**
     * Device assigned to this event.
     */
    public Device device;

    /**
     * Pet assigned to this event.
     */
    public Pet pet;

    /**
     * Description of the task concerning this event.
     */
    public String whatToDo;

    /**
     * Creates event linking given device with command what to do.
     * @param device device that should be used for given task
     * @param whatToDo task
     */
    public Event(Device device, String whatToDo) {
        this.device = device;
        this.whatToDo = whatToDo;
    }

    /**
     * Creates event linking given tool with command what to do.
     * @param tool tool that should be used for given task
     * @param whatToDo task
     */
    public Event(Tool tool, String whatToDo) {
        this.tool = tool;
        this.whatToDo = whatToDo;
    }

    /**
     * Creates event linking given pet with command what to do.
     * @param pet tool that should be used for given task
     * @param whatToDo task
     */
    public Event(Pet pet, String whatToDo) {
        this.pet = pet;
        this.whatToDo = whatToDo;
    }

    /**
     * Returns string value representing task to be done.
     * @return string value of given task
     */
    public String getWhatToDo() {
        return whatToDo;
    }
}
