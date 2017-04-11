package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.dwigg.jupiterjet.entities.components.LaserComponent;
import com.dwigg.jupiterjet.entities.components.MeteorComponent;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.utils.Constants;

public class EntityRemovalSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper;

    public EntityRemovalSystem() {
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.one(
                        LaserComponent.class,
                        MeteorComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            PositionComponent position = positionMapper.get(entity);

            if (position.y > Constants.V_HEIGHT + 500 ||
                    position.y < -500 ||
                    position.x > Constants.V_WIDTH + 500 ||
                    position.x < -500) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
