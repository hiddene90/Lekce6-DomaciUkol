package com.engeto.plants;

import java.util.Comparator;

public class ComparatorWatered implements Comparator<Plant> {
    @Override
    public int compare(Plant plant1, Plant plant2) {

        return plant1.getWatered().compareTo(plant2.getWatered());
    }
}
