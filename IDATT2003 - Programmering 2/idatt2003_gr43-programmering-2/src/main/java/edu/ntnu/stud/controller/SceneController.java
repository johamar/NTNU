package edu.ntnu.stud.controller;

import edu.ntnu.stud.view.AffinePage;
import edu.ntnu.stud.view.FileInputPage;
import edu.ntnu.stud.view.JuliaPage;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for the scene of the application.
 *
 * @version 1.0
 * @since 0.1
 */
public class SceneController {
  private final Scene scene;
  private final JuliaPage juliaPage;
  private final AffinePage affinePage;
  private final FileInputPage fileInputPage;


  /**
   * Constructor for the SceneController.
   *
   * @param primaryStage the primary stage of the application
   * @throws FileNotFoundException if the file is not found
   */
  public SceneController(Stage primaryStage) throws FileNotFoundException {
    this.scene = new Scene(new AnchorPane(), 1280, 720);
    scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());


    primaryStage.setScene(scene);
    primaryStage.setMinWidth(1200);
    primaryStage.setMinHeight(900);
    this.affinePage = new AffinePage(this);
    this.juliaPage = new JuliaPage(this);
    this.fileInputPage = new FileInputPage(this);
  }

  /**
   * Method to show the Julia page.
   */
  public void showJuliaPage() {
    scene.setRoot(juliaPage.getRoot());
  }

  /**
   * Method to show the Affine page.

   */
  public void showAffinePage() {
    scene.setRoot(affinePage.getRoot());
  }

  /**
   * Method to show the File Input page.
   */
  public void showFileInputPage() {
    scene.setRoot(fileInputPage.getRoot());
  }
}
