package com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

import java.io.*;

public class DataManager {

    /**
     * Saves data to a binary file using serialization.
     * @param data The object to save (e.g., a List<Subscriber>).
     * @param fileName The name of the file (e.g., "subscribers.bin").
     */
    public static void saveData(Serializable data, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Loads data from a binary file.
     * @param fileName The name of the file to load from.
     * @return The object read from the file, or null if an error occurs.
     */
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