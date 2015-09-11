package layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import enumerations.Key;
import handlers.FontOutlineHandler;

public class InstructionsLayer extends Layer {

    private final String titletext = "INSTRUCTIONS";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    private final int ytitle = 125;

    private final String optiontext = "BACK";
    private final Color selectedfill = Color.YELLOW;
    private final Color optionoutline = Color.BLACK;
    private final float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    private final int xoption = 300;
    private final int yoption = 300;

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
        FontOutlineHandler.drawTextCenterWidth(graphic, titlefont, titletext, titlefill, titleoutline, titleoutlinesize, ytitle);
        FontOutlineHandler.drawText(graphic, optionfont, optiontext, selectedfill, optionoutline, optionoutlinesize, xoption, yoption);
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
