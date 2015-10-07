package states;

import java.awt.Graphics2D;

import keys.Key;
import keys.TypedKey;

public interface State {

    public void drawToScreen(Graphics2D screen);
    public void update();
    public void handleKeyPressed(Key key);
    public void handleKeyReleased(Key key);
    public void handleKeyTyped(TypedKey key);
}
