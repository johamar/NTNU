package edu.ntnu.stud.controller;

import edu.ntnu.stud.model.ChaosGame;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is an observer for the canvas.
 * It contains methods for updating the canvas and clearing it.
 * Goal: act as an observer for the canvas.
 *
 * @version 1.0
 * @since 0.1
 */
public class CanvasObserver {

  /**
   * Method to update the observer.
   */
  public static Canvas update(int steps, Canvas canvas, ChaosGame chaosGame) {
    chaosGame.runSteps(steps);
    int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(javafx.scene.paint.Color.BLACK);

    for (int i = 0; i < 800; i++) {
      for (int j = 0; j < 800; j++) {
        if (canvasArray[j][i] == 1) {
          gc.getPixelWriter().setColor(i, j, javafx.scene.paint.Color.BLACK);
        }
      }
    }
    return canvas;
  }

  /**
   * Method to clear the canvas.
   *
   * @param canvas the canvas to be cleared
   * @return the cleared canvas
   */
  public static Canvas clearCanvas(Canvas canvas) {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setFill(javafx.scene.paint.Color.WHITE);
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    return canvas;
  }
}

