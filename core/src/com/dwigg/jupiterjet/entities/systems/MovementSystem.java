package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.entities.components.VelocityComponent;

public class MovementSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;

    public MovementSystem() {
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        PositionComponent.class,
                        VelocityComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            PositionComponent position = positionMapper.get(entity);
            VelocityComponent velocity = velocityMapper.get(entity);

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;
        }
    }
}
