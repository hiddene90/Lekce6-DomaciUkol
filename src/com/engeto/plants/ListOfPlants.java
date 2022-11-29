package com.engeto.plants;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.engeto.plants.Main.OUTPUT_FILE;

public class ListOfPlants  {
    public static final String DELIMITER = "\t";
    private List<Plant> plantList = new ArrayList<>();

    public void  addPlant(Plant plant){
        plantList.add(plant);
    }
    public void  removePlant(Plant plant){
        plantList.remove(plant);
    }

    public Plant getPlantIndexOf(int index){
    return plantList.get(index);
    }

    public List<Plant> getlistOfPlants(){
        return  new ArrayList<>(plantList);
    }


    public void readPlantFromFile (String filename) throws PlantException {
        String nextLine = null;
        String[]items = new String[0];
        String name= null;
        String notes= null;
        LocalDate planted= null;
        LocalDate watered= null;
        int frequency=0;
        Plant newPlant = null;
        int lineNumber=0;

       try( Scanner scanner = new Scanner(new BufferedReader((new FileReader(filename))))){
           while (scanner.hasNextLine()){
               lineNumber++;
               nextLine = scanner.nextLine();
               items = nextLine.split(DELIMITER);
               name = items[0];
               notes = items[1];
               frequency = Integer.parseInt(items[2]);
               watered = LocalDate.parse(items[3]);
               planted = LocalDate.parse(items[4]);

               newPlant = new Plant(name,notes,planted,watered,frequency);
               addPlant(newPlant);
           }
       }catch (FileNotFoundException e){
           throw new PlantException("Nepodařilo se najít soubor"+filename+": "+e.getLocalizedMessage());
       }catch (NumberFormatException e){
           throw new PlantException("Zadán špatný formát frekvence zálivky: "+items[2] +" na řádku: "+ lineNumber);
       }catch (DateTimeException e){
           throw new PlantException("Zadán špatný formát data na řádku: "+ lineNumber);
       }
    }
    public void saveListToNewFile() {
        try (
                PrintWriter outputWriter = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
            for (Plant plant : plantList
            ) {
                outputWriter.println(plant.getName() + "\t" + plant.getNotes() + "\t" + plant.getFrequencyOfWatering() + "\t" + plant.getPlanted() + "\t" + plant.getWatered());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
