package io.github.products;

/**
 * This contains the required constants that represent the available types for a product. Each label
 * has a shortened String representation.
 *
 * @author drose
 */
public enum ItemType {

  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  private final String code;

  ItemType(String c) {
    code = c;
  }

  public String getCode() {
    return code;
  }
}
