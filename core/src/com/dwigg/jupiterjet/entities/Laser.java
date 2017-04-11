package com.dwigg.jupiterjet.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.dwigg.jupiterjet.entities.components.*;
import com.dwigg.jupiterjet.utils.Constants;

public class Laser extends Entity {

    private Texture texture;
    private float x = 0.0f;
    private float y = 0.0f;

    private final float LASER_VELOCITY = 10f * 60;

    public Laser(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;

        addComponent();
    }

    private void addComponent() {
        VelocityComponent velocityComponent = new VelocityComponent();
        velocityComponent.y = LASER_VELOCITY;

        this.add(new TextureComponent(texture));
        this.add(new PositionComponent(x, y));
        this.add(new ZComponent(0));
        this.add(velocityComponent);
        this.add(new RenderableComponent());
        this.add(new LaserComponent());
        this.add(new BoundsComponent(Constants.LASER_WIDTH, Constants.LASER_HEIGHT));
    }
}
