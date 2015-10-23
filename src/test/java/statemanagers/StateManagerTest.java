
package statemanagers;

import enumerations.States;
import games.Game;
import java.awt.Graphics2D;
import keys.Key;
import keys.TypedKey;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import mockit.*;


public class StateManagerTest {
    
    public StateManagerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testSetCurrentState() {
	System.out.println("setCurrentState");
	States state = null;
	StateManager instance = new StateManager();
	instance.setCurrentState(state);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testLaunchNewGame() {
	System.out.println("launchNewGame");
	Game game = null;
	StateManager instance = new StateManager();
	instance.launchNewGame(game);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
	System.out.println("update");
	StateManager instance = new StateManager();
	instance.update();
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testDrawToScreen() {
	System.out.println("drawToScreen");
	Graphics2D screen = null;
	StateManager instance = new StateManager();
	instance.drawToScreen(screen);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testHandleKeyPressed() {
	System.out.println("handleKeyPressed");
	Key key = null;
	StateManager instance = new StateManager();
	instance.handleKeyPressed(key);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testHandleKeyReleased() {
	System.out.println("handleKeyReleased");
	Key key = null;
	StateManager instance = new StateManager();
	instance.handleKeyReleased(key);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testHandleKeyTyped() {
	System.out.println("handleKeyTyped");
	TypedKey key = null;
	StateManager instance = new StateManager();
	instance.handleKeyTyped(key);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
