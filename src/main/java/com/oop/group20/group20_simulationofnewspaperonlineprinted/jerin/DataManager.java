package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.*;

public class DataManager {

    public static void saveData(Serializable data, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static Object loadData(String fileName) {
        File dataFile = new File(fileName);
        if (!dataFile.exists()) {
            System.out.println("No existing data file found: " + fileName);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object data = ois.readObject();
            System.out.println("Data successfully loaded from " + fileName);
            return data;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}