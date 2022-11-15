package com.engeto.plants;


import java.io.File;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static final String KVETINY = "kvetiny.txt";
    public static final String OUTPUT_FILE = "kvetiny-Updated.txt";
//    public static final String SPATNEDATUM = "kvetiny-spatne-datum.txt";
//    public static final String SPATNEFREKVENCE = "kvetiny-spatne-frekvence.txt";

    public static void main(String[] args) {

        ListOfPlants listOfPlants = new ListOfPlants();
        try {
            listOfPlants.readPlantFromFile(KVETINY);
//            listOfPlants.readPlantFromFile(SPATNEDATUM);
//            listOfPlants.readPlantFromFile(SPATNEFREKVENCE);
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru: " + e.getLocalizedMessage());
        }


        List<Plant> plants = listOfPlants.getlistOfPlants();
        //výpis informací o zálivce pro všechny květiny

        for (Plant plant : plants) {
                System.out.println(plant.getWateringInfo());
        }





        //přidání dvou květin do seznamu

        Plant rododendron = null;
        try {
            rododendron = new Plant("Rododendron", "čeled vřesovcovité", LocalDate.of(2020, 11, 22), LocalDate.of(2020,11,23), 4);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        Plant ruze = new Plant("růže");

        listOfPlants.addPlant(rododendron);
        listOfPlants.addPlant(ruze);

        //test výjimky metody setWatered
//        try {
//            rododendron.setWatered(LocalDate.of(2019,1,1));
//        } catch (PlantException e) {
//            System.err.println(e.getLocalizedMessage());
//        }


        //odebrání jedné květiny
        listOfPlants.removePlant(listOfPlants.getlistOfPlants().get(0));


        //výpis informací o zálivce pro všechny květiny po úpravách listu
            System.out.println("Výpis po úpravách\n++++++++++++++++++++++++++++++");
        for (Plant plant : listOfPlants.getlistOfPlants()) {
            System.out.println(plant.getWateringInfo());
        }

        //uložení do nového souboru "kvetiny-Updated.txt"
        listOfPlants.saveListToNewFile();

    }
}