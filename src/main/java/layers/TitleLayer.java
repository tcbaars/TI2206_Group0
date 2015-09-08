package layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import enumerations.Key;

public class TitleLayer extends Layer {

    private int selected;
    private String[] options = { "New Game", "Instructions", "Exit" };

    private Color titleColor;
    private Font titleFont;

    private Color optionColor;
    private Color selectedColor;
    private Font optionFont;

    public TitleLayer() {
        selected = 0;

        titleColor = Color.WHITE;
        titleFont = new Font("Times New Roman", Font.PLAIN, 32);

        optionColor = Color.WHITE;
        selectedColor = Color.GREEN;
        optionFont = new Font("Times New Roman", Font.PLAIN, 16);
    }

    private void select() {
        System.out.println(options[selected]);
        if (selected == 0) {
            addLayer(new GameLayer());
            deactivate();
            setVisible(false);
        } else if (selected == 1) {
            addLayer(new InstructionsLayer());
            deactivate();
            setVisible(false);
        } else if (selected == 2) {
            System.exit(0);
        }
    }

    @Override
    public void update() {
        updateNext();
    }

    @Override
    public void draw(Graphics2D g) {
        if (isVisible()) {
            g.setColor(titleColor);
            g.setFont(titleFont);
            g.drawString("Fishy", 20, 20);

            g.setFont(optionFont);
            for (int i = 0; i < options.length; i++) {
                g.setColor(optionColor);
                if (selected == i) {
                    g.setColor(selectedColor);
                }
                g.drawString(options[i], 20, 40 + (20 * i));
            }
        }
        drawNext(g);
    }

    @Override
    public void keyPressed(Key e) {
        if (isActive()) {
            switch (e) {
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
            }
        }
        passKeyPress(e);
    }

    @Override
    public void keyReleased(Key e) {
        passKeyRelease(e);
    }
}
