
package states;

import mockit.*;
import org.junit.Before;
import org.junit.Test;
import statemanagers.StateManager;
import gui.MainFrame;
import enumerations.States;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
	
@RunWith(JMockit.class)
public class ExitScreenStateTest {
    static ExitScreenState ess; 
    
    @Mocked
    StateManager mocked_sm;
    States mocked_state;
    
    
    /*
     *	Create an instance of ExitScreenState, using a mock-up of StateManager
     */
    @Before
    public void setUp() {
	ess = new ExitScreenState(mocked_sm);
    }
    
    
    /*
     *	Test to see if the update() method causes MainFrame to exit
     *	
     */
    @Test
    public void testUpdate(final @Mocked MainFrame mf) {
	
	ess.update();
	
	new Verifications() {
	    {
		mf.exit();
	    }
	};
    }
    
    /*
     *	Test to see if the current state gets set;
     */
    public void testSetCurrentState(){
	
        ess.setCurrentState(mocked_state);
	
	new Verifications() {
	    {
		mocked_sm.setCurrentState(mocked_state);
	    }
	};
    }
}
