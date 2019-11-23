package io.github.products;


public class MoviePlayer extends Product implements MultimediaControl {

  private final Screen screen;
  private final MonitorType monitorType;

  /**
   * Creates an object that inherits from Product by setting the object's name and manufacturer.
   *
   * @param id           the unique id of the movie player
   * @param name         the name of the product
   * @param manufacturer the manufacturer of the product
   * @param screen       the Screen that the product contains
   * @param itemType     the specific type of product
   * @param mt           the MonitorType that the product has
   */
  public MoviePlayer(int id, String name, String manufacturer, ItemType itemType, Screen screen,
      MonitorType mt) {
    //This project accepts an ItemType for the field 'itemType' in class Product.
    super(id, name, manufacturer, itemType);
    this.screen = screen;
    monitorType = mt;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previously");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * returns the name, manufacturer, and type of the product in the form:
   * <p>
   * Name: <i>name</i>\n Manufacturer: <i>manufacturer</i>\n Type: <i>type</i>\n Screen:
   * <i>screen</i>\n MonitorType: <i>monitorType</i>
   * </p>
   * .
   *
   * @return a String in the above format
   */
  @Override
  public String toString() {
    String pre = super.toString();
    String str = "\nScreen: " + screen + "\nMonitorType: " + monitorType;
    return (pre + str);
  }
}
