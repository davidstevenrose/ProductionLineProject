package io.github.davidstevenrose;

import java.util.Date;

/**
 * Represents a recorded item that has been input into the system but has not yet been processed.
 * Objects of this class are displayed in the TextArea on the production log tab.
 */
class ProductionRecord {

  /**
   * The number of the item in production.
   */
  private int productionNumber;
  /**
   * The ID that identifies the product.
   */
  private int productID;
  /**
   * The serial number of the product.
   */
  private String serialNumber;
  /**
   * The date which the product was put into the system.
   */
  private Date dateProduced;

  /**
   * Creates a new product with the given ID. Sets the production number to 0, S.N. to
   *     0, and the date produced to today's date.
   * @param productID the ID of the item
   * @deprecated
   *
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * Creates a new product with the given ID, a given date of entry, the product to be recorded, and
   * the amount of ProductionRecord instances that exist. Generates the serial number by using the
   * first three letters of the Manufacturer name, then the two letter ItemType code, then five
   * digits that are unique.
   *
   * @param productID    the ID of the item
   * @param dateProduced a date that shows when the product was entered into the system
   * @param product      the product that is being recorded
   * @param count        the number of instances ProductionRecord created
   */
  public ProductionRecord(int productID,
      Date dateProduced, Product product, int count) {
    this.productID = productID;
    this.dateProduced = new Date(dateProduced.getTime());
    String type = product.getType();
    String manu = product.getManufacturer().substring(0, 3);
    serialNumber = manu + type + String.format("%05d", count);
  }

  /**
   * Creates a new product with the given ID, a production number, a given S.N, and a given date of
   * entry.
   *
   * @param productionNumber the production number of the product
   * @param productID        the ID of the item
   * @param serialNumber     the serial number of the product
   * @param dateProduced     a date that shows when the product was entered into the system
   */
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productID = productID;
    this.productionNumber = productionNumber;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Returns the production number of the product.
   *
   * @return the production number
   */
  private int getProductionNumber() {
    return productionNumber;
  }

  /**
   * Sets the production number of the product.
   *
   * @param productionNumber the production number
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Returns the product's ID.
   *
   * @return the product's ID.
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Sets the product's ID.
   *
   * @param productID the product's ID.
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Returns the product's serial number as a String.
   *
   * @return the product's serial number
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Sets the product's serial number.
   *
   * @param serialNumber the product's serial number as a String
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Returns the value of the date the product was produced.
   *
   * @return the date the product was produced
   */
  public Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  /**
   * Sets the value of the date the product was produced.
   *
   * @param dateProduced the date the product was produced.
   */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Returns a String representation of an object of this class as:
   * <p>
   * Prod. Num: <i>productionNumber</i><br /> Product ID: <i>productID</i><br /> Serial Num:
   * <i>serialNumber</i><br /> Date: <i>day mon date hh:mm:ss timezone yyyy</i>
   * </p>.
   *
   * @return a String representation in the form mentioned above.
   */
  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNumber() + "\nProduct ID: " + getProductID()
        + "\nSerial Num: "
        + getSerialNumber() + "\nDate: " + getDateProduced().toString();
  }
}
