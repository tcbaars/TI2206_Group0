package states;



import enumerations.States;
import games.Game;
import keys.Key;
import keys.TypedKey;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import statemanagers.StateManager;


public class ScreenStateTest {
    StateManager stateManager = mock(StateManager.class);
    //when(stateManager.getCurrrentState()).thenReturn()
    
    /*
     *	Mock the behavior of the StateManager
     */
    @Before
    public void setUp() {
	this.stateManager = stateManager;
    }

    /*
     *	Check if the current state gets set
     *	    -->	create a state, & call setCurrentState
     *	    -->	expect 
     */
    @Test
    public void testSetCurrentState() {
	
        ScreenState instance = null;
        instance.setCurrentState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testLaunchNewGame() {
        System.out.println("launchNewGame");
        Game game = null;
        ScreenState instance = null;
        instance.launchNewGame(game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        ScreenState instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testHandleKeyReleased() {
        System.out.println("handleKeyReleased");
        Key key = mock(Key.class);
        ScreenState instance = null;
        instance.handleKeyReleased(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
    
}
