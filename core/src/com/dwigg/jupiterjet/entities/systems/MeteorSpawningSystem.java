package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dwigg.jupiterjet.entities.Meteor;
import com.dwigg.jupiterjet.utils.Constants;

public class MeteorSpawningSystem extends EntitySystem {

    private Texture texture;
    private float posX = 0;
    private float posY = 0;
    private float velX = 0;
    private float velY = 0;
    private float spawnTimer = 0;
    private float spawnRate = 0.5f;

    public MeteorSpawningSystem(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void update(float deltaTime) {
        spawnTimer += deltaTime;

        if (spawnTimer > spawnRate) {
            int origin = MathUtils.random(2);

            if (origin == 0) { // TOP
                posX = MathUtils.random(Constants.V_WIDTH);
                posY = Constants.V_HEIGHT + texture.getHeight();
                velX = MathUtils.random(-3f * 60, 3f * 60);
                velY = MathUtils.random(-5f * 60, -3f * 60);
            } else if (origin == 1) { // LEFT
                posX = -texture.getWidth();
                posY = MathUtils.random(Constants.V_HEIGHT / 2, Constants.V_HEIGHT);
                velX = MathUtils.random(3f * 60, 3.5f * 60);
                velY = MathUtils.random(-3.5f * 60, -3f * 60);
            } else if (origin == 2) { // RIGHT
                posX = Constants.V_WIDTH + texture.getWidth();
                posY = MathUtils.random(Constants.V_HEIGHT / 2, Constants.V_HEIGHT);
                velX = MathUtils.random(-3.5f * 60, -3f * 60);
                velY = MathUtils.random(-3.5f * 60, -3f * 60);
            }

            Meteor meteor = new Meteor(texture, posX, posY, velX, velY);
            getEngine().addEntity(meteor);

            spawnTimer = 0;
        }
    }
}
