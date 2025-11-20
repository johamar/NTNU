package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class for initializing the user interface for the train dispatch system
 * and handling user input.
 *
 * @author Johan M. Arntsen
 * @version 1.0
 * @since 0.3
 */
public class UserMenu {
  private static TrainSchedule schedule;
  private static final int DISPLAY_BOARD = 1;
  private static final int ADD_DEPARTURE = 2;
  private static final int ASSIGN_TRACK = 3;
  private static final int ADD_DELAY = 4;
  private static final int SEARCH_BY_TRAIN_NR = 5;
  private static final int SEARCH_BY_DESTINATION = 6;
  private static final int UPDATE_CLOCK = 7;
  private static final int DELETE_DEPARTURE = 8;
  private static final int EXIT = 9;

  /**
   * Method for displaying the menu and handling user input.
   *
   * @return - The option chosen by the user.
   */
  private int showMenu() {
    int menuChoice = 0;
    System.out.println("---------Togavgang Applikasjon V1.0---------\n"
            + "1: Vis informsajonstavle\n"
            + "2: Legge inn ny togavgang\n"
            + "3: Tildele spor til en togavgang\n"
            + "4: Legg inn forsinkelse på en togavgang\n"
            + "5: Søk etter togavgang basert på tognummer\n"
            + "6: Søk etter togavgang basert på destinasjon\n"
            + "7: Oppdater klokken\n"
            + "8: Slett en eksisterende togavgang\n"
            + "9: Avslutt\n"
            + "--------------------------------------------\n"
            + "Vennligst velg et nummer mellom 1 og 9");
    Scanner scan = new Scanner(System.in);
    if (scan.hasNextInt()) {
      menuChoice = scan.nextInt();
    } else {
      System.out.println("Vennligst skriv et tall, ikke tekst");
    }
    return menuChoice;
  }

  /**
   * Method for initializing the train schedule with some premade train departures.
   */
  public void init() {
    schedule = new TrainSchedule(LocalTime.parse("05:12"));

    TrainDeparture d1 = new TrainDeparture(
            LocalTime.parse("08:40"),
            "T5", 132, "OSLO", 2,
            LocalTime.parse("00:02"));
    TrainDeparture d2 = new TrainDeparture(
            LocalTime.parse("06:15"),
            "A1", 128, "OSLO", 2,
            LocalTime.parse("00:00"));
    TrainDeparture d3 = new TrainDeparture(
            LocalTime.parse("07:25"),
            "A7", 210, "TRONDHEIM", 5,
            LocalTime.parse("00:01"));
    TrainDeparture d4 = new TrainDeparture(
            LocalTime.parse("12:55"),
            "B6", 568, "BODØ", 8,
            LocalTime.parse("00:04"));
    TrainDeparture d5 = new TrainDeparture(
            LocalTime.parse("16:30"),
            "T5", 138, "OSLO", 3,
            LocalTime.parse("00:00"));
    TrainDeparture d6 = new TrainDeparture(
            LocalTime.parse("08:20"),
            "A7", 212, "TRONDHEIM", 6,
            LocalTime.parse("00:02"));

    schedule.addDeparture(d1);
    schedule.addDeparture(d2);
    schedule.addDeparture(d3);
    schedule.addDeparture(d4);
    schedule.addDeparture(d5);
    schedule.addDeparture(d6);
  }

