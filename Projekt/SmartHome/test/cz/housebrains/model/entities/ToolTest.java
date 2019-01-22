package cz.housebrains.model.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToolTest {

    /**
     * Tests repair of tool with full health. Repair is unsuccessful.
     */
    @Test
    public void repairUnssuccessfulFullHealth() {
        Tool dummy = new Tool("Dummy", new Room("Test Room", 0));
        assertFalse(dummy.repair());
    }

    /**
     * Tests repair of BROKEN tool. Repair is successful.
     */
    @Test
    public void repairSuccessfulNoHealth() {
        Tool dummy = new Tool("Dummy", new Room("Test Room", 0));
        dummy.state = Tool.State.BROKEN;
        assertTrue(dummy.repair());
    }

    /**
     * Test destroying IDLE tool. Destroy is successful.
     */
    @Test
    public void destroyItemSuccesful() {
        Tool dummy = new Tool("Dummy", new Room("Test Room", 0));
        assertTrue(dummy.destroy());
    }

    /**
     * Test destroying ACTIVE tool. Destroy is unsuccessful.
     */
    @Test
    public void destroyItemUnsuccesfulItemUsed() {
        Tool dummy = new Tool("Dummy", new Room("Test Room", 0));
        dummy.state = Tool.State.ACTIVE;
        assertFalse(dummy.destroy());
    }

}