package cs3500.pa04.view;


/**
 * Abstracted view class
 */
public abstract class AbstractView {
  protected final Appendable appendable;

  public AbstractView(Appendable a) {
    this.appendable = a;
  }

  /**
   * Prints the given lines to the terminal
   *
   * @param lines a String of text
   */
  public void printLine(String lines) {
    try {
      appendable.append(lines).append(System.lineSeparator());
    } catch (Exception e) {
      // Handle exception if append fails
      e.printStackTrace();
    }
  }

}
