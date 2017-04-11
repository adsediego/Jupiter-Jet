package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.dwigg.jupiterjet.entities.components.PositionComponent;
import com.dwigg.jupiterjet.entities.components.RenderableComponent;
import com.dwigg.jupiterjet.entities.components.TextureComponent;
import com.dwigg.jupiterjet.utils.ZComparator;

public class RenderSystemV2 extends IteratingSystem {

    private SpriteBatch spriteBatch;
    private Array<Entity> renderQue;
    private ZComparator comparator;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<PositionComponent> positionMapper;

    public RenderSystemV2(SpriteBatch spriteBatch) {
        super(Family.all(
                TextureComponent.class,
                PositionComponent.class,
                RenderableComponent.class
                ).get()
        );

        this.spriteBatch = spriteBatch;
        renderQue = new Array<Entity>();
        comparator = new ZComparator();

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        renderQue.sort(comparator);

        for (Entity entity : renderQue) {
            TextureComponent texture = textureMapper.get(entity);
            PositionComponent position = positionMapper.get(entity);

            spriteBatch.begin();
            spriteBatch.draw(texture.texture, position.x, position.y);
            spriteBatch.end();
        }

        renderQue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQue.add(entity);
    }
}
