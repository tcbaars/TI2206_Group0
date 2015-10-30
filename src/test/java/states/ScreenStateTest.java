package states;


import mockit.*;
import enumerations.States;
import games.Game;
import java.awt.Graphics2D;
import keys.Key;
import org.junit.Before;
import org.junit.Test;
import mockit.integration.junit4.JMockit;

import org.junit.runner.RunWith;
import statemanagers.StateManager;
import tools.resourcetools.MusicPlayer;
import settings.SoundSettings;
import settings.MusicSettings;
import settings.DebugSettings;
import enumerations.GameKeys;

@RunWith(JMockit.class)
public class ScreenStateTest {
    static ScreenState ss;
    
    @Mocked StateManager mocked_sm;
    @Mocked States mocked_state;
    @Mocked Game mocked_game;
 
    /*
     * Create an instance of ScreenState, using a mock-up of a StateManager
     */
    @Before
    public void setUp() {
	
	ss = new ScreenState(mocked_sm) {
	    
	    public void drawToScreen(Graphics2D screen) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    }
	};
    }

    /*
     *	Check if the current state gets set
     */
    @Test
    public void testSetCurrentState() {
	
        ss.setCurrentState(mocked_state);
	
	new Verifications() {{
		mocked_sm.setCurrentState(mocked_state);
	}};
    }

    @Test
    public void testLaunchNewGame() {
        
        ss.launchNewGame(mocked_game);
	
	new Verifications() {{
		mocked_sm.launchNewGame(mocked_game);
	}};
    }

    
    /*
     * test to see if music starts playing
     */
    @Test
    public void testUpdate(final @Mocked MusicPlayer mocked_mp) {
        ss.update();
	
	new Verifications() {{
		mocked_mp.playMusic();
	}};
    }

    /*
     * test to see if the sound key release is handled appropriately
     */
    @Test
    public void testHandleKeyReleased_sound(@Mocked final SoundSettings mocked_sound_settings, @Mocked final Key mocked_key) {
	
	new NonStrictExpectations() {
	    final GameKeys mocked_sound = GameKeys.SOUND;
	    {
	    mocked_key.getKey();
	    result = mocked_sound;
	    }
	};
	
	ss.handleKeyReleased(mocked_key);
    
	new Verifications() {{
	    mocked_sound_settings.toggleSound();
	}};
    }
    
    /*
     * test to see if the music key release is handled appropriately
     */
    @Test
    public void testHandleKeyReleased_music(@Mocked final MusicSettings mocked_music_settings, @Mocked final Key mocked_key) {
	
	new NonStrictExpectations() {
	    final GameKeys mocked_music = GameKeys.MUSIC;
	    {
	    mocked_key.getKey();
	    result = mocked_music;
	    }
	};
	
	ss.handleKeyReleased(mocked_key);
    
	new Verifications() {{
	    mocked_music_settings.toggleMusic();
	}};
    }
    
    /*
     * test to see if the debug key release is handled appropriately
     */
    @Test
    public void testHandleKeyReleased_music(@Mocked final DebugSettings mocked_debug_settings, @Mocked final Key mocked_key) {
	
	new NonStrictExpectations() {
	    final GameKeys mocked_debug = GameKeys.DEBUG;
	    {
	    mocked_key.getKey();
	    result = mocked_debug;
	    }
	};
	
	ss.handleKeyReleased(mocked_key);
    
	new Verifications() {{
	    mocked_debug_settings.toggleDebugMode();
	}};
    }


}
