package sample;

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
   * @param name               the name of the product
   * @param manufacturer       the manufacturer of the product
   * @param audioSpecification the The audio specification of the object.
   */
  public AudioPlayer(String name, String manufacturer, ItemType it, String audioSpecification) {
    super(name, manufacturer, it);
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
   * media type.
   */
  @Override
  public String toString() {
    String str =
        super.toString() + "\nAudio Specification: " + this.audioSpecification + "\nMedia Type: "
            + this.mediaType;
    return str;
  }
}
