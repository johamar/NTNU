package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Class for making train departures for the train dispatch system
 * Contains a constructor for making a train departure
 * The class has getters for all attributes, and setters for those that are logical to mutate.
 *
 * @author Johan M. Arntsen
 * @version 1.0
 * @since 0.1
 */
public class TrainDeparture {
  private final LocalTime departureTime;
  private final String line;
  private final int trainNr;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   *Constructor for initializing an object.
   *Constructs by assigning the parameters below as attributes for the train departure.
   *
   * @param departureTime - is a value of the class LocalTime,
   *                      represent the departure time of a train departure.
   * @param line - is a String containing a letter and an integer
   *             to represent a route in which the train travels.
   * @param trainNr - an Integer describing an unique number for each departure.
   * @param destination - a String describing the destination of the departure.
   * @param track - an Integer that represents which track the train will be on.
   * @param delay - a value of the class LocalTime for displaying if the train is delayed,
   *              and for how long.
   *
   * @throws IllegalArgumentException if any of the strings are blank,
   *        or any of the integers are negative.
   */
  public TrainDeparture(LocalTime departureTime,
                        String line,
                        int trainNr,
                        String destination,
                        int track,
                        LocalTime delay) {

    if (departureTime == null) {
      throw new IllegalArgumentException(
              "The LocalTime value for departure time was null, please retry registration.");
    }
    if (departureTime.equals(LocalTime.MIDNIGHT)) {
      throw new IllegalArgumentException(
              "The LocalTime value for departure time was empty, please retry registration.");
    }
    if (line.isBlank()) {
      throw new IllegalArgumentException(
              "The String for the parameter 'Line' was a blank string, please retry registration.");
    }
    if (trainNr < 0) {
      throw new IllegalArgumentException(
                  "The value for the parameter 'traiNr' was negative, please retry registration");
    }
    if (destination.isBlank()) {
      throw new IllegalArgumentException(
              "The value for the parameter 'destination' was blank, please retry registration");
    }
    if (track < -1) {
      throw new IllegalArgumentException(
              "The value for the parameter 'track' was negative, please retry registration");
    }
    if (delay == null) {
      throw new IllegalArgumentException(
              "The value for the parameter 'delay' was null, please retry registration");
    }

    this.departureTime = departureTime;
    this.line = line;
    this.trainNr = trainNr;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * get-method for returning departure time.
   *
   * @return an LocalTime value of the departure time of the train.
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * get-method for getting the line.
   *
   * @return a string value of the line of the train.
   */
  public String getLine() {
    return line;
  }

  /**
   * get-method for getting the specific train number of the departure.
   *
   * @return an integer value of the train number.
   */
  public int getTrainNr() {
    return trainNr;
  }

  /**
   * get-method for getting the destination in which the train is going.
   *
   * @return a String value of the destination of the departure.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * get-method for getting the track in which the train is on.
   *
   * @return the track of the train.
   */
  public int getTrack() {
    return track;
  }

  /**
   * set-method for assigning a new value of which track the train will be on.
   *
   * @param track - describing a new integer for the track of the train.
   */
  public void setTrack(int track) {
    this.track = track;
  }

  /**
   * get-method for getting the delay of a departure.
   *
   * @return a LocalTime value of the delay of the departure.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * set-method for setting a delay for a departure.
   *
   * @param delay - describing a new LocalTime value of a delay.
   */
  public void setDelay(LocalTime delay) {
    this.delay = delay;
  }

  /**
   * a toString-method that prints a string of a departure with all the attributes needed.
   * If there are no delay or track, the string will not show these attributes.
   *
   * @return a string containing necessary information about the departure
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(String.format("%-10s | %-5s | %-9s | %-11s",
            departureTime, line, trainNr, destination.toUpperCase()));
    if (track != -1) {
      sb.append(String.format(" | %-4s ", track));
    } else {
      sb.append(String.format(" | %-4s ", ""));
    }
    if (!delay.equals(LocalTime.parse("00:00"))) {
      sb.append(String.format("| %-3s", delay));
    }

    return sb.toString();
  }

  /**
   * Method for comparing two objects of the same class.
   *
   * @param o - Used for comparing two objects of the same class.
   * @return - If the objects are equal it will return true,
   *          if not it will return false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrainDeparture that = (TrainDeparture) o;
    return trainNr == that.trainNr
            && track == that.track
            && departureTime.equals(that.departureTime)
            && line.equals(that.line)
            && destination.equals(that.destination)
            && delay.equals(that.delay);
  }
}

