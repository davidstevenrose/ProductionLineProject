package io.github.davidstevenrose;

/**
 * Item will show that any implemented class can return the Id, Name, and Manufacturer of any
 * instance of the class. The instance can also have its Name and Manufacturer modified.
 *
 * @author drose
 */
interface Item {

  /**
   * Gets the Id of the object.
   *
   * @return the Id.
   */
  int getId();

  /**
   * Sets the name of the object.
   *
   * @param name the name to use
   */
  void setName(String name);

  /**
   * Gets the name of the object.
   *
   * @return the name
   */
  String getName();

  /**
   * sets the manufacturer of the object.
   *
   * @param manu the manufacturer to use
   */
  void setManufacturer(String manu);

  /**
   * Gets the manufacture of the object.
   *
   * @return the manufacturer
   */
  String getManufacturer();
}
