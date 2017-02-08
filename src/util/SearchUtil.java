/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Abed
 */
public class SearchUtil {
    public static String addConstraint(String beanAbrev, String atributeName, String operator, Object value) {
        if (value != null && !(value+"").equals("")) {
            return " AND " + beanAbrev + "." + atributeName + " " + operator + " '" + value + "'";
        }
        return "";
    }
}
