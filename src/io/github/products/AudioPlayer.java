package io.github.products;

/**
 * <p>A special product that has basic media controls.</p>
 *
 * @author drose
 */

public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * The audio specification of the object.
   */
  private final String audioSpecification;
  /**
   * The type of media the object holds.
   */
  private final String mediaType;

  /**
   * Creates an audio player product for the production line.
   *
   * @param id                 the unique id of the product
   * @param name               the name of the product
   * @param manufacturer       the manufacturer of the product
   * @param it                 the ItemType that the product is
   * @param audioSpecification the The audio specification of the object.
   */
  public AudioPlayer(int id, String name, String manufacturer, ItemType it,
      String audioSpecification) {
    super(id, name, manufacturer, it);
    this.audioSpecification = audioSpecification;
    this.mediaType = "Unavailable";
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
   * Prints a String representation of the object.
   *
   * @return the String from Product method toString() with lines for the audio specification and
   *     media type.
   */
  @Override
  public String toString() {
    return super.toString() + "\nAudio Specification: " + this.audioSpecification + "\nMedia Type: "
        + this.mediaType;
  }
}
