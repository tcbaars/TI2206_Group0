package layers;

import enumerations.Key;
import handlers.GameHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PauseLayer extends Layer{

  private GameHandler game;
  private Color textColor;
  private Font textFont;
  
  /**
   * Layer to display pause screen.
   */
  public PauseLayer(GameHandler game) {
    this.game = game;
    textColor = Color.WHITE;
    textFont = new Font("Times New Roman", Font.PLAIN, 16);
  }
  
  @Override
  protected void update() {
    if (!game.isPaused()) {
      removeLayer();
    }
  }
  
  @Override
  public Graphics2D draw(Graphics2D graphic) {
    graphic.setColor(textColor);
    graphic.setFont(textFont);
    graphic.drawString("Paused", 20, 20);
    return graphic;
  }
  
  @Override
  public void keyPressed(Key key) {
  }
  
  @Override
  public void keyReleased(Key key) {
    switch (key) {
      case ESC:
        game.resume();
        break;
      default :
    }
  }


}
