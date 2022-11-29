package com.engeto.plants;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watered;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watered, int frequencyOfWatering) throws PlantException {


        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatered(watered);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
//        this.name = name;
//        this.planted = planted;
//        setFrequencyOfWatering(frequencyOfWatering);
//        this.notes = "";
//        this.watered = LocalDate.now();
    }

    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
//        this.name = name;
//        this.planted = LocalDate.now();
//        this.frequencyOfWatering = 7;
//        this.notes = "";
//        this.watered = LocalDate.now();
    }

    ///region

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatered() {
        return watered;
    }

    public void setWatered(LocalDate watered) throws PlantException {
        if (watered.isBefore(getPlanted())) {
            throw new PlantException("Datum poslední zálivky nesmí být starší než datum zasazení rostliny");
        }
        this.watered = watered;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException("Frekvence zálivky nesmí být menší než 1! Zadaná hodnota: " + frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    ///endregion
    public String getWateringInfo() {
        return "------------------------------\nNázev květiny: " + getName() + "\nDatum Poslední zálivky: " + getWatered() + "\nDoporučené datum příští zálivky: " + getWatered().plusDays(getFrequencyOfWatering()) + "\n------------------------------";
    }

    @Override
    public int compareTo(Plant secondPlant) {

        return this.getName().compareTo(secondPlant.getName());
    }

    @Override
    public String toString() {
        return name + "  " + watered;
    }
    @Override
    public boolean equals(Object second) {
        if (this == second) return  true;
        if (second == null || getClass()!= second.getClass()) return false;
        Plant plant = (Plant) second;
        return Objects.equals(getWatered(), ((Plant) second).getWatered());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getWatered());
    }

    public String wasWateredLastWeek() {

        if (ChronoUnit.DAYS.between(this.watered, LocalDate.now()) < 7) return  "Rostlina: "+ this.name + " Byla zalita";
        else return  "Rostlina: "+ this.name + " Nebyla zalita";
    }
}