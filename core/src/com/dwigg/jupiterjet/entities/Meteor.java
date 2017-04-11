package com.dwigg.jupiterjet.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.dwigg.jupiterjet.entities.components.*;
import com.dwigg.jupiterjet.utils.Constants;

public class Meteor extends Entity {

    private Texture texture;
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float velX = 0.0f;
    private float velY = 0.0f;

    public Meteor(Texture texture, float posX, float posY, float velX, float velY) {
        this.texture = texture;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;

        addComponent();
    }

    private void addComponent() {
        VelocityComponent velocityComponent = new VelocityComponent();
        velocityComponent.x = velX;
        velocityComponent.y = velY;

        this.add(new TextureComponent(texture));
        this.add(new PositionComponent(posX, posY));
        this.add(velocityComponent);
        this.add(new ZComponent(0));
        this.add(new RenderableComponent());
        this.add(new MeteorComponent());
        this.add(new BoundsComponent(Constants.METEOR_WIDTH, Constants.METEOR_HEIGHT));
    }
}
