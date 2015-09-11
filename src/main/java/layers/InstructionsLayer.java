package layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import enumerations.Key;
import handlers.FontOutlineHandler;

public class InstructionsLayer extends Layer {

    private final String titleText = "INSTRUCTIONS";
    private final Color titleFillColor = Color.WHITE;
    private final Color titleOutlineColor = Color.BLACK;
    private final float titleOutlineSize = 2;
    private final Font titleFont = new Font("Times New Roman", Font.BOLD, 100);

    private final int titleStartY = 125;

    private final String optionText = "BACK";
    private final Color selectedFillColor = Color.YELLOW;
    private final Color optionOutlineColor = Color.BLACK;
    private final float optionOutlineSize = 1;
    private final Font optionFont = new Font("Times New Roman", Font.BOLD, 85);

    private final int optionStartX = 300;
    private final int optionStartY = 300;

    public InstructionsLayer(){
        super();
    }

    private void select() {
        addLayer(new TitleLayer());
        removeLayer();
    }

    @Override
    public void update() {}

    @Override
    public Graphics2D draw(Graphics2D graphic) {
        FontOutlineHandler.drawTextCenterWidth(graphic, titleFont, titleText, titleFillColor, titleOutlineColor, titleOutlineSize, titleStartY);
        FontOutlineHandler.drawText(graphic, optionFont, optionText, selectedFillColor, optionOutlineColor, optionOutlineSize, optionStartX, optionStartY);
        return graphic;
    }

    @Override
    public void keyPressed(Key key) {
        switch (key) {
        case ENTER:
        case ESC:
            select();
            break;
        default:
            break;
        }
    }

    @Override
    public void keyReleased(Key key) {
    }

}
