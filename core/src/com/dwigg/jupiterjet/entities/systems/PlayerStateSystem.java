package com.dwigg.jupiterjet.entities.systems;

import com.badlogic.ashley.core.*;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.entities.components.PlayerComponent;
import com.dwigg.jupiterjet.entities.components.StateComponent;
import com.dwigg.jupiterjet.screens.GameOverScreen;

public class PlayerStateSystem extends EntitySystem {

    private MainGame game;

    private Entity player;

    private ComponentMapper<StateComponent> stateMapper;

    public PlayerStateSystem(MainGame game) {
        this.game = game;

        stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        StateComponent state = stateMapper.get(player);

        if (state.state.equals("DEAD")) {
            game.setScreen(new GameOverScreen(game));
        }
    }
}
