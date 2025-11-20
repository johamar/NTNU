package edu.ntnu.stud.launcher;

import edu.ntnu.stud.model.ChaosGame;
import edu.ntnu.stud.model.ChaosGameDescription;
import edu.ntnu.stud.model.ChaosGameDescriptionFactory;

/**
 * This class is main class of the program for displaying a Julia set in the console.
 * Goal: act as a launcher for the chaos game.
 */
public class Main {

  /**
   * Main method for the Main class.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet1();
    ChaosGame chaosGame = new ChaosGame(description, 150, 150);
    chaosGame.runSteps(1000000);
    chaosGame.getCanvas().showAsciiCanvas();
  }
}