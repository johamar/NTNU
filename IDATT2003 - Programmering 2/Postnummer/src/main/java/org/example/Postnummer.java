package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Postnummer {
  public static String findCityByPostnummer(String postnummer) {
    File file = new File("src/main/resources/postnummer.txt");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.startsWith(postnummer)) {
          return line.split("\t")[1];
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String findPostnummerByCity(String city) {
    File file = new File("src/main/resources/postnummer.txt");
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (line.contains(city.toUpperCase())) {
          return line.split("\t")[0];
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    File file = new File("src/main/resources/postnummer.txt");
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Enter a postnummer or city:");
      String input = scanner.nextLine();
      if (input.matches("\\d+")) {
        System.out.println(findCityByPostnummer(input));
      } else {
        System.out.println(findPostnummerByCity(input));
      }
    }
  }
}
