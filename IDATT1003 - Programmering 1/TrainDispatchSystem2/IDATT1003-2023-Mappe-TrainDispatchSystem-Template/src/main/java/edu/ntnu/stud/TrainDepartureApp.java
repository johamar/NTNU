package edu.ntnu.stud;

/**
 * Class for starting the program.
 *
 * @author Johan M. Arntsen
 * @version 1.0
 * @since 0.3
 */
public class TrainDepartureApp {

  /**
   * Main method for starting the program.
   *
   * @param args - are the arguments for the main method.
   */
  public static void main(String[] args) {

    UserMenu menu = new UserMenu();
    menu.init();
    menu.start();

  }
}




