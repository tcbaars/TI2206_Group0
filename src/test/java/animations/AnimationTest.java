package animations;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Adriaan on 11-9-2015.
 * Test method for animations.Animation class.
 */
public class AnimationTest {

    @Test
    public void constructorTest() {
        Animation test = Animation.createAnimation("Player", "/sprites/Player.png", 12, 1300, 600, 10);

        assertFalse(test.hasLooped());
    }

    @Test // TODO, THIS DOES NOT YET WORK, THIS IS TEMPORARY, NEED INJECTION
    public void needInjectionTest() {
        Animation test = Animation.createAnimation("Player", "/sprites/Player.png", 12, 1300, 600, 10);
        test.getCurrentFrame();
        test.setCurrentFrame(1);
        test.setDelay(1);
        test.setNumberFrames(1);
        test.setStartFrame(1);
        test.update();
    }
}
