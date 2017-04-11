package com.dwigg.jupiterjet.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.dwigg.jupiterjet.entities.components.*;
import com.dwigg.jupiterjet.utils.Constants;

public class Player extends Entity {

    private Texture texture;
    private float x = 0.0f;
    private float y = 0.0f;

    public Player() {
        texture = new Texture("ship.png");
        x = (Constants.V_WIDTH / 2) - (texture.getWidth() / 2);
        y = 10;

        addComponent();
    }

    private void addComponent() {
        this.add(new TextureComponent(texture));
        this.add(new PositionComponent(x, y));
        this.add(new ZComponent(1));
        this.add(new VelocityComponent());
        this.add(new RenderableComponent());
        this.add(new PlayerComponent());
        this.add(new BoundsComponent(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        this.add(new StateComponent());
        this.add(new ScoreComponent());
    }

    public void dispose() {
        texture.dispose();
    }
}
