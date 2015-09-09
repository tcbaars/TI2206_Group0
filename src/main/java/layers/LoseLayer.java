package layers;

import enumerations.Key;
import handlers.GameHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LoseLayer extends Layer{
  
  private GameHandler game;
  private Color textColor;
  private Font textFont;
  
  /**
   * Displays you lost screen.
   */
  public LoseLayer(GameHandler game) {
    this.game = game;
    textColor = Color.WHITE;
    textFont = new Font("Times New Roman", Font.PLAIN, 16);
  }
  
  private void newGame() {
    addLayer(new GameLayer());
    removeLayer();
  }
  
  @Override
  protected void update() {
    // TODO Auto-generated method stub
  }
  
  @Override
  public Graphics2D draw(Graphics2D graphic) {
    graphic.setColor(textColor);
    graphic.setFont(textFont);
    graphic.drawString("Lose", 20, 20);
    return graphic;
  }
  
  @Override
  public void keyPressed(Key key) {
    // TODO Auto-generated method stub
  }
  
  @Override
  public void keyReleased(Key key) {
    switch (key) {
      case ESC:
        System.exit(0);
        break;
      case ENTER:
        newGame();
        break;
      default: 
    }
  }

}
