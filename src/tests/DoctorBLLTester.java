/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import bus.DoctorBLL;
import gui.IMenu;
import gui.Menu;

/**
 *
 * @author MSI
 */
public class DoctorBLLTester {
    public static void main(String[] args) {
        DoctorBLL doctorBLL = new DoctorBLL();
        IMenu menu = new Menu();
        menu.add("1. Show doctor list");
        menu.add("2. Add new doctor");
        menu.add("3. Update doctor");
        menu.add("4. Delete doctor");
        menu.add("5. Search doctor by name");
        menu.add("6. Store data to file");
        
        int choice;
        do {
            menu.show();
            choice = menu.getChoice();
            
            switch (choice) {
                case 1:
                    doctorBLL.showDoctorList();
                    break;
                case 2:
                    doctorBLL.addNewDoctor();
                    break;
                case 3:
                    doctorBLL.updateDoctor();
                    break;
                case 4:
                    doctorBLL.deleteDoctor();
                    break;
                case 5:
                    doctorBLL.searchDoctorbyName();
                    break;
                case 6:
                    doctorBLL.storeDataToFile();
                    break;
                
                default:
            }
        } while(0<choice && choice<=menu.size());
    }
}
