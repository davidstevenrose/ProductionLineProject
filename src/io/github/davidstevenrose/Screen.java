package io.github.davidstevenrose;

public class Screen implements ScreenSpec {

  /**
   * The resolution. Format: "720x480" where numbers vary
   */
  private final String resolution;
  /**
   * The refresh rate.
   */
  private final int refreshRate;
  /**
   * The response time.
   */
  private final int responseTime;

  /**
   * Creates a Screen object with a resolution, refresh rate, and response time.
   *
   * @param res      the resolution
   * @param refresh  the refresh rate
   * @param response the response time
   */
  public Screen(String res, int refresh, int response) {
    resolution = res;
    refreshRate = refresh;
    responseTime = response;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * returns the resolution, refresh rate, and response time of the screen in the form:
   * <p>
   * Resolution: <i>resolution</i>\n Refresh rate: <i>refreshRate</i>\n Response time:
   * <i>responseTime</i>
   * </p>
   * .
   *
   * @return a String in the above format
   */
  @Override
  public String toString() {
    return "Resolution: " + resolution + "\nRefresh rate: " + refreshRate
        + "\nResponse time: " + responseTime;
  }
}
