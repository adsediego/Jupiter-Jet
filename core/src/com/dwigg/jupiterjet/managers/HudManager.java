package com.dwigg.jupiterjet.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.entities.components.ScoreComponent;
import com.dwigg.jupiterjet.utils.Constants;

public class HudManager {

    private MainGame game;

    private Entity entity;

    private Stage stage;
    private int score = 0;
    private Label scoreLabel;
    private BitmapFont font;

    public HudManager(MainGame game, Entity entity) {
        this.entity = entity;

        font = new BitmapFont(Gdx.files.internal("digi.fnt"));

        stage = new Stage(game.getViewport(), game.getSpriteBatch());

        scoreLabel = new Label(
                String.format("%06d", score),
                new Label.LabelStyle(font, Color.WHITE)
        );
        scoreLabel.setPosition(
                Constants.V_WIDTH / 2 - scoreLabel.getWidth() / 2,
                Constants.V_HEIGHT - scoreLabel.getHeight() * 2
        );

        stage.addActor(scoreLabel);
    }

    public void update(float delta) {
        stage.act(delta);

        score = entity.getComponent(ScoreComponent.class).score;
        scoreLabel.setText(String.format("%06d", score));
    }

    public void draw() {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
