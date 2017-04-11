package com.dwigg.jupiterjet.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.level.Level;

public class GameScreen implements Screen {

    private MainGame game;

    private Level level;

    private Sound blipSound = Gdx.audio.newSound(Gdx.files.internal("Blip_Select.wav"));

    public GameScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        blipSound.play();

        level = new Level(game);
    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        draw();

        if (!game.isPaused()) {
            update(delta);
        }
    }

    private void update(float delta) {
        game.getCamera().update();
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getShapeRenderer().setProjectionMatrix(game.getCamera().combined);

        level.update(delta);
    }

    private void draw() {
        level.draw(game.getSpriteBatch());
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
        level.dispose();
        blipSound.dispose();
    }
}
