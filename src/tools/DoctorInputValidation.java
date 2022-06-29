/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author MSI
 */
public class DoctorInputValidation {

    public static boolean checkDoctorIDFormat(String input) {
        return input.matches("^DOC\\d{3}$");
    }

    public static boolean checkNameLength(String input) {
        if(input.isEmpty()) return false;
        return input.length()<=20;
    }

    public static boolean checkSexFormat(String input) {
        if(input.isBlank()) return false;
        
        input = input.trim().toUpperCase();
        return input.equals("TRUE") || input.equals("FALSE");
    }

    public static boolean checkAddressFormat(String input) {
        return !input.isBlank() && input.length()<=50;
    }
    
}
