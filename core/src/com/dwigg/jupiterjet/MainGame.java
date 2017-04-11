package com.dwigg.jupiterjet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dwigg.jupiterjet.screens.GameScreen;
import com.dwigg.jupiterjet.screens.MainMenuScreen;
import com.dwigg.jupiterjet.utils.Constants;

public class MainGame extends Game {

	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private boolean paused;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
		paused = false;

		camera.position.set(
				viewport.getWorldWidth() / 2,
				viewport.getWorldHeight() / 2,
				0
		);

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		shapeRenderer.dispose();
	}

	public void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public boolean isPaused() {
		return paused;
	}
}
