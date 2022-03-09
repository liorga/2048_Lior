package com.lior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lior.TwentyFourtyGame;
import com.lior.drawbehavior.DrawBeige;
import com.lior.handlers.AssetHandler;
import com.lior.handlers.ScreenHandler;

public class MenuScreen extends Screen {

    private Label mainLabel;

    private TextButton singlePlayer;

    private AssetHandler assetHandler = AssetHandler.getInstance();

    private static ScreenHandler screenHandler = ScreenHandler.getInstance();

    public MenuScreen() {
        Gdx.graphics.setDisplayMode(600,
                600, false);
        stage = new Stage();
        mainLabel = new Label("Welcome to Lior Final Project!", assetHandler.getSkin().get("large", Label.LabelStyle.class));
        singlePlayer = new TextButton("Play!", assetHandler.getSkin());
        this.setDrawBehavior(new DrawBeige(stage));
    }

    @Override
    public void create() {
        super.create();
        setMainLabelLocation();
        setSinglePlayerLocation();
        stage.addActor(mainLabel);
        stage.addActor(singlePlayer);
        setupListeners();
    }

    /** Sets the location of the destinyLabel. */
    private void setMainLabelLocation() {
        mainLabel.setX(TwentyFourtyGame.GAME_WIDTH / 2
                - mainLabel.getWidth() / 2);
        mainLabel.setY(TwentyFourtyGame.GAME_HEIGHT / 2
                + mainLabel.getHeight() + 8 * TwentyFourtyGame.GAP);
    }

    /** Sets the location of the singleplayer button. */
    private void setSinglePlayerLocation() {
        singlePlayer.setWidth(220);
        singlePlayer.setX(TwentyFourtyGame.GAME_WIDTH / 2
                - singlePlayer.getWidth() / 2);
        singlePlayer.setY(mainLabel.getY() - mainLabel.getHeight() - 6
                * TwentyFourtyGame.GAP);
    }

    /** Sets the listeners for all buttons. */
    private void setupListeners() {
        singlePlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenHandler.set(new GameScreen());
            }
        });
    }
}
