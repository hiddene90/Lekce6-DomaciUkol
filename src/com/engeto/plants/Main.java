package com.engeto.plants;


import java.io.File;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.*;

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
            rododendron = new Plant("Rododendron", "čeled vřesovcovité", LocalDate.of(2020, 11, 22), LocalDate.of(2020, 11, 23), 4);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        Plant ruze = null;
        try {
            ruze = new Plant("Růže");
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());

        }Plant kaktus = null;
        try {
            kaktus = new Plant("Kaktus","Vánoční",LocalDate.of(1999,12,1),LocalDate.now(),4);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());

        }

        listOfPlants.addPlant(rododendron);
        listOfPlants.addPlant(ruze);
        listOfPlants.addPlant(kaktus);

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

        //Seřazení rostlin podle názvu

        System.out.println("------------------------------\nSeřazení rostlin podle názvu\n++++++++++++++++++++++++++++++");
        List<Plant> plantList = new ArrayList<>(listOfPlants.getlistOfPlants());
        Collections.sort(plantList);
        plantList.forEach(System.out::println);

        //Seřazení rostlin podle data zalití
        System.out.println("------------------------------\nSeřazení rostlin podle data zalití\n++++++++++++++++++++++++++++++");
        Collections.sort(plantList, new ComparatorWatered());
        plantList.forEach(System.out::println);

        //Poslední data zalití rostlin
        System.out.println("------------------------------\nPoslední data zalití rostlin\n++++++++++++++++++++++++++++++");
        Set<Plant> plantSet = new HashSet<>(plantList);
        plantSet.forEach(plant -> System.out.println((plant.getWatered())));

        //Byla rostlina zalita v posledním týdnu?
        System.out.println("------------------------------\nByla rostlina zalita v posledním týdnu?\n++++++++++++++++++++++++++++++");

        plantList.forEach(plant -> System.out.println(plant.wasWateredLastWeek()));

    }

    }
