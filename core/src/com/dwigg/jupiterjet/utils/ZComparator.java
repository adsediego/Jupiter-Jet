package com.dwigg.jupiterjet.utils;

import com.badlogic.ashley.core.Entity;
import com.dwigg.jupiterjet.entities.components.ZComponent;

import java.util.Comparator;

public class ZComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity entity1, Entity entity2) {
        return entity1.getComponent(ZComponent.class).z -
                entity2.getComponent(ZComponent.class).z;
    }
}
