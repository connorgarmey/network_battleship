package cs3500.pa04.control;

import cs3500.pa04.model.Coord;
import cs3500.pa04.view.UserView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for a User Dependency Injector
 */
public class DependencyInjector {
  private List<Coord> current;
  private List<Coord> guessed;
  private Scanner input;
  private UserView view;


  /**
   * Dependency injector constructor
   *
   * @param scanner the Scanner object
   * @param view the View object
   */
  public DependencyInjector(Scanner scanner, UserView view) {
    this.input = scanner;
    this.view = view;
    this.current = new ArrayList<>();
    this.guessed = new ArrayList<>();
  }



  /**
   * Processes and validates user input for shots
   */
  public void acceptShots(int height, int width, int count) {
    boolean invalidInput = true;
    view.printLine("Please enter " + count + " shots");
    view.printLine("_____________________");
    List<Coord> list = new ArrayList<>();

    while (invalidInput) {
      for (int i = 0; i < count; i++) {
        if (input.hasNext()) {
          String s = input.next();
          String s2 = input.next();
          int c1;
          int c2;
          try {
            c1 = Integer.parseInt(s);
            c2 = Integer.parseInt(s2);
          } catch (Exception e) {
            c1 = -1;
            c2 = -1;
          }
          list.add(new Coord(c1, c2));
        }
      }

      if (isValidShotsList(list, height, width)) {
        invalidInput = false;
      } else {
        list.clear();
        view.printLine("Please enter valid and unique coordinates.");
      }
    }
    current = list;
  }


  /**
   * Determines if the coordinates are in bounds and not guessed already
   *
   * @param list the coordinates to be validated
   * @param height the board height
   * @param width the board width
   *
   * @return if the list is valid
   */
  private boolean isValidShotsList(List<Coord> list, int height, int width) {
    for (Coord c : list) {
      if (c.getX() > width || c.getY() > height || c.getX() < 0 || c.getY() < 0) {
        return false;
      }
      if (guessed.contains(c)) {
        return false;
      }
    }
    return true;
  }


  /**
   * Clears the list of coordinates
   */
  public void clear() {
    current = new ArrayList<>();
  }


  /**
   * Get the List object
   *
   * @return list of coordinates
   */
  public List<Coord> retrieveShots() {
    return current;
  }


}


