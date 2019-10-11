package io.github.davidstevenrose;

/**
 * Any class that can be described as a screen that has a resolution, refresh rate, and response
 * time.
 *
 * @author drose
 */
public interface ScreenSpec {

  /**
   * Returns the resolution of the screen.
   *
   * @return <i>an integer</i><b>x</b><i>an integer</i>
   */
  String getResolution();

  /**
   * Gets the refresh rate of the screen.
   *
   * @return the refresh rate
   */
  int getRefreshRate();

  /**
   * Gets the response time of the screen.
   *
   * @return the response time
   */
  int getResponseTime();
}
