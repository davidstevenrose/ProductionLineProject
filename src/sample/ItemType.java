package sample;

/**
 * This contains the required constants that represent the available types for a product. Each label
 * has a shortened String representation.
 *
 * @author drose
 */
public enum ItemType {

  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  private String code;

  ItemType(String c) {
    code = c;
  }

  public String getCode() {
    return code;
  }
}
