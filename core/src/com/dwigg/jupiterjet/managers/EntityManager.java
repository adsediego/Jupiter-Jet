package com.dwigg.jupiterjet.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dwigg.jupiterjet.MainGame;
import com.dwigg.jupiterjet.entities.Player;
import com.dwigg.jupiterjet.entities.components.PlayerComponent;
import com.dwigg.jupiterjet.entities.systems.*;
import com.dwigg.jupiterjet.utils.InputHelper;

public class EntityManager {

    private MainGame game;

    private Engine engine;

    private Player player;

    private Texture laserTexture = new Texture("laser.png");
    private Texture meteorTexture = new Texture("meteor_1.png");

    private Sound laserSound = Gdx.audio.newSound(Gdx.files.internal("Laser_Shoot6.wav"));
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("Explosion.wav"));

    public EntityManager(MainGame game) {
        this.game = game;

        engine = new Engine();

        player = new Player();

        addEntities();
        addSystem();
    }

    private void addSystem() {
        InputSystem inputSystem = new InputSystem();

        engine.addSystem(new RenderSystem(game.getSpriteBatch()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(inputSystem);
        engine.addSystem(new AutoFireSystem(laserTexture, laserSound));
        engine.addSystem(new MeteorSpawningSystem(meteorTexture));
        engine.addSystem(new EntityRemovalSystem());
        engine.addSystem(new CollisionSystem(explosionSound));
        engine.addSystem(new PlayerStateSystem(game));
        //engine.addSystem(new BoundsRendererSystem(game.getShapeRenderer()));

        Gdx.input.setInputProcessor(new InputHelper(game.getCamera(), inputSystem));
    }

    private void addEntities() {
        engine.addEntity(player);
    }

    public void update(float delta) {
        engine.update(delta);
    }

    public void dispose() {
        player.dispose();
        laserTexture.dispose();
        meteorTexture.dispose();
        laserSound.dispose();
        explosionSound.dispose();
    }

    public Entity getPlayer() {
        return engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }
}
