package edu.ntnu.stud.controller;

import edu.ntnu.stud.model.ChaosGame;
import javafx.scene.canvas.Canvas;

/**
 * This interface is an observer for the chaos game.
 * It contains a method for updating the observer.
 * Goal: act as an observer for the chaos game.
 *
 * @version 1.0
 * @since 0.3
 */
public interface ChaosGameObserver {

  /**
   * Method to update the observer.
   */
  Canvas update(int steps, Canvas canvas, ChaosGame chaosGame);
}
