package io.github.davidstevenrose;


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
  private final String type;
  /**
   * the product manufacturer.
   */
  private String manufacturer;
  /**
   * the product name.
   */
  private String name;

  /**
   * Creates an object that inherits from Product by setting the object's name and manufacturer.
   *
   * @param name         the name of the product
   * @param manufacturer the manufacturer of the product
   * @param itemType     the specific type of product
   */
  public Product(String name, String manufacturer, ItemType itemType) {
    this.name = name;
    this.manufacturer = manufacturer;
    type = itemType.getCode();
  }

  @Override
  public int getId() {
    return id;
  }

  /**
   * Temporary method to allow the table in the product line tab to display the product's id.
   *
   * @param id the unique identification of the product.
   */
  public void setId(int id) {
    this.id = id;
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
   * returns the item code of the type assigned to the product.
   *
   * @return the item code of the type
   */
  public String getType() {
    return type;
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
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}
