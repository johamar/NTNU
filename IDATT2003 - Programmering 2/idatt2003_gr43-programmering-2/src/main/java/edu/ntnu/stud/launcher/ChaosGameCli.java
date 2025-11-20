package edu.ntnu.stud.launcher;

import edu.ntnu.stud.model.ChaosGame;
import edu.ntnu.stud.model.ChaosGameFileHandler;
import java.util.Scanner;

/**
 * This class is a command line interface for the chaos game.
 * It contains methods for starting the CLI and handling user input.
 * Goal: act as a launcher for the chaos game in the console.
 *
 * @version 1.0
 * @since 0.2
 */
public class ChaosGameCli {
  private final Scanner scanner;
  private ChaosGame chaosGame;
  private final ChaosGameFileHandler chaosGameFileHandler;

  /**
   * Constructor for the ChaosGameCLI class.
   */
  public ChaosGameCli() {
    scanner = new Scanner(System.in);
    chaosGameFileHandler = new ChaosGameFileHandler();
  }

  /**
   * Main method for the ChaosGameCLI class.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ChaosGameCli cli = new ChaosGameCli();
    cli.start();
  }

  /**
   * Method to start the CLI.
   */
  public void start() {
    System.out.println("Welcome to ChaosGame CLI!");
    System.out.println("Type 'help' to see available commands.");

    boolean running = true;
    while (running) {
      System.out.print("> ");
      String input = scanner.nextLine().trim();

      switch (input.toLowerCase()) {
        case "help":
          printHelp();
          break;
        case "exit":
          running = false;
          break;
        case "load":
          readFromFile();
          break;
        case "save":
          writeToFile();
          break;
        case "steps":
          runIterations();
          break;
        case "run":
          runASCII();
          break;
        default:
          System.out.println("Invalid command. Type 'help' to see available commands.");
          break;
      }
    }

    scanner.close();
  }

  /**
   * Method to print the available commands.
   */
  private void printHelp() {
    System.out.println("Available commands:");
    System.out.println("- help: Show available commands");
    System.out.println("- exit: Exit the program");
    System.out.println("- load: Load a chaos game description from a file");
    System.out.println("- save: Save the current chaos game description to a file");
    System.out.println("- steps: Number of iterations in the chaos game");
    System.out.println("- run: Draw the fractal on the canvas");
  }

  /**
   * Method to read a chaos game description from a file.
   */
  private void readFromFile() {
    System.out.print("Enter file path: ");
    String filePath = scanner.nextLine().trim();
    chaosGame = new ChaosGame(chaosGameFileHandler.readFromFile(filePath), 150, 150);
  }

  /**
   * Method to write a chaos game description to a file.
   */
  private void writeToFile() {
    System.out.print("Enter file path: ");
    String filePath = scanner.nextLine().trim();
    chaosGameFileHandler.writeToFile(chaosGame.getDescription(), filePath);
  }

  /**
   * Method to run a number of iterations in the chaos game.
   */
  private void runIterations() {
    System.out.print("Enter number of iterations: ");
    int iterations = scanner.nextInt();
    scanner.nextLine();
    chaosGame.runSteps(iterations);
  }

  /**
   * Method to draw the fractal on the canvas.
   */
  private void runASCII() {
    chaosGame.getCanvas().showAsciiCanvas();
  }
}
