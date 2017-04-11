package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.dwigg.jupiterjet.entities.Laser;
import com.dwigg.jupiterjet.entities.components.PlayerComponent;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.utils.Constants;

public class AutoFireSystem extends EntitySystem {

    private Entity entity;

    private ComponentMapper<PositionComponent> positionMapper;

    private Texture texture;

    private float fireTimer = 0.0f;
    private float fireInterval = 0.4f;

    private Sound laserSound;

    public AutoFireSystem(Texture texture, Sound laserSound) {
        this.laserSound = laserSound;
        this.texture = texture;

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entity = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        fireTimer += deltaTime;

        if (fireTimer > fireInterval) {

            PositionComponent position = positionMapper.get(entity);

            float x = (position.x + Constants.PLAYER_WIDTH / 2) - (texture.getWidth() / 2);
            float y = position.y + Constants.PLAYER_HEIGHT;

            Laser laser = new Laser(texture, x, y);
            getEngine().addEntity(laser);

            laserSound.play();

            fireTimer = 0;
        }
    }
}
