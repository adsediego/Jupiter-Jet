package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.entities.components.RenderableComponent;
import com.dwigg.jupiterjet.entities.components.TextureComponent;

public class RenderSystem extends EntitySystem {

    private SpriteBatch spriteBatch;

    private ImmutableArray<Entity> entities;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<PositionComponent> positionMapper;

    public RenderSystem(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        TextureComponent.class,
                        PositionComponent.class,
                        RenderableComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            TextureComponent texture = textureMapper.get(entity);
            PositionComponent position = positionMapper.get(entity);

            spriteBatch.begin();
            spriteBatch.draw(texture.texture, position.x, position.y);
            spriteBatch.end();
        }
    }
}
