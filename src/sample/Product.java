package sample;

/**
 * An abstract class that defines a product with an integer Id, and Strings name, manufacturer, and
 * type.
 *
 * @author drose
 */
public abstract class Product implements Item {

  /**
   * the product id.
   */
  private int id;
  /**
   * the product type.
   */
  private String type;
  /**
   * the procudt manufacturer.
   */
  private String manufacturer;
  /**
   * the procudt name.
   */
  private String name;

  public Product(String name) {
    this.name = name;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manu) {
    manufacturer = manu;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * returns the name, manufacturer, and type of the product in the form:
   * <p>
   * Name: <i>name</i>\n Manufacturer: <i>manufacturer</i>\n Type: <i>type</i>
   * </p>
   * .
   *
   * @return a String in the above format
   */
  @Override
  public String toString() {
    String data = "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
    return data;
  }
}
