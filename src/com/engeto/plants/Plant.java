package com.engeto.plants;

import java.time.LocalDate;

public class Plant {
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
        this.name = name;
        this.planted = planted;
        setFrequencyOfWatering(frequencyOfWatering);
        this.notes = "";
        this.watered = LocalDate.now();
    }

    public Plant(String name) {
        this.name = name;
        this.planted = LocalDate.now();
        this.frequencyOfWatering = 7;
        this.notes = "";
        this.watered = LocalDate.now();
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
        if(watered.isBefore(getPlanted())){
            throw new PlantException("Datum poslední zálivky nesmí být starší než datum zasazení rostliny");
        }
        this.watered = watered;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if(frequencyOfWatering<1){
            throw new PlantException("Frekvence zálivky nesmí být menší než 1! Zadaná hodnota: " + frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }
    ///endregion
    public String getWateringInfo(){
        return "------------------------------\nNázev květiny: "+getName()+"\nDatum Poslední zálivky: "+ getWatered()+"\nDoporučené datum příští zálivky: "+ getWatered().plusDays(getFrequencyOfWatering())+"\n------------------------------";
    }
}
