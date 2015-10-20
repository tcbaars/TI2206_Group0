package states;

import java.awt.Graphics2D;

import keys.Key;
import keys.TypedKey;

public interface State {

    void drawToScreen(Graphics2D screen);
    void update();
    void handleKeyPressed(Key key);
    void handleKeyReleased(Key key);
    void handleKeyTyped(TypedKey key);
}
