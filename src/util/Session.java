/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ayoub
 */
public class Session {

    public static List<SessionItem> myMap = new ArrayList<>();

    public static Object getAttribut(String name) {

        for (int i = 0; i < myMap.size(); i++) {
            SessionItem sessionItem = myMap.get(i);
            if (sessionItem.getKey().equals(name)) {
                return sessionItem.getObject();
            }
        }
        return null;
    }

    public static Object setAttribut(Object obj, String name) {
        SessionItem sessionItem = new SessionItem();
        sessionItem.setKey(name);
        sessionItem.setObject(obj);
        myMap.add(sessionItem);
        return myMap;
    }


    public static void delete(String name) {

        Object object = new Object();
        for (int i = 0; i < myMap.size(); i++) {
            SessionItem sessionItem = myMap.get(i);
            if (sessionItem.getKey().equals(name)) {
                System.out.println("QQQQQQQQQQQQQQQ" + name);
                sessionItem.setKey("");
                sessionItem.setObject(object);

            }
        }

    }

//    public static void d(String name) {
//        
//    }
    public static void clear() {
        myMap.clear();
    }

}
