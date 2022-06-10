/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class DateHandler {
    public static String toPatternFormat(Date date, String pattern) {
        if(date==null) return null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    
    public static Date createDateFromPattern(String date, String regex) throws Exception {
//        System.out.println(date);
//        System.out.println(regexPattern);
        try {
            return new SimpleDateFormat(regex).parse(date);
        }
        catch (Exception e) {
            return null;
        }
    }
}
