/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import bus.DepartmentBLL;
import gui.IMenu;
import gui.Menu;

/**
 *
 * @author MSI
 */
public class DepartmentBLLTester {
    public static void main(String[] args) {
        DepartmentBLL deptBLL = new DepartmentBLL();
        IMenu menu = new Menu();
        menu.add("1. Show department list");
        menu.add("2. Add new department");
        menu.add("3. Update department");
        menu.add("4. Delete department");
        menu.add("5. Search department by ID");
        menu.add("6. Store data to file");
        
        int choice;
        do {
            menu.show();
            choice = menu.getChoice();
            
            switch (choice) {
                case 1:
                    deptBLL.showDepartmentList();
                    break;
                case 2:
                    deptBLL.addNewDepartment();
                    break;
                case 3:
                    deptBLL.updateDepartment();
                    break;
                case 4:
                    deptBLL.deleteDepartment();
                    break;
                case 5:
                    deptBLL.searchDepartmentbyID();
                    break;
                case 6:
                    deptBLL.storeDataToFile();
                    break;
                default:
            }
        } while(0<choice && choice<=menu.size());
    }
}
