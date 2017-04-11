package com.dwigg.jupiterjet.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.managers.BackgroundManager;
import com.dwigg.jupiterjet.utils.Constants;

public class GameOverScreen implements Screen {

    private MainGame game;

    private BackgroundManager background;

    private Sprite gameOverSprite;

    public GameOverScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new BackgroundManager();

        gameOverSprite = new Sprite(new Texture("game_over.png"));
        gameOverSprite.setPosition(0, Constants.V_HEIGHT / 2);
    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        draw();

        if (!game.isPaused()) {
            update(delta);
        }
    }

    private void draw() {
        game.getSpriteBatch().begin();
        background.draw(game.getSpriteBatch());
        gameOverSprite.draw(game.getSpriteBatch());
        game.getSpriteBatch().end();
    }

    private void update(float delta) {
        game.getCamera().update();
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);

        background.update(delta);

        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOverSprite.getTexture().dispose();
    }
}
