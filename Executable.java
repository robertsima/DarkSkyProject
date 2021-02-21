package com.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Executable extends CityUtils {

    public static void addtoArray(ArrayList a, ArrayList b) {
        for (int i = 0; i < a.size(); i++) {
            b.add(a.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        CityUtils n = new CityUtils();
        System.out.println("Welcome user, select from the following. ");
        System.out.println("1. Get temp from all cities. (Warmest to coolest)");
        System.out.println("2. Get all cities it is currently raining in.");
        System.out.println("3. Get uv index of all cities (lowest to highest");
        System.out.println("-1: End the loop.");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        while (choice != -1) {
            if (choice == 1) {
                System.out.println("Sorting Temperatures: "); //runs tempsort comparator and prints the output
                for (int i = 0; i < n.cities.size(); i++) {
                    Collections.sort(n.cities, new TempSort());
                }
                for (int i = 0; i < n.cities.size(); i++) {
                    System.out.println(n.cities.get(i).getState() + ", " + n.cities.get(i).getName() + ": " + n.cities.get(i).getTemperature());
                }
            }

            else if (choice == 2) {
                n.printRainingCities();
            }

            else if (choice == 3) {
                System.out.println("Sorting UV Index's: ");
                for (int i = 0; i < n.cities.size(); i++) {
                    Collections.sort(n.cities, new UVSort()); //runs uvsort and prints uvindex
                }
                for (int i = 0; i < n.cities.size(); i++) {
                    System.out.println(n.cities.get(i).getState() + ", " + n.cities.get(i).getName() + ": " + n.cities.get(i).getUVIndex());
                }
            }

            else {
                System.out.println("Invalid input. Try 1, 2, or 3.");
            }
            choice = sc.nextInt();
        }
    }
}