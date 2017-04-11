package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.dwigg.jupiterjet.entities.components.*;

public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> lasers;
    private ImmutableArray<Entity> meteors;
    private Entity player;

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<BoundsComponent> boundsMapper;
    private ComponentMapper<StateComponent> stateMapper;
    private ComponentMapper<ScoreComponent> scoreMapper;

    private Sound explosionSound;

    public CollisionSystem(Sound explosionSound) {
        this.explosionSound = explosionSound;

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
        scoreMapper = ComponentMapper.getFor(ScoreComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        lasers = engine.getEntitiesFor(
                Family.all(
                    LaserComponent.class
                ).get()
        );

        meteors = engine.getEntitiesFor(
                Family.all(
                        MeteorComponent.class
                ).get()
        );

        player = engine.getEntitiesFor(
                Family.all(
                        PlayerComponent.class
                ).get()
        ).first();
    }

    @Override
    public void update(float deltaTime) {
        checkForLaserCollision();
        checkForJetCollision();
    }

    private void checkForJetCollision() {
        StateComponent state = stateMapper.get(player);
        PositionComponent playerPosition = positionMapper.get(player);
        BoundsComponent playerBounds = boundsMapper.get(player);
        playerBounds.rectangle.x = playerPosition.x;
        playerBounds.rectangle.y = playerPosition.y;

        for (Entity meteor : meteors) {
            PositionComponent meteorPosition = positionMapper.get(meteor);
            BoundsComponent meteorBounds = boundsMapper.get(meteor);
            meteorBounds.rectangle.x = meteorPosition.x;
            meteorBounds.rectangle.y = meteorPosition.y;

            if (playerBounds.rectangle.overlaps(meteorBounds.rectangle)) {
                getEngine().removeEntity(meteor);
                state.state = "DEAD";
            }
        }
    }

    private void checkForLaserCollision() {
        for (int i = 0; i < lasers.size(); ++i) {
            Entity laser = lasers.get(i);

            ScoreComponent score = scoreMapper.get(player);
            PositionComponent laserPosition = positionMapper.get(laser);
            BoundsComponent laserBounds = boundsMapper.get(laser);
            laserBounds.rectangle.x = laserPosition.x;
            laserBounds.rectangle.y = laserPosition.y;

            for (Entity meteor : meteors) {
                PositionComponent meteorPosition = positionMapper.get(meteor);
                BoundsComponent meteorBounds = boundsMapper.get(meteor);
                meteorBounds.rectangle.x = meteorPosition.x;
                meteorBounds.rectangle.y = meteorPosition.y;

                if (laserBounds.rectangle.overlaps(meteorBounds.rectangle)) {
                    getEngine().removeEntity(laser);
                    getEngine().removeEntity(meteor);
                    score.score++;

                    explosionSound.play();
                }
            }
        }
    }
}
