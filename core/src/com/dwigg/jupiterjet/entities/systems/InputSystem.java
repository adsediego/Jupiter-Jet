package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dwigg.jupiterjet.entities.Meteor;
import com.dwigg.jupiterjet.entities.components.PlayerComponent;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.entities.components.TextureComponent;
import com.dwigg.jupiterjet.entities.components.VelocityComponent;
import com.dwigg.jupiterjet.utils.Constants;
import com.dwigg.jupiterjet.utils.InputListener;

public class InputSystem extends EntitySystem implements InputListener {

    private Entity entity;

    private ComponentMapper<VelocityComponent> velocityMapper;

    private VelocityComponent velocity;

    public InputSystem() {
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entity = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void moveLeft() {
        velocity = velocityMapper.get(entity);
        velocity.x = -4f * 60;
    }

    @Override
    public void moveRight() {
        velocity = velocityMapper.get(entity);
        velocity.x = 4f * 60;
    }

    @Override
    public void stopMoving() {
        velocity = velocityMapper.get(entity);
        velocity.x = 0f;
    }
}
