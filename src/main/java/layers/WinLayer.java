package layers;

import enumerations.Key;
import handlers.GameHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class WinLayer extends Layer{

  private GameHandler game;
  private Color textColor;
  private Font textFont;
  
  /**
   * Display win screen.
   */
  public WinLayer(GameHandler game) {
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
    graphic.drawString("Win", 20, 20);
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
