package edu.ntnu.stud.launcher;

import edu.ntnu.stud.controller.SceneController;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the main class of the program for displaying the UI.
 * Goal: act as a launcher for the UI.
 *
 * @version 1.0
 * @since 0.3
 */
public class MainUI extends Application {

  /**
   * The main method of the UI.
   *
   * @param primaryStage The primary stage of the program.
   */
  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    SceneController sceneController = new SceneController(primaryStage);
    sceneController.showAffinePage();

    primaryStage.setTitle("Chaos Game");
    primaryStage.setWidth(1500);
    primaryStage.setHeight(1000);
    primaryStage.show();
  }
}
