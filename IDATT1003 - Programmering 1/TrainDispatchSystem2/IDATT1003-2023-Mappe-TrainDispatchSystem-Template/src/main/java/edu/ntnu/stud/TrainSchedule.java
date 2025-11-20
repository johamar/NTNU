package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class for administrating the train departures and all information about them.
 * All departures will be stored in an ArrayList.
 * goal: act as a register for all train departures
 *
 * @author Johan M. Arntsen
 * @version 1.0
 * @since 0.2
 */
public class TrainSchedule {
  private final ArrayList<TrainDeparture> schedule;
  private LocalTime time;

  /**
   * Constructor for making objects of the class 'TrainDeparture' and storing them in an ArrayList.
   * Also acts as a mechanic lock, with adding the value time into the parameter
   *
   * @param time - sets the time for the hole program
   *
   * @throws IllegalArgumentException if the time is null or empty.
   */
  public TrainSchedule(LocalTime time) {

    if (time == null) {
      throw new IllegalArgumentException(
              "The LocalTime value for departure time was null, please retry registration.");
    }
    if (time.equals(LocalTime.MIDNIGHT)) {
      throw new IllegalArgumentException(
              "The LocalTime value for departure time was empty, please retry registration.");
    }

    schedule = new ArrayList<>();
    this.time = time;
  }

  /**
   * Method that makes a String out of all objects in the ArrayList 'schedule'
   * containing all train departures.
   * The method sorts all objects in order after the departure time.
   * If a departure is delayed it will be stored in the string until
   * the current time is passed the time of departure plus any delay.
   *
   * @return - A string containing all objects that have departure time after
   *        the current time, sorted after the departure time of each departure.
   */
  public String displayBoard() {
    StringBuilder infoBoard = new StringBuilder();
    schedule.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    for (TrainDeparture t : schedule) {
      LocalTime calculatedDepTime = t.getDepartureTime()
              .plusMinutes(t.getDelay().toSecondOfDay() / 60);
      if (calculatedDepTime.isAfter(getTime())) {
        infoBoard.append(t).append("\n");
      }
    }
    return infoBoard.toString();
  }

  /**
   * Method that lets the user make a new object of the class 'TrainDeparture'.
   * It checks if the object already exists before adding it.
   * If it already exists it will return false.
   *
   * @param departure - an object of the class 'TrainDeparture'.
   *
   * @return - if the departure does not exist, it will return true.
   *      If the departure already exists it will return false.
   */
  public boolean addDeparture(TrainDeparture departure) {
    if (!schedule.contains(departure)) {
      schedule.add(departure);
      return true;
    }
    return false;
  }

  /**
   * Method for assigning a track for a departure
   * by searching for the train number of the departure.
   *
   * @param trainNr - Used for searching for an existing departure.
   * @param newTrack - Used for assigning a new track value to the departure.
   * @return - If departure is found, it will return the given departure.
   *        If departure is not found, it will return null.
   */
  public TrainDeparture assignTrack(int trainNr, int newTrack) {
    for (TrainDeparture t : schedule) {
      if (trainNr == t.getTrainNr()) {
        t.setTrack(newTrack);
        return t;
      }
    }
    return null;
  }

  /**
   * Method for adding/changing a delay to a departure
   * by searching for the train number of the departure.
   *
   * @param trainNr - Used for searching for the existing departure.
   * @param newDelay - Parameter that represents the new delay value.
   * @return - If departure is found it will return the delay of the given departure.
   *        If not it will return null.
   */
  public LocalTime addDelay(int trainNr, String newDelay) {
    for (TrainDeparture t : schedule) {
      if (trainNr == t.getTrainNr()) {
        t.setDelay(LocalTime.parse(newDelay));
        return t.getDelay();
      }
    }
    return null;
  }

  /**
   * Method for searching for a specific departure by the train number.
   *
   * @param trainNr - Used for searching for the existing departure.
   * @return - If the departure is found it will return it. If not it will return null.
   */
  public TrainDeparture searchDepartureByNr(int trainNr) {
    for (TrainDeparture t : schedule) {
      if (trainNr == t.getTrainNr()) {
        return t;
      }
    }
    return null;
  }

  /**
   * Method for searching after departures by their destination.
   * Lists all departures to the destination by making a new ArrayList.
   *
   * @param destination - Used for searching for all existing departures to the given destination.
   * @return - A string with all departures to the given destination.
   */
  public String searchDepartureByDest(String destination) {
    StringBuilder departuresToDest = new StringBuilder();
    ArrayList<TrainDeparture> departureByDest = new ArrayList<>();
    for (TrainDeparture t : schedule) {
      if (t.getDestination().equals(destination.toUpperCase())) {
        departureByDest.add(t);
      }
    }
    for (TrainDeparture d : departureByDest) {
      departuresToDest.append(d).append("\n");
    }
    return departuresToDest.toString();
  }

  /**
   * Method for checking if a departure is equal to another.
   *
   * @param o - Used for comparing two departures.
   * @return - If the departures are equal it will return true.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrainSchedule that = (TrainSchedule) o;
    return Objects.equals(schedule, that.schedule) && Objects.equals(time, that.time);
  }

  /**
   * Get-method for getting the current time.
   *
   * @return - The current time as a LocalTime value.
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Set-method for setting a new time.
   *
   * @param updatedTime - for entering a new updated time.
   * @return - returns the new current time as a LocalTime value.
   */

  public boolean setTime(String updatedTime) {
    LocalTime newTime = LocalTime.parse(updatedTime);
    if (newTime.isAfter(time) || updatedTime.equals("00:00")) {
      time = newTime;
      return true;
    }
    return false;
  }

  /**
   * Method for deleting a departure by the train number.
   *
   * @param trainNr - Used for searching for the existing departure.
   * @return - If the departure is found it will return it. If not it will return null.
   */
  public TrainDeparture deleteDeparture(int trainNr) {
    for (TrainDeparture t : schedule) {
      if (trainNr == t.getTrainNr()) {
        schedule.remove(t);
        return t;
      }
    }
    return null;
  }
}


