package keys;

import org.junit.Test;

import enumerations.GameKeys;
import keys.KeyAdapter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito;
import static org.mockito.Mockito.*;


/**
 * Created by Dylan 9-10-2015
 * Test method for keys.KeyAdapter class.
 */
public class KeyAdapterTest {
	KeyEvent key = new Mockito.mock(KeyEvent.class);
	KeyEvent tkey = new Mockito.mock(KeyEvent.class);

    /**
     * This method tests if the output is a key and not null.
     */
	  @Test
	    public void convertKeyPressedTest() {
	        Mockito.when(key.getKeyCode()).thenReturn(GameKeys.UP.getKeyCode());
	        assertEquals(GameKeys.UP, KeyAdapter.convertKeyPressed(key).getKey());
	    }

      /**
     * This method tests if the output is a key and not null.
     */
    @Test
    public void convertKeyReleasedTest() {
    	 Mockito.when(key.getKeyCode()).thenReturn(GameKeys.UP.getKeyCode());
    	 assertEquals(GameKeys.UP, KeyAdapter.convertKeyReleased(key).getKey());
    }

      /**
     * This method tests if the output is a typedkey and not null.
     */
    @Test
    public void convertKeyTypedTest() {
    	Mockito.when(tkey.getKeyCode()).thenReturn(GameKeys.MUSIC.getKeyCode());
        assertEquals(GameKeys.MUSIC, KeyAdapter.convertKeyPressed(key).getKey());
    }
}
