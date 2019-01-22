package cz.housebrains.model.entities;

import cz.housebrains.model.entities.details.Consumption;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeviceTest {

    /**
     * Tests repair of device with full health. Repair is unsuccessful.
     */
    @Test
    public void repairUnssuccessfulFullHealth() {
        Device dummy = new Device("Dummy", new Room("Test Room", 0), new Consumption(2,1,2,1,2,1));
        assertFalse(dummy.repair());
    }

    /**
     * Tests repair of BROKEN device. Repair is successful.
     */
    @Test
    public void repairSuccessfulNoHealth() {
        Device dummy = new Device("Dummy", new Room("Test Room", 0), new Consumption(2,1,2,1,2,1));
        dummy.state = Device.State.BROKEN;
        assertTrue(dummy.repair());
    }

    /**
     * Test destroying IDLE device. Destroy is successful.
     */
    @Test
    public void destroyItemSuccesful() {
        Device dummy = new Device("Dummy", new Room("Test Room", 0), new Consumption(2,1,2,1,2,1));
        assertTrue(dummy.destroy());
    }

    /**
     * Test destroying ACTIVE device. Destroy is unsuccessful.
     */
    @Test
    public void destroyItemUnsuccesfulItemUsed() {
        Device dummy = new Device("Dummy", new Room("Test Room", 0), new Consumption(2,1,2,1,2,1));
        dummy.state = Device.State.ACTIVE;
        assertFalse(dummy.destroy());
    }

    /**
     * Tests deactivation of device at the end of round. Deactivation is successful.
     */
    @Test
    public void deactivateAfterRoundEnd() {
        Device dummy = new Device("Dummy", new Room("Test Room", 0), new Consumption(2,1,2,1,2,1));
        dummy.state = Device.State.ACTIVE;
        dummy.nextRound();
        assertEquals(Device.State.IDLE, dummy.state);
    }
}
