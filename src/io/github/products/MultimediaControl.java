package io.github.products;

/**
 * <p>This interface forces any implemented class to have basic media controls.</p>
 *
 * @author drose
 */
interface MultimediaControl {

  /**
   * Plays the object.
   */
  void play();

  /**
   * Stops the playing of the object.
   */
  void stop();

  /**
   * Goes to the previous media.
   */
  void previous();

  /**
   * Goes to the next media.
   */
  void next();
}
