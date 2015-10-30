package states;

import enumerations.GameKeys;
import enumerations.States;
import games.Game;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
import java.awt.Graphics2D;
import keys.Key;
import org.junit.Before;
import org.junit.Test;
import statemanagers.StateManager;
import layers.TitleLayer;

@RunWith(JMockit.class)
public class TitleScreenStateTest {
    static TitleScreenState tss;
    
    @Mocked StateManager mocked_sm;
    @Mocked States mocked_state;
    @Mocked Game mocked_game;
    @Mocked Graphics2D mocked_screen;
    @Mocked TitleLayer mocked_tl;
    
    
    @Before
    public void setUp() {
	tss = new TitleScreenState(mocked_sm);
    }

    /*
     *	Check to see if the drawToScreen method gets called
     */
    @Test
    public void testDrawToScreen() {
	
	tss.drawToScreen(mocked_screen);
	
	new Verifications() {{
	    mocked_tl.drawToScreen(mocked_screen);
	}};
    }

    /*
     * 
     */
    @Test
    public void testHandleKeyReleased_Up(@Mocked final Key mocked_key) {
	new NonStrictExpectations() {
	    final GameKeys mocked_up = GameKeys.UP;
	    {
	    mocked_key.getKey();
	    result = mocked_up;
	    }
	};
	
	tss.handleKeyReleased(mocked_key);
    
	new Verifications() {{
	    
	}};
	
    }
    
}
