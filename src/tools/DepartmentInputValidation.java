/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author MSI
 */
public class DepartmentInputValidation {

    public static boolean checkDepartmentIDFormat(String input) {
        return input.matches("^DEP\\d{2}$");
    }

    public static boolean checkNameLength(String input) {
        if(input.isEmpty()) return false;
        return 5<=input.length() && input.length()<=20;
    }
    
}
