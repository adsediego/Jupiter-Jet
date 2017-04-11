package com.dwigg.jupiterjet.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHelper implements InputProcessor {

    private OrthographicCamera camera;

    private InputListener listener;

    public InputHelper(OrthographicCamera camera, InputListener listener) {
        this.camera = camera;
        this.listener = listener;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            listener.moveLeft();
        } else if (keycode == Input.Keys.D) {
            listener.moveRight();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            listener.stopMoving();
        } else if (keycode == Input.Keys.D) {
            listener.stopMoving();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPosition);

        if (touchPosition.x < Constants.V_WIDTH / 2) {
            listener.moveLeft();
        } else if (touchPosition.x > Constants.V_WIDTH / 2) {
            listener.moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        listener.stopMoving();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
