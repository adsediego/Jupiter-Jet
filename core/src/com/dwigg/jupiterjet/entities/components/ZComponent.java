package com.dwigg.jupiterjet.entities.components;

import com.badlogic.ashley.core.Component;

public class ZComponent implements Component {

    public int z = 0;

    public ZComponent(int z) {
        this.z = z;
    }
}
