package io.github.davidstevenrose;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The definition of an employee who recorded some production.
 *
 * @author drose
 */
public class Employee {

  private StringBuilder name;
  private String username;
  private String password;
  private String email;

  /**
   * Creates a new employee and checks the correctness of the user's name and password.
   *
   * @param name  the full name of the user
   * @param pword the password provided by the user
   */
  public Employee(String name, String pword) {
    //trim off any whitespace chars
    name = name.trim();
    //initialize the name and pword
    this.name = new StringBuilder(name);
    password = pword;
    //check input
    //if name does not have a space, use default values
    if (!checkName()) {
      username = "default";
      email = "user@oracleacademy.Test";
    } else {
      setUsername(name);
      setEmail(name);
    }
    //if the password is not valid, use default values
    if (!isValidPassword()) {
      password = "pw";
    }
  }

  /**
   * Creates the username of the employee. The username begins with the first char of the first name
   * concatenated with the surname. All lowercase.
   *
   * @param name the employee's name
   */
  private void setUsername(String name) {
    String[] names = name.split("\\s");
    username = names[0].charAt(0) + names[1];
    username = username.toLowerCase();
  }

  /**
   * Checks if the user provided a name with one space.
   *
   * @return true if the name contains a space; false otherwise.
   */
  private boolean checkName() {
    //the regex is any alpha character with a space somewhere in the middle
    return name.toString().matches("^\\p{Alpha}+\\s\\p{Alpha}+$");
  }

  /**
   * Creates the email of the employee. Format: %first%.%last%@oracleacademy.Test
   *
   * @param name the name of the employee
   */
  private void setEmail(String name) {
    String[] names = name.split("\\s");
    email = names[0] + "." + names[1] + "@oracleacademy.Test";
  }

  /**
   * Checks if the password has at lease one of each char: lowercase char, uppercase char, special
   * char. A special char is an element in the set \p{Punct}
   *
   * @return true is the password is valid; false otherwise.
   */
  private boolean isValidPassword() {
    Matcher low = Pattern.compile("\\p{Lower}+").matcher(password);
    Matcher upp = Pattern.compile("\\p{Upper}+").matcher(password);
    Matcher pun = Pattern.compile("\\p{Punct}+").matcher(password);
    return low.find() && upp.find() && pun.find();
  }

  /**
   * Returns the information of an employee.
   *
   * @return a string representation of the employee
   */
  @Override
  public String toString() {
    return "Employee Details\n"
        + "Name : " + name.toString() + "\n"
        + "Username : " + username + "\n"
        + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }

}
