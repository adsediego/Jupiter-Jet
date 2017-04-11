package com.dwigg.jupiterjet.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.managers.BackgroundManager;
import com.dwigg.jupiterjet.managers.EntityManager;
import com.dwigg.jupiterjet.managers.HudManager;

public class Level {

    private BackgroundManager background;
    private EntityManager entities;
    private HudManager hud;

    public Level(MainGame game) {
        background = new BackgroundManager();
        entities = new EntityManager(game);
        hud = new HudManager(game, entities.getPlayer());
    }

    public void update(float delta) {
        background.update(delta);
        hud.update(delta);
        hud.update(delta);
        entities.update(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        background.draw(spriteBatch);
        spriteBatch.end();

        hud.draw();
    }

    public void dispose() {
        background.dispose();
        entities.dispose();
        hud.dispose();
    }
}
