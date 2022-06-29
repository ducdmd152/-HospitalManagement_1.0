// @author SE160104
package gui;
 
import bus.DepartmentBLL;
import bus.DoctorBLL;
import bus.HospitalBLL;
import java.util.List;
import java.util.Vector;
import repo.*;
import tools.Console;

public class SE160104 {
    static HospitalBLL hospitalBLL = new HospitalBLL();
    static DepartmentBLL deptBLL = hospitalBLL.getDepartmentBLL();
    static DoctorBLL doctBLL = hospitalBLL.getDoctorBLL();
    
    static IMenu menu = new Menu();
    static List<IMenu> subMenus = new Vector();
    
    public static void buildMenu() {
        menu.add("1. Show information"
                + "\n  1.1 Show doctor list"
                + "\n  1.2 Show department list");
        menu.add("2. Add new"
                + "\n  2.1 Add new doctor"
                + "\n  2.2 Add new department");
        menu.add("3. Update information"
                + "\n  3.1 Update doctor"
                + "\n  3.2 Update department");
        menu.add("4. Delete"
                + "\n  4.1 Delete doctor"
                + "\n  4.2 Delete department");
        menu.add("5. Search information"
                + "\n  5.1 Search doctor by name"
                + "\n  5.2 Search department by ID");
        menu.add("6. Store data to file");
        menu.add("Others- Quit");
        
        for(int i=0;i<menu.size();i++) subMenus.add(new Menu());
        
        subMenus.get(0).add("1.1 Show doctor list");
        subMenus.get(0).add("1.2 Show department list");
        
        subMenus.get(1).add("2.1 Add new doctor");
        subMenus.get(1).add("2.2 Add new department");
        
        subMenus.get(2).add("3.1 Update doctor");
        subMenus.get(2).add("3.2 Update department");
        
        subMenus.get(3).add("4.1 Delete doctor");
        subMenus.get(3).add("4.2 Delete department");
        
        subMenus.get(4).add("5.1 Search doctor by name");
        subMenus.get(4).add("5.2 Search department by ID");
    }
    
    public static void main(String[] args) {
        buildMenu();
        
        int choice;
        do {
            menu.show();
            choice = menu.getChoice();
            IMenu curSubMenu = null;
            
            if(choice>0 && choice<menu.size())
                curSubMenu = subMenus.get(choice-1);
            
            int subChoice;
            
            switch (choice) {
                case 1:
                    do {
                        curSubMenu.show();
                        subChoice = curSubMenu.getChoice();
                        switch (subChoice) {
                            case 1:
                                doctBLL.showDoctorList();
                                break;
                            case 2:
                                deptBLL.showDepartmentList();
                                break;
                            default:

                        }
                    } while(0<subChoice && subChoice<=curSubMenu.size());
                    break;
                case 2:
                    do {
                        curSubMenu.show();
                        subChoice = curSubMenu.getChoice();
                        switch (subChoice) {
                            case 1:
                                doctBLL.addNewDoctor();
                                break;
                            case 2:
                                deptBLL.addNewDepartment();
                                break;
                            default:

                        }
                    } while(0<subChoice && subChoice<=curSubMenu.size());
                    break;
                case 3:
                    do {
                        curSubMenu.show();
                        subChoice = curSubMenu.getChoice();
                        switch (subChoice) {
                            case 1:
                                doctBLL.updateDoctor();
                                break;
                            case 2:
                                deptBLL.updateDepartment();
                                break;
                            default:

                        }
                    } while(0<subChoice && subChoice<=curSubMenu.size());
                    break;
                case 4:
                    do {
                        curSubMenu.show();
                        subChoice = curSubMenu.getChoice();
                        switch (subChoice) {
                            case 1:
                                doctBLL.deleteDoctor();
                                break;
                            case 2:
                                deptBLL.deleteDepartment();
                                break;
                            default:

                        }
                    } while(0<subChoice && subChoice<=curSubMenu.size());
                    break;
                case 5:
                    do {
                        curSubMenu.show();
                        subChoice = curSubMenu.getChoice();
                        switch (subChoice) {
                            case 1:
                                doctBLL.searchDoctorbyName();
                                break;
                            case 2:
                                deptBLL.searchDepartmentbyID();
                                break;
                            default:

                        }
                    } while(0<subChoice && subChoice<=curSubMenu.size());
                    break;
                case 6:
                    deptBLL.storeDataToFile();
                    doctBLL.storeDataToFile();
                    System.out.println("Store file successfully");
                    break;
                default:
            }
        } while(choice > 0 && choice < menu.size());
    }
}