  /**
   * Method for starting the user interface.
   * The method will run until the user chooses to exit.
   */
  public void start() {

    boolean finished = false;
    Scanner scan = new Scanner(System.in);

    while (!finished) {
      int menuChoice = this.showMenu();
      switch (menuChoice) {
        case DISPLAY_BOARD:
          System.out.println("Informasjonstavlen:");
          System.out.println("                            " + schedule.getTime());
          System.out.println("-----------------------------------------------------------------");
          System.out.println("Avgangstid | Linje | Tognummer | Destinasjon | Spor | Forsinkelse");
          System.out.println(schedule.displayBoard());
          System.out.println("-----------------------------------------------------------------");
          break;
        case ADD_DEPARTURE:
          System.out.println("--------------------------------------------");
          System.out.println("Ny togavgang:");

          System.out.println("Skriv inn avgangstid (tt:mm):");
          String departureTimeInput = scan.nextLine();
          LocalTime departureTime;
          while (true) {
            try {
              departureTime = LocalTime.parse(departureTimeInput);
              break;
            } catch (DateTimeParseException e) {
              System.out.println("Ugyldig tidspunkt, prøv igjen!");
              System.out.println("Skriv inn avgangstid (tt:mm) (f.eks. 15:30):");
              departureTimeInput = scan.nextLine();
            }
          }

          System.out.println("Skriv inn linje:");
          final String line = scan.nextLine();

          System.out.println("Skriv inn tognummer:");
          while (!scan.hasNextInt()) {
            System.out.println("Tognummer må være et heltall, prøv igjen!");
            System.out.println("Skriv inn tognummer:");
            scan.next();
          }
          int trainNr = scan.nextInt();
          scan.nextLine();
          while (true) {
            TrainDeparture departure = schedule.searchDepartureByNr(trainNr);
            if (departure != null && trainNr == departure.getTrainNr()) {
              System.out.println("Tognummeret finnes allerede, prøv igjen!");
              System.out.println("Skriv inn tognummer:");
              trainNr = scan.nextInt();
              scan.nextLine();
            } else if (trainNr < 0) {
              System.out.println("Tognummer må være et positivt heltall, prøv igjen!");
              System.out.println("Skriv inn tognummer:");
              trainNr = scan.nextInt();
              scan.nextLine();
            } else {
              break;
            }
          }

          System.out.println("Skriv inn destinasjon:");
          final String destination = scan.nextLine().toUpperCase();

          System.out.println("Skriv inn spor (hvis ingen, skriv -1):");
          int track;
          while (true) {
            while (!scan.hasNextInt()) {
              System.out.println("Spor må være et postitivt heltall eller -1, prøv igjen!");
              System.out.println("Skriv inn spor (hvis ingen, skriv -1):");
              scan.next();
            }
            track = scan.nextInt();
            if (track >= -1) {
              break;
            }
            System.out.println("Spor må være et postitivt heltall eller -1, prøv igjen!");
            System.out.println("Skriv inn spor (hvis ingen, skriv -1):");
          }
          scan.nextLine();


          System.out.println("Skriv inn forsinkelse (tt:mm) (hvis ingen, trykk enter):");
          String delayInput = scan.nextLine();
          LocalTime delay;
          while (true) {
            try {
              if (delayInput.isBlank() || delayInput.equals("0") || delayInput.equals("0000")) {
                delay = LocalTime.parse("00:00");
              } else {
                delay = LocalTime.parse(delayInput);
              }
              break;
            } catch (DateTimeParseException e) {
              System.out.println("Ugyldig tidspunkt, prøv igjen!");
              System.out.println("Skriv inn forsinkelse (tt:mm) (hvis ingen, trykk enter):");
              delayInput = scan.nextLine();
            }
          }

          TrainDeparture newDep = new TrainDeparture(
                  departureTime, line, trainNr, destination, track, delay);
          if (schedule.addDeparture(newDep)) {
            schedule.addDeparture(newDep);
            System.out.println("Avgang lagt til!");
          } else {
            System.out.println("Denne avgangen finnes allerede");
          }
          break;
        case ASSIGN_TRACK:
          System.out.println("--------------------------------------------");
          System.out.println("Tildel spor");
          System.out.println("Skriv inn tognummer:");
          while (!scan.hasNextInt()) {
            System.out.println("Tognummer må være et heltall, prøv igjen!");
            System.out.println("Skriv inn tognummer:");
            scan.next();
          }
          int trainNrTrack = scan.nextInt();
          if (schedule.searchDepartureByNr(trainNrTrack) == null) {
            System.out.println("Togavgang med tognummer " + trainNrTrack + " finnes ikke");
            break;
          }
          System.out.println("Du skifter nå spor for tognumer " + trainNrTrack);
          System.out.println("Skriv inn spor for denne togavgangen: ");
          int newTrack;
          while (true) {
            while (!scan.hasNextInt()) {
              System.out.println("Spor må være et positivt heltall, prøv igjen!");
              System.out.println("Skriv inn spor (hvis ingen, skriv -1):");
              scan.next();
            }
            newTrack = scan.nextInt();
            if (newTrack >= -1) {
              break;
            }
            System.out.println("Spor må være et positivt heltall, prøv igjen!");
            System.out.println("Skriv inn spor (hvis ingen, skriv -1):");
          }

          schedule.assignTrack(trainNrTrack, newTrack);
          System.out.println("Nytt spor tildelt " + trainNrTrack + " til "
                  + schedule.searchDepartureByNr(trainNrTrack).getDestination());
          break;
        case ADD_DELAY:
          System.out.println("--------------------------------------------");
          System.out.println("Legg til forsinkelse");
          System.out.println("Skriv inn tognummer");
          while (!scan.hasNextInt()) {
            System.out.println("Tognummer må være et heltall, prøv igjen!");
            System.out.println("Skriv inn tognummer:");
            scan.next();
          }
          int trainNrDelay = scan.nextInt();
          scan.nextLine();
          if (schedule.searchDepartureByNr(trainNrDelay) == null) {
            System.out.println("Togavgang med tognummer " + trainNrDelay + " finnes ikke");
            break;
          }
          System.out.println("Du legger nå til forsinkelse for tognummer " + trainNrDelay);
          System.out.println("Skriv inn forsinkelse (hh:mm):");
          String delayAdded = scan.nextLine();
          while (true) {
            try {
              if (delayAdded.isBlank() || delayAdded.equals("0") || delayAdded.equals("0000")) {
                delayAdded = "00:00";
              }
              LocalTime.parse(delayAdded);
              break;
            } catch (DateTimeParseException e) {
              System.out.println("Ugyldig tidspunkt, prøv igjen!");
              System.out.println("Skriv inn forsinkelse (hh:mm):");
              delayAdded = scan.nextLine();
            }
          }

          schedule.addDelay(trainNrDelay, delayAdded);
          System.out.println("Forsinkelse lagt til for " + trainNrDelay);
          break;
        case SEARCH_BY_TRAIN_NR:
          System.out.println("--------------------------------------------");
          System.out.println("Søk etter togavgang basert på tognummer");
          System.out.println("Skriv inn tognummer:");
          while (!scan.hasNextInt()) {
            System.out.println("Tognummer må være et heltall, prøv igjen!");
            System.out.println("Skriv inn tognummer:");
            scan.next();
          }
          int trainNrSearched = scan.nextInt();
          if (schedule.searchDepartureByNr(trainNrSearched) == null) {
            System.out.println("Togavgang med tognummer " + trainNrSearched + " finnes ikke");
            break;
          }
          System.out.println("Togavgang med tognummer " + trainNrSearched + ":");
          System.out.println(schedule.searchDepartureByNr(trainNrSearched));
          break;
        case SEARCH_BY_DESTINATION:
          System.out.println("--------------------------------------------");
          System.out.println("Søk etter togavgang basert på destinasjon");
          System.out.println("Skriv inn destinasjon:");
          String trainDestSearched = scan.nextLine();
          if (!schedule.searchDepartureByDest(trainDestSearched).isEmpty()) {
            System.out.println("Togavganger som går til " + trainDestSearched + ":");
            System.out.println("                            " + schedule.getTime());
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Avgangstid | Linje | Tognummer | Destinasjon | Spor | Forsinkelse");
            System.out.println(schedule.searchDepartureByDest(trainDestSearched));
          } else {
            System.out.println("Det finnes ingen togavganger til " + trainDestSearched);
          }
          break;
        case UPDATE_CLOCK:
          System.out.println("--------------------------------------------");
          System.out.println("Oppdater klokken");
          System.out.println("Skriv inn nytt klokkeslett (hh:mm):");
          String newTime = scan.nextLine();
          while (true) {
            try {
              if (schedule.setTime(newTime)) {
                schedule.setTime(newTime);
                System.out.println("Ny tid registrert! Klokken er nå " + schedule.getTime());
              } else {
                System.out.println("Ugyldig tidspunkt! Du kan ikke stille klokken tilbake i tid.");
              }
              break;
            } catch (DateTimeParseException e) {
              System.out.println("Ugyldig tidspunkt, prøv igjen!");
              System.out.println("Skriv inn nytt klokkeslett (tt:mm)(f.eks. 16:00):");
              newTime = scan.nextLine();
            }
          }
          break;
        case DELETE_DEPARTURE:
          System.out.println("--------------------------------------------");
          System.out.println("Slett en eksisterende togavgang");
          System.out.println("Skriv inn tognummer:");
          while (!scan.hasNextInt()) {
            System.out.println("Tognummer må være et heltall, prøv igjen!");
            System.out.println("Skriv inn tognummer:");
            scan.next();
          }
          int trainNrDelete = scan.nextInt();
          if (schedule.searchDepartureByNr(trainNrDelete) == null) {
            System.out.println("Togavgang med tognummer " + trainNrDelete + " finnes ikke");
            break;
          }
          schedule.deleteDeparture(trainNrDelete);
          System.out.println("Togavgang med tognummer " + trainNrDelete + " er nå slettet");
          break;
        case EXIT:
          System.out.println("Avluttet!");
          return;
        default:
          System.out.println("Ugyldig valg, prøv igjen!");
          break;
      }
    }
  }
}
