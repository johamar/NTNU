package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {
  TrainDeparture trainDeparture;
  private final LocalTime departureTime = LocalTime.parse("12:00");
  private final String line = "A1";
  private final int trainNr = 123;
  private final String destination = "Trondheim";
  private final int track = 1;
  private final LocalTime delay = LocalTime.parse("00:01");

  @Nested
  @DisplayName("Postive tests for 'TrainDeparture'")
  public class postiveTestsForTrainDeparture {
    @BeforeEach
    @DisplayName("Setup for testing parameters")
    public void setUp() {
      trainDeparture = new TrainDeparture(departureTime, line, trainNr, destination, track, delay);
    }

    @Test
    @DisplayName("Positive test for departure time-parameter")
    public void testDepartureTimeForValidParameter() {
      assertEquals(departureTime, trainDeparture.getDepartureTime());
    }

    @Test
    @DisplayName("Positive test for line-parameter")
    public void testLineForValidParameter() {
      assertEquals(line, trainDeparture.getLine());
    }

    @Test
    @DisplayName("Positive test for train number-parameter")
    public void testTrainNrForValidParameter() {
      assertEquals(trainNr, trainDeparture.getTrainNr());
    }

    @Test
    @DisplayName("Positive test for destination-parameter")
    public void testDestinationForValidParameter() {
      assertEquals(destination, trainDeparture.getDestination());
    }

    @Test
    @DisplayName("Positive test for track-parameter")
    public void testTrackForValidParameter() {
      assertEquals(track, trainDeparture.getTrack());
    }

    @Test
    @DisplayName("Positive test for delay-parameter")
    public void testDelayForValidParameter() {
      assertEquals(delay, trainDeparture.getDelay());
    }
    @Test
    @DisplayName("Positive test for set-delay-method")
    public void testSetDelayForValidMethodRespos() {
      trainDeparture.setDelay(LocalTime.parse("00:05"));
      assertEquals(LocalTime.parse("00:05"), trainDeparture.getDelay());
    }
    @Test
    @DisplayName("Positive test for toString")
    public  void testToString() {
      assertEquals("12:00      | A1    | 123       | TRONDHEIM   | 1    | 00:01", trainDeparture.toString());
    }
    @Test
    @DisplayName("Positive test for toString without delay")
    public  void testToStringWithoutDelay() {
      trainDeparture.setDelay(LocalTime.parse("00:00"));
      assertEquals("12:00      | A1    | 123       | TRONDHEIM   | 1    ", trainDeparture.toString());
    }
    @Test
    @DisplayName("Positive test for toString without track")
    public  void testToStringWithoutTrack() {
      trainDeparture.setTrack(-1);
      assertEquals("12:00      | A1    | 123       | TRONDHEIM   |      | 00:01", trainDeparture.toString());
    }
    @Test
    @DisplayName("Positive test for toString without delay and track")
    public  void testToStringWithoutDelayAndTrack() {
      trainDeparture.setTrack(-1);
      trainDeparture.setDelay(LocalTime.parse("00:00"));
      assertEquals("12:00      | A1    | 123       | TRONDHEIM   |      ", trainDeparture.toString());
    }

  }
  @Nested
  @DisplayName("Negative tests for 'Traindeparture")
  public class negativeTestsForTrainDeparture {
    @Test
    @DisplayName("Should not create train departure if departure time is null")
    public void departureTimeNullShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
              new TrainDeparture(null, line, trainNr, destination, track, delay);
      });
    }
    @Test
    @DisplayName("Should not create train departure if departure time is a blank String")
    public void DepartureTimeBlankShouldThrowExcetion() {
      assertThrows(DateTimeParseException.class, () -> {
        new TrainDeparture(LocalTime.parse(""), line, trainNr, destination, track, delay);
      });
    }
    @Test
    @DisplayName("Should not create train departure if line is a blank String")
    public void lineBlankShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainDeparture(departureTime, "", trainNr, destination, track, delay);
      });
    }
    @Test
    @DisplayName("Should not create train departure if train number is negative")
    public void trainNrNegativeShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainDeparture(departureTime, line, -123, destination, track, delay);
      });
    }

    @Test
    @DisplayName("Should not create train departure if destination is a blank String")
    public void destinationBlankShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainDeparture(departureTime, line, trainNr, "", track, delay);
      });
    }
    @Test
    @DisplayName("Should not create train departure if track is a negative number less than -1")
    public void trackLessThanNegativeOneShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainDeparture(departureTime, line, trainNr, destination, -2, delay);
      });
    }
    @Test
    @DisplayName("Should not create train departure if delay is null")
    public void delayNullShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainDeparture(departureTime, line, trainNr, destination, track, null);
      });
    }

  }
}