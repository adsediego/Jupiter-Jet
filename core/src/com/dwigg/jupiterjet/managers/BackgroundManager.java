package com.dwigg.jupiterjet.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dwigg.jupiterjet.utils.Constants;

public class BackgroundManager {

    private Sprite backgroundSprite1;
    private Sprite backgroundSprite2;

    public BackgroundManager() {
        Texture texture = new Texture("background.png");

        backgroundSprite1 = new Sprite(texture);
        backgroundSprite1.setPosition(0, 0);
        backgroundSprite1.setSize(Constants.V_WIDTH, Constants.V_HEIGHT);

        backgroundSprite2 = new Sprite(texture);
        backgroundSprite2.setPosition(0, backgroundSprite1.getHeight());
        backgroundSprite2.setSize(Constants.V_WIDTH, Constants.V_HEIGHT);
    }

    public void update(float delta) {
        float bg1NewY = backgroundSprite1.getY() - 16f * delta;
        float bg2NewY = backgroundSprite2.getY() - 16f * delta;

        backgroundSprite1.setY(bg1NewY);
        backgroundSprite2.setY(bg2NewY);

        if (backgroundSprite1.getY() < -backgroundSprite1.getHeight()) {
            backgroundSprite1.setY(backgroundSprite2.getY() + backgroundSprite2.getHeight());
        }

        if (backgroundSprite2.getY() < -backgroundSprite2.getHeight()) {
            backgroundSprite2.setY(backgroundSprite1.getY() + backgroundSprite1.getHeight());
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        backgroundSprite1.draw(spriteBatch);
        backgroundSprite2.draw(spriteBatch);
    }

    public void dispose() {
        backgroundSprite1.getTexture().dispose();
        backgroundSprite2.getTexture().dispose();
    }
}
