package animations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import animations.Animation;
import enumerations.GameAnimations;
import enumerations.GameSprites;
import tools.resourcetools.AnimationLoader;

public class AnimationTest {
	
	Animation animation = new Animation(GameSprites.TROUT.getGameAnimation());
	
	/**
    * This method tests the current frame( starts at 0).
    */
	@Test
	public void getCurrentFrameTest() {
		AnimationLoader.getInstance().loadAnimation(GameAnimations.TROUT);
		assertEquals(AnimationLoader.getInstance().getAnimation(GameAnimations.TROUT)[0],animation.getCurrentFrame());
	}
		
	/**
	* This method tests the frame width.
	*/
	@Test
	public void getFrameWidthTest() {
		assertEquals(GameAnimations.TROUT.getFrameWidth(),animation.getFrameWidth(),0);
	}
	
	/**
	* This method tests frame height.
	*/
	@Test
	public void getFrameHeightTest() {
		assertEquals(GameAnimations.TROUT.getFrameHeight(),animation.getFrameHeight(),0);
	}
	
	/**
	* This method tests number of frames.
	*/
	@Test
	public void getNumberFramesTest() {
		assertEquals(GameAnimations.TROUT.getNumberFrames(),animation.getNumberFrames(),0);
	}
	
	/**
	* This method tests if it is facing left.
	*/
	@Test
	public void isFacingLeftTest() {
		assertEquals(GameAnimations.TROUT.isFacingLeft(),animation.isFacingLeft());
	}

	/**
	 * this method tests the update method for looping the frames
	 */
	@Test
	public void updateTest() {
		for(int i = animation.getNumberFrames(); i == 0; i--) {
			animation.update();
		}
		AnimationLoader.getInstance().loadAnimation(GameAnimations.TROUT);
		assertEquals(AnimationLoader.getInstance().getAnimation(GameAnimations.TROUT)[0],animation.getCurrentFrame());
	}
}
