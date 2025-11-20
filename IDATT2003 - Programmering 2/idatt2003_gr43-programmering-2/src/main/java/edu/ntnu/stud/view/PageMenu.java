package edu.ntnu.stud.view;

import edu.ntnu.stud.controller.SceneController;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * This class represents the page menu.
 * Contains a method for creating the page menu.
 * Goal: act as a view for the page menu.
 *
 * @version 1.0
 * @since 0.1
 */
public class PageMenu {

  MenuButton menuButton;

  /**
   * Constructs a new PageMenu object with the given scene controller.
   *
   * @param sceneController the scene controller to be used for the page menu
   */
  public PageMenu(SceneController sceneController) {
    menuButton = new MenuButton("Menu");

    MenuItem affinePage = new MenuItem("Affine Transformations");
    MenuItem juliaPage = new MenuItem("Julia Sets");
    MenuItem fileInputPage = new MenuItem("File Input");

    menuButton.getItems().addAll(
        affinePage,
        juliaPage,
        fileInputPage
    );

    affinePage.setOnAction(e -> sceneController.showAffinePage());
    juliaPage.setOnAction(e -> sceneController.showJuliaPage());
    fileInputPage.setOnAction(e -> sceneController.showFileInputPage());
  }

  /**
   * Returns the menu button of the page menu.
   *
   * @return the menu button of the page menu
   */
  public MenuButton getMenuButton() {
    return menuButton;
  }
}
