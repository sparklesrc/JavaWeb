/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.zelper;

/**
 *
 * @author LAB704-00
 */
public class Helper {

    public static Integer toInteger(String string, Integer deff) {
        Integer value = deff;
        try {

            if (string != null) {
                value = Integer.valueOf(string.toString());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;

    }
}
