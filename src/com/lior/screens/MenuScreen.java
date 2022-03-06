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
    /** The main label. */
    private Label destinyLabel;

    /** The button to launch a singleplayer game. */
    private TextButton singlePlayer;

    /** The button to go to the settings menu. */
    private ImageButton settings;

    /** The singleton AssetHandler instance used to access our assets. */
    private AssetHandler assetHandler = AssetHandler.getInstance();

    /** The singleton reference to the ScreenHandler class. */
    private static ScreenHandler screenHandler = ScreenHandler.getInstance();

    /** Constructs a new MenuScreen. */
    public MenuScreen() {
        Gdx.graphics.setDisplayMode(600,
                600, false);
        stage = new Stage();
        destinyLabel = new Label("Welcome to Lior Final Project!", assetHandler.getSkin().get("large", Label.LabelStyle.class));
        singlePlayer = new TextButton("Singleplayer", assetHandler.getSkin());
        this.setDrawBehavior(new DrawBeige(stage));
    }

    /** Constructor for testing purposes only. */
    public MenuScreen(Stage stage, Label label, TextButton button,
                      ImageButton settings) {
        this.stage = stage;
        this.destinyLabel = label;
        this.singlePlayer = button;
        this.settings = settings;
        this.setDrawBehavior(new DrawBeige(stage));
    }

    @Override
    public void create() {
        super.create();

        setDestinyLabelLocation();
        setSinglePlayerLocation();
        addActors();
        setupListeners();
    }

    /** Sets the location of the destinyLabel. */
    private void setDestinyLabelLocation() {
        destinyLabel.setX(TwentyFourtyGame.GAME_WIDTH / 2
                - destinyLabel.getWidth() / 2);
        destinyLabel.setY(TwentyFourtyGame.GAME_HEIGHT / 2
                + destinyLabel.getHeight() + 8 * TwentyFourtyGame.GAP);
    }

    /** Sets the location of the singleplayer button. */
    private void setSinglePlayerLocation() {
        singlePlayer.setWidth(220);
        singlePlayer.setX(TwentyFourtyGame.GAME_WIDTH / 2
                - singlePlayer.getWidth() / 2);
        singlePlayer.setY(destinyLabel.getY() - destinyLabel.getHeight() - 6
                * TwentyFourtyGame.GAP);
    }

    /** Adds all required actors to stage. */
    private void addActors() {
        stage.addActor(destinyLabel);
        stage.addActor(singlePlayer);
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
