package com.lior;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.lior.handlers.AssetHandler;
import com.lior.handlers.ScreenHandler;
import com.lior.screens.MenuScreen;
import com.lior.states.*;

public class TwentyFourtyGame extends Game {

    private static final String CLASSNAME = TwentyFourtyGame.class
            .getSimpleName();

    /** The width of the game */
    public static final int GAME_WIDTH = 600;

    /** The height of the game */
    public static final int GAME_HEIGHT = 600;

    /** The gap between all the elements. */
    public static final int GAP = 15;

    private static GameState curState;

    private static AssetHandler assetHandler = AssetHandler.getInstance();

    private static ScreenHandler screenHandler = ScreenHandler.getInstance();

    private static Logger logger = Logger.getInstance();

    @Override
    public void create() {
        logger.info(CLASSNAME, "Starting  Game...");
        logger.debug(CLASSNAME, "Skin is loaded and menu screen is launched.");

        /* Load all our assets. */
        assetHandler.load();
        assetHandler.loadSkinFile(Gdx.files.internal("skin.json"));

        /* Show a menu screen. */
        screenHandler.set(new MenuScreen());
    }

    @Override
    public void render() {
        super.render();
        screenHandler.update();
        screenHandler.draw();
    }

    @Override
    public void dispose() {
        logger.info(CLASSNAME, "Closing game...");
        screenHandler.dispose();
        assetHandler.dispose();
        logger.dispose();
    }

    /**
     * Sets the new state of the game.
     *
     * @param state
     *            The new state of the game.
     */
    public static void setState(GameState state) {
        logger.info("TwentyFourtyGame", "Changing current game state to '"
                + state.getClass().getSimpleName() + "'.");
        curState = state;
    }


    /**
     * @return The current game state.
     */
    public static GameState getState() {
        return curState;
    }

    /**
     * @return True if the current game is lost.
     */
    public static boolean isLost() {
        return curState instanceof LostState;
    }
}