import static org.junit.Assert.*;
import org.junit.Test;

public class RoomsTextTest {

    @Test
    public void test_defaultConstructor_doors() {
        Rooms r = new Rooms();
        int expectedDoors = 0;
        for (int i = 0; i < r.getDoorways().length; i++) {
            if (r.checkRoomExit(i)) {
                expectedDoors++;
            }
        }
        assertEquals("No doors should be present when map is generated", 0, expectedDoors);
    }

    @Test
    public void test_defaultConstructor_event() {
        Rooms r = new Rooms();
        assertEquals("No event should be associated with a room made with the default constructor", false, r.getEvent());
    }

    @Test
    public void test_constructor_doors() {
        Rooms r = new Rooms(true, true, false, false, false);
        int foundDoors = 0;
        for (int i = 0; i < r.getDoorways().length; i++) {
                if (r.checkRoomExit(i)) {
                    foundDoors++;
                }
        }
        assertEquals("Should have found two doors. Actually found: ", 2, foundDoors);
    }

    @Test
    public void test_constructor_event() {
        Rooms r = new Rooms(false,false,false,false,true);
        assertEquals("Room should have an unresolved event", true, r.getEvent());
    }

    @Test
    public void test_copyConstructor() {
        Rooms r = new Rooms(true, true, false, false, true);
        Rooms r2 = new Rooms(r);
        r.setDoorway(3);
        r.setUnresolvedEvent(false);
        assertEquals("Copied room should not have a western doorway. Expected false, got: ", false, r2.checkRoomExit(3));
        assertEquals("Copied room should have an unresolved event", true, r2.getEvent());
    }

    @Test
    public void test_setDoorway() {
        Rooms r = new Rooms();
        r.setDoorway(2);
        assertEquals("Door should have been opened to the south", true, r.checkRoomExit(2));
    }

    @Test
    public void test_setEvent() {
        Rooms r = new Rooms();
        assertEquals("Event should be false when initiated", false, r.getEvent());
        r.setUnresolvedEvent(true);
        assertEquals("Event should be true after use of setter", true, r.getEvent());
    }
}

