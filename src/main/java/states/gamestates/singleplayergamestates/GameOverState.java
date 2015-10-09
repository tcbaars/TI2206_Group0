package states.gamestates.singleplayergamestates;

import java.awt.Graphics2D;

import enumerations.GameSounds;
import enumerations.MenuItems;
import enumerations.States;
import games.SinglePlayerGame;
import games.singleplayergames.ClassicGame;
import highscores.HighScores;
import highscores.Score;
import keys.Key;
import keys.TypedKey;
import layers.gamelayers.GameOverLayer;
import states.gamestates.GameState;
import tools.resourcetools.SoundLoader;
import tools.resourcetools.SoundPlayer;

public class GameOverState extends GameState{

    private SinglePlayerGame game;
    private GameOverLayer gameOverLayer;

    public GameOverState(SinglePlayerGame game) {
        super();
        this.game = game;
        SoundLoader.getInstance().loadSound(GameSounds.NAVIGATE);
        SoundLoader.getInstance().loadSound(GameSounds.SELECT);
        SoundLoader.getInstance().loadSound(GameSounds.WIN);
        SoundLoader.getInstance().loadSound(GameSounds.LOSE);
        gameOverLayer = createLayer(game);
    }

    protected GameOverLayer createLayer(SinglePlayerGame game){
        SoundPlayer.getInstance().playSound(GameSounds.LOSE);
        return new GameOverLayer("Game Over", game);
    }

    public void drawToScreen(Graphics2D screen) {
        gameOverLayer.drawToScreen(screen);
    }

    private void navigateUp(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        gameOverLayer.navigateUp();
    }
    private void navigateDown(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        gameOverLayer.navigateDown();
    }
    private void navigateLeft(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        gameOverLayer.navigateLeft();
    }
    private void navigateRight(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        gameOverLayer.navigateRight();
    }
    private void select(){
        SoundPlayer.getInstance().playSound(GameSounds.SELECT);
        MenuItems selection = gameOverLayer.getSelection();
        switch(selection){
            case INCREMENT:
                gameOverLayer.incrementSelection();
                break;
            case SET_VALUE:
                gameOverLayer.incrementSelection();
                break;
            case DECREMENT:
                gameOverLayer.decrementSelection();
                break;
            case RESTART:
                addScore();
                launchNewGame(new ClassicGame());
                break;
            case TITLE_SCREEN:
                addScore();
                setCurrentState(States.TITLE_SCREEN);
                break;
            case EXIT:
                addScore();
                setCurrentState(States.EXIT_SCREEN);
                break;
            default:
                break;
        }
    }
    private void escape(){
        addScore();
        setCurrentState(States.TITLE_SCREEN);
    }
    private void addScore(){
        String name = gameOverLayer.getName();
        int playerScore = game.getPlayer().getCurrentScore();
        Score score = new Score(name, playerScore);
        HighScores.getInstance().addScore(score);
    }
    private void setValue(char value){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        gameOverLayer.setValue(value);
    }
    @Override
    public void handleKeyReleased(Key key) {
        switch (key.getKey()) {
            case UP:
                navigateUp();
                break;
            case LEFT:
                navigateLeft();
                break;
            case RIGHT:
                navigateRight();
                break;
            case DOWN:
                navigateDown();
                break;
            case ENTER:
                select();
                break;
            case ESC:
                escape();
            default:
                super.handleKeyReleased(key);
        }
    }
    @Override
    public void handleKeyTyped(TypedKey key){
        setValue(key.getValue());
    }
}
