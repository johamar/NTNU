package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TrainScheduleTest {
  TrainSchedule trainSchedule;


  @Nested
  @DisplayName("Positive tests for 'TrainSchedule' class")
  class PositiveTestsForTrainSchedule {
    @BeforeEach
    @DisplayName("Setup for testing parameters")
    public void setUp() {
      trainSchedule = new TrainSchedule(LocalTime.parse("10:00"));
    }
    @Test
    @DisplayName("Positive test for constructor")
    public void testConstructor() {
      assertNotNull(trainSchedule);
    }
    @Test
    @DisplayName("Positive test for displayBoard-method")
    public void testDisplayBoard() {
      assertNotNull(trainSchedule.displayBoard());
    }
    @Test
    @DisplayName("Positive test for getTime-method")
    public void testGetTime() {
      assertNotNull(trainSchedule.getTime());
    }
    @Test
    @DisplayName("Positive test for detTime-method")
    public void testGetTimeEquals() {
      assertEquals(LocalTime.parse("10:00"), trainSchedule.getTime());
    }

    @Test
    @DisplayName("Positive test for setTime-method")
    public void testSetTime() {
      trainSchedule.setTime("13:00");
      assertEquals(LocalTime.parse("13:00"), trainSchedule.getTime());
    }
    @Test
    @DisplayName("Positive test for addTrainDeparture-method")
    public void testAddDeparture() {
      assertTrue(trainSchedule.addDeparture(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01"))));
    }
    @Test
    @DisplayName("Positive test for addTrainDeparture-method")
    public void testAddDepartureEquals() {
      trainSchedule.addDeparture(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01")));
      assertFalse(trainSchedule.addDeparture(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01"))));
    }

    @Test
    @DisplayName("Positive test for deleteDeparture-method")
    public void testDeleteDeparture() {
      trainSchedule.addDeparture(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01")));
      assertNotNull(trainSchedule.deleteDeparture(123));
    }
    @Test
    @DisplayName("Positive test for deleteDeparture-method")
    public void testDeleteDepartureEquals() {
      trainSchedule.addDeparture(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01")));
      assertEquals(new TrainDeparture(LocalTime.parse("12:00"), "A1", 123, "Trondheim", 1, LocalTime.parse("00:01")), trainSchedule.deleteDeparture(123));
    }
    @Test
    @DisplayName("Positive test for deleteDeparture-method")
    public void testDeleteDepartureNull() {
      assertNull(trainSchedule.deleteDeparture(123));
    }

    @Test
    @DisplayName("Positive test for equals-method")
    public void testEquals() {
      TrainSchedule trainSchedule2 = new TrainSchedule(LocalTime.parse("10:00"));
      assertEquals(trainSchedule, trainSchedule2);
    }
    @Test
    @DisplayName("Positive test for equals-method")
    public void testEqualsFalse() {
      TrainSchedule trainSchedule2 = new TrainSchedule(LocalTime.parse("11:00"));
      assertNotEquals(trainSchedule, trainSchedule2);
    }

    @Test
    @DisplayName("Positive test for equals-method")
    public void testEqualsNull() {
      TrainSchedule trainSchedule2 = null;
      assertNotEquals(trainSchedule, trainSchedule2);
    }
    @Test
    @DisplayName("Positive test for equals-method")
    public void testEqualsSame() {
      TrainSchedule trainSchedule2 = trainSchedule;
      assertEquals(trainSchedule, trainSchedule2);
    }
    @Test
    @DisplayName("Positive test for equals-method")
    public void testEqualsDifferentClass() {
      String trainSchedule2 = "test";
      assertNotEquals(trainSchedule, trainSchedule2);
    }
  }
  @Nested
  @DisplayName("Negative tests for 'TrainSchedule' class")
  class NegativeTestsForTrainSchedule {
    @Test
    @DisplayName("Should not create train schedule if time is null")
    public void timeNullShouldThrowExcetion() {
      assertThrows(IllegalArgumentException.class, () -> {
        new TrainSchedule(null);
      });
    }
    @Test
    @DisplayName("Should not create train schedule if time is a blank String")
    public void timeBlankShouldThrowExcetion() {
      assertThrows(DateTimeParseException.class, () -> {
        new TrainSchedule(LocalTime.parse(""));
      });
    }
    @Test
    @DisplayName("Should not create train schedule if time is after 23:59")
    public void timeAfterMidnightShouldThrowExcetion() {
      assertThrows(DateTimeParseException.class, () -> {
        new TrainSchedule(LocalTime.parse("24:00"));
      });
    }
    @Test
    @DisplayName("Should not create train schedule if time is before 00:00")
    public void timeBeforeMidnightShouldThrowExcetion() {
      assertThrows(DateTimeParseException.class, () -> {
        new TrainSchedule(LocalTime.parse("-00:01"));
      });
    }
    @Test
    @DisplayName("Should not create train schedule if time is not in the right format")
    public void timeWrongFormatShouldThrowExcetion() {
      assertThrows(DateTimeParseException.class, () -> {
        new TrainSchedule(LocalTime.parse("12"));
      });
    }
    @Test
    @DisplayName("Should throw exception if displayBoard-method is called on an empty schedule")
    public void displayBoardEmptyScheduleShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.displayBoard();
      });
    }
    @Test
    @DisplayName("Should throw exception if addDeparture-method is called with a null parameter")
    public void addDepartureNullShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.addDeparture(null);
      });
    }
    @Test
    @DisplayName("Should throw exception if deleteDeparture-method is called with a negative parameter")
    public void deleteDepartureNegativeShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.deleteDeparture(-1);
      });
    }
    @Test
    @DisplayName("Should throw exception if deleteDeparture-method is called with a zero parameter")
    public void deleteDepartureZeroShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.deleteDeparture(0);
      });
    }
    @Test
    @DisplayName("Should throw exception of setTime-method is called with a null parameter")
    public void setTimeNullShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.setTime(null);
      });
    }
    @Test
    @DisplayName("Should throw exception if setTime-method is called with a blank String")
    public void setTimeBlankShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.setTime("");
      });
    }
    @Test
    @DisplayName("Should throw exception if assignTrack-method is called with a negative parameter")
    public void assignTrackNegativeTrainNrShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.assignTrack(-1, 3);
      });
    }
    @Test
    @DisplayName("Should throw exception if assignTrack-method is called with a negative parameter")
    public void assignTrackNegativeTrackShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.assignTrack(3, -2);
      });
    }
    @Test
    @DisplayName("Should throw exception if addDelay-method is called with a negative parameter")
    public void addDelayNegativeTrainNrShouldThrowExcetion() {
      assertThrows(NullPointerException.class, () -> {
        trainSchedule.addDelay(-1, "00:01");
      });
    }
  }
}