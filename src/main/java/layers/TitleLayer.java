package layers;

import enumerations.Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TitleLayer extends Layer {

  private int selected;
  private String[] options = { "New Game", "Instructions", "Exit" };

  private Color titleColor;
  private Font titleFont;

  private Color optionColor;
  private Color selectedColor;
  private Font optionFont;

  /**
   * Title screen.
   */
  public TitleLayer() {
    selected = 0;

    titleColor = Color.WHITE;
    titleFont = new Font("Times New Roman", Font.PLAIN, 32);

    optionColor = Color.WHITE;
    selectedColor = Color.GREEN;
    optionFont = new Font("Times New Roman", Font.PLAIN, 16);
  }

  private void select() {
    if (selected == 0) {
      addLayer(new GameLayer());
      removeLayer();
    } else if (selected == 1) {
      addLayer(new InstructionsLayer());
      removeLayer();
    } else if (selected == 2) {
      System.exit(0);
    }
  }

  @Override
  public void update() {
  }

  @Override
  public Graphics2D draw(Graphics2D graphic) {

    graphic.setColor(titleColor);
    graphic.setFont(titleFont);
    graphic.drawString("Fishy", 20, 20);

    graphic.setFont(optionFont);
    for (int i = 0; i < options.length; i++) {
      graphic.setColor(optionColor);
      if (selected == i) {
        graphic.setColor(selectedColor);
      }
      graphic.drawString(options[i], 20, 40 + (20 * i));
    }

    return graphic;
  }

  @Override
  public void keyPressed(Key key) {
    if (isActive()) {
      switch (key) {
        case UP:
        case LEFT:
          selected = selected - 1;
          if (selected < 0) {
            selected = options.length - 1;
          }
          break;
        case DOWN:
        case RIGHT:
          selected = (selected + 1) % options.length;
          break;
        case ENTER:
          select();
          break;
        default:
      }
    }
  }

  @Override
  public void keyReleased(Key key) {
  }
}
