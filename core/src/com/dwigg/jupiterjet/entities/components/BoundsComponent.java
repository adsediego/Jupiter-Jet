package com.dwigg.jupiterjet.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent implements Component {

    public Rectangle rectangle;

    public BoundsComponent(float width, float height) {
        rectangle = new Rectangle(0, 0, width, height);
    }
}
