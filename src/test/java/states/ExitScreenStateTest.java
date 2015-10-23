
package states;

import org.junit.*;
import mockit.*;
import mockit.integration.junit4.JMockit;
import java.awt.Graphics2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import statemanagers.StateManager;
import gui.MainFrame;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
	
@RunWith(JMockit.class)
public class ExitScreenStateTest {
    static ExitScreenState ess; 
    
    @Mocked
    StateManager sm;
    
    @Before
    public void setUp() {
	ess = new ExitScreenState(sm);
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
		mf.getInstance().exit();
	    }
	};
    }
    
}
